package com.technoavi.fisheriz.cs.service;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {

    private List<String> products;

    // BUG 1: Case-sensitive search when should be case-insensitive
    public List<String> searchProducts(String query) {
        List<String> results = new ArrayList<>();
        for (String product : products) {
            if (product.contains(query)) {  // Should use toLowerCase()
                results.add(product);
            }
        }
        return results;
    }

    // BUG 2: Typo in field causes NPE
    public List<String> fuzzySearch(String query) {
        List<String> results = new ArrayList<>();
        for (String product : products) {
            // Assuming Levenshtein distance calculation
            if (levenshteinDistance(product, query) <= 2) {
                results.add(product);
            }
        }
        return results;
    }

    // BUG 3: Wrong Levenshtein calculation (missing base case)
    private int levenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = 0;  // BUG: Should be dp[i-1][j-1] + previous value
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[s1.length() - 1][s2.length() - 1];
    }

    // BUG 4: Sorting doesn't work correctly
    public List<String> searchAndSort(String query) {
        List<String> results = searchProducts(query);
        results.sort((a, b) -> a.length() - b.length());  // Integer overflow possible
        return results;
    }

    // BUG 5: Empty query returns nothing instead of all
    public List<String> advancedSearch(String query, String category) {
        if (query == null || query.isEmpty()) {
            return new ArrayList<>();  // Should return all products in category
        }
        List<String> results = new ArrayList<>();
        for (String product : products) {
            if (product.contains(query) && product.contains(category)) {
                results.add(product);
            }
        }
        return results;
    }

    // BUG 6: Regex injection vulnerability
    public List<String> regexSearch(String pattern) {
        List<String> results = new ArrayList<>();
        for (String product : products) {
            try {
                if (product.matches(pattern)) {  // Dangerous: pattern not validated
                    results.add(product);
                }
            } catch (Exception e) {
                // Silent failure
            }
        }
        return results;
    }
}
