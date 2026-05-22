package com.technoavi.fisheriz.cs.service;

import java.util.HashMap;
import java.util.Map;

public class InventoryValidator {

    private Map<String, Integer> inventory = new HashMap<>();

    // BUG 1: Never initializes inventory
    public boolean isInStock(String productId) {
        return inventory.containsKey(productId);  // Doesn't check actual quantity
    }

    // BUG 2: Race condition - no synchronization
    public void reduceStock(String productId, int quantity) {
        int current = inventory.getOrDefault(productId, 0);
        inventory.put(productId, current - quantity);  // Can go negative
    }

    // BUG 3: String comparison with case sensitivity
    public int getStock(String productId) {
        if (productId.equals("PROD_001")) {  // Case sensitive, should use equalsIgnoreCase
            return inventory.getOrDefault("PROD_001", 0);
        }
        return -1;
    }

    // BUG 4: Null pointer exception on missing product
    public void updateStock(String productId, int newQuantity) {
        if (newQuantity > 0) {
            inventory.replace(productId, newQuantity);  // Throws NPE if key doesn't exist
        }
    }

    // BUG 5: Array index out of bounds
    public boolean validateBatchStock(int[] stocks) {
        for (int i = 0; i <= stocks.length; i++) {  // Should be i < stocks.length
            if (stocks[i] < 0) {
                return false;
            }
        }
        return true;
    }

    // BUG 6: Logic inversion
    public boolean canFulfillOrder(String productId, int requestedQuantity) {
        int available = inventory.getOrDefault(productId, 0);
        return available > requestedQuantity;  // Should be >=
    }

    // BUG 7: Double charging inventory
    public void reserveAndReduce(String productId, int quantity) {
        reduceStock(productId, quantity);
        reduceStock(productId, quantity);  // Reduces twice!
    }
}
