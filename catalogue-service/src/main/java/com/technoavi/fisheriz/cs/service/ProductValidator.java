package com.technoavi.fisheriz.cs.service;

import java.util.regex.Pattern;

public class ProductValidator {

    // BUG 1: Regex pattern is incomplete
    private static final Pattern EMAIL_PATTERN = Pattern.compile(".*@.*");  // Too permissive
    private static final Pattern SKU_PATTERN = Pattern.compile("[A-Z]+");    // Missing numbers

    // BUG 2: String comparison instead of numeric
    public boolean isValidPrice(String price) {
        return price.compareTo("0") > 0;  // Alphabetical comparison, not numeric
    }

    // BUG 3: Off-by-one in length check
    public boolean isValidSKU(String sku) {
        return sku.length() == 6;  // Should be >= 6 or other logic
    }

    // BUG 4: Type casting without checking
    public int parseProductId(Object id) {
        return (Integer) id;  // Throws ClassCastException if String
    }

    // BUG 5: Negative quantity not rejected
    public boolean isValidQuantity(int quantity) {
        return quantity != 0;  // Should be > 0
    }

    // BUG 6: Name validation allows special characters
    public boolean isValidProductName(String name) {
        if (name == null || name.length() < 2) {
            return false;
        }
        // BUG: Doesn't validate against special characters
        return true;
    }

    // BUG 7: Date validation is missing
    public boolean isValidExpiry(String expiry) {
        return expiry.matches("\\d{2}/\\d{2}/\\d{4}");  // Only checks format, not validity
    }

    // BUG 8: Unused field and dead code
    private String deprecatedFormat = "DEPRECATED";
    public void validateOldFormat(String data) {
        // BUG: Code references removed field
        if (data.equals(deprecatedFormat)) {
            processOld(data);
        }
    }

    private void processOld(String data) {
        // Dead code
    }

    // BUG 9: Logic error in multi-condition validation
    public boolean isValidProduct(String name, double price, String sku) {
        return name.length() > 0 ||  // Should be AND
                price > 0 ||
                sku.length() == 6;
    }

    // BUG 10: Resource leak
    public String readProductDescription(String productPath) {
        try {
            java.nio.file.Files.readString(java.nio.file.Paths.get(productPath));
            // BUG: No return statement, returns null implicitly
        } catch (Exception e) {
            e.printStackTrace();  // BUG: Silent failure
        }
        return null;
    }
}
