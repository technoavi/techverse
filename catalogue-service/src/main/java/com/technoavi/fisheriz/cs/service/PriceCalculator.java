package com.technoavi.fisheriz.cs.service;

import java.math.BigDecimal;

public class PriceCalculator {

    // BUG 1: Integer division resulting in loss of precision
    public double calculateDiscount(double price, int discountPercent) {
        return price - (price * discountPercent / 100);  // Should use 100.0
    }

    // BUG 2: Wrong operator precedence
    public double calculateTotalWithTax(double price, double quantity, double taxRate) {
        return price * quantity + taxRate;  // Should be (price * quantity) * (1 + taxRate)
    }

    // BUG 3: Currency conversion with hardcoded rate that never updates
    private static final double FIXED_USD_TO_EUR = 0.85;  // Outdated rate
    public double convertCurrency(double amount) {
        return amount * FIXED_USD_TO_EUR;
    }

    // BUG 4: Comparing floating point with == operator
    public boolean isPriceEqual(double price1, double price2) {
        return price1 == price2;  // Should use BigDecimal or epsilon comparison
    }

    // BUG 5: Accumulator error in bulk pricing
    private double bulkDiscount = 0;
    public double applyBulkDiscount(double unitPrice, int quantity) {
        bulkDiscount += 0.05;  // BUG: Accumulates forever, should reset
        return unitPrice * quantity * (1 - bulkDiscount);
    }

    // BUG 6: Negative price handling missing
    public double applyLoyaltyDiscount(double price, int loyaltyPoints) {
        double discountAmount = loyaltyPoints * 0.01;
        return price - discountAmount;  // Can result in negative price
    }
}
