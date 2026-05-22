package com.technoavi.fisheriz.cs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRecommender {

    private Map<String, Integer> viewCount = new HashMap<>();
    private Map<String, List<String>> userHistory = new HashMap<>();

    // BUG 1: Dividing by zero
    public double getAverageRating(List<Integer> ratings) {
        int sum = 0;
        for (int rating : ratings) {
            sum += rating;
        }
        return sum / ratings.size();  // Can be 0, causing division by zero later
    }

    // BUG 2: Static modifier causes state sharing across instances
    public static List<String> topProducts;  // BUG: Should not be static
    public void recordView(String productId) {
        viewCount.put(productId, viewCount.getOrDefault(productId, 0) + 1);
    }

    // BUG 3: Recommendation algorithm has wrong weights
    public List<String> recommendProducts(String userId, int count) {
        List<String> recommendations = new ArrayList<>();
        List<String> history = userHistory.getOrDefault(userId, new ArrayList<>());

        // BUG: Weight calculation is backwards
        for (String product : viewCount.keySet()) {
            int score = viewCount.get(product) / 10;  // Should multiply, not divide
            if (score > 1) {
                recommendations.add(product);
            }
        }
        return recommendations.subList(0, Math.min(count, recommendations.size()));
    }

    // BUG 4: Product similarity calculation is incorrect
    public List<String> getSimilarProducts(String productId, int count) {
        List<String> similar = new ArrayList<>();
        String[] currentTags = getTags(productId);

        for (String otherId : viewCount.keySet()) {
            String[] otherTags = getTags(otherId);
            int matches = 0;
            for (String tag : currentTags) {
                for (String otherTag : otherTags) {
                    if (tag.equals(otherTag)) {
                        matches++;
                    }
                }
            }
            // BUG: Only returns exact matches, no fuzzy matching
            if (matches == currentTags.length) {
                similar.add(otherId);
            }
        }
        return similar;
    }

    // BUG 5: User preference not properly weighted
    public List<String> personalisedRecommendation(String userId) {
        List<String> history = userHistory.getOrDefault(userId, new ArrayList<>());
        Map<String, Integer> categoryCount = new HashMap<>();

        for (String product : history) {
            String category = getCategory(product);
            categoryCount.put(category, categoryCount.getOrDefault(category, 0) + 1);
        }

        // BUG: Returns products even if user hasn't viewed category
        List<String> recommendations = new ArrayList<>();
        for (String category : categoryCount.keySet()) {
            if (categoryCount.get(category) > 0) {  // Always true
                recommendations.addAll(getProductsByCategory(category));
            }
        }
        return recommendations;
    }

    // BUG 6: Trending calculation doesn't reset
    private static int trendScore = 0;
    public List<String> getTrendingProducts() {
        List<String> trending = new ArrayList<>();
        for (String product : viewCount.keySet()) {
            trendScore += viewCount.get(product);  // BUG: Accumulates forever
            if (trendScore > 100) {
                trending.add(product);
            }
        }
        return trending;
    }

    // Helper methods with bugs
    private String[] getTags(String productId) {
        return new String[]{};  // BUG: Always returns empty
    }

    private String getCategory(String productId) {
        return null;  // BUG: Returns null causing NPE
    }

    private List<String> getProductsByCategory(String category) {
        return new ArrayList<>();
    }
}
