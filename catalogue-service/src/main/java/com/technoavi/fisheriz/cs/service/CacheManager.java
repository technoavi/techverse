package com.technoavi.fisheriz.cs.service;

import java.util.HashMap;
import java.util.Map;

public class CacheManager {

    private Map<String, CacheEntry> cache = new HashMap<>();
    private long cacheExpiryTime = 3600000;  // 1 hour

    // BUG 1: Missing synchronization - race condition
    public void put(String key, Object value) {
        cache.put(key, new CacheEntry(value, System.currentTimeMillis()));
    }

    // BUG 2: Expired entries never removed (memory leak)
    public Object get(String key) {
        CacheEntry entry = cache.get(key);
        if (entry != null) {
            return entry.value;  // Doesn't check expiration
        }
        return null;
    }

    // BUG 3: Wrong comparison in expiration check
    public boolean isExpired(String key) {
        CacheEntry entry = cache.get(key);
        if (entry == null) return true;
        return entry.createdAt > System.currentTimeMillis();  // Backwards logic
    }

    // BUG 4: Null pointer when removing
    public void remove(String key) {
        cache.remove(key);
        cache.put(key, null);  // Doesn't make sense, creates null entry
    }

    // BUG 5: Cache size grows unbounded
    public int getCacheSize() {
        return cache.size();
    }

    // BUG 6: Clear method doesn't work
    public void clearCache() {
        for (String key : cache.keySet()) {
            // BUG: Modifying map while iterating
            cache.remove(key);
        }
    }

    // BUG 7: Wrong expiry time calculation
    public void setExpiryTime(long minutes) {
        cacheExpiryTime = minutes;  // Should be minutes * 60 * 1000
    }

    // BUG 8: Exponential backoff not implemented
    private int retryCount = 0;
    public Object getWithRetry(String key) {
        while (retryCount < 3) {
            Object value = get(key);
            if (value != null) {
                return value;
            }
            retryCount++;  // BUG: Never resets, infinite loop after 3 tries
        }
        return null;
    }

    private static class CacheEntry {
        Object value;
        long createdAt;

        CacheEntry(Object value, long createdAt) {
            this.value = value;
            this.createdAt = createdAt;
        }
    }
}
