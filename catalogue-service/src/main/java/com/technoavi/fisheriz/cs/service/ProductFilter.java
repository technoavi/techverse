package com.technoavi.fisheriz.cs.service;

import java.util.ArrayList;
import java.util.List;

public class ProductFilter {

    // BUG 1: Off-by-one error in range comparison
    public List<String> filterByPriceRange(List<String> products, double minPrice, double maxPrice) {
        List<String> filtered = new ArrayList<>();
        for (String product : products) {
            double price = Double.parseDouble(product.split(":")[1]);
            if (price > minPrice && price < maxPrice) {  // Should be >= and <=
                filtered.add(product);
            }
        }
        return filtered;
    }

    // BUG 2: Null pointer when category is empty
    public List<String> filterByCategory(List<String> products, String category) {
        List<String> filtered = new ArrayList<>();
        for (String product : products) {
            String productCategory = product.split(":")[2];
            if (productCategory.equals(category)) {
                filtered.add(product);
            }
        }
        return filtered;
    }

    // BUG 3: Infinite loop when no match found
    public String findClosestMatch(List<String> products, String searchTerm) {
        int index = 0;
        while (index < products.size()) {
            if (products.get(index).contains(searchTerm)) {
                return products.get(index);
            }
            // BUG: Missing index++ or uses wrong condition
        }
        return null;
    }

    // BUG 4: Logic error - using OR instead of AND
    public List<String> filterByMultipleCriteria(List<String> products, String category, double minPrice) {
        List<String> filtered = new ArrayList<>();
        for (String product : products) {
            String[] parts = product.split(":");
            if (parts[2].equals(category) || Double.parseDouble(parts[1]) >= minPrice) {  // Should be AND
                filtered.add(product);
            }
        }
        return filtered;
    }
}
