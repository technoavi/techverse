package com.technoavi.fisheriz.cs.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.io.IOException;

@RestController
@RequestMapping("/api/products")
public class VulnerableProductController {

    // VULN 1: Missing CSRF protection
    @PostMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestParam String productId, @RequestParam String name) {
        // VULNERABLE - No @PostMapping CSRF token validation, no CSRF protection
        // Can be exploited via cross-site request forgery
        return ResponseEntity.ok("Product updated");
    }

    // VULN 2: Insecure CORS configuration
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        // VULNERABLE - Allows requests from any origin with wildcard CORS
        return ResponseEntity.ok("All products");
    }

    // VULN 3: Reflected XSS vulnerability
    @GetMapping("/search")
    public ResponseEntity<?> searchProduct(@RequestParam String query) {
        // VULNERABLE - User input directly returned without escaping
        return ResponseEntity.ok("<html><body>Search results for: " + query + "</body></html>");
    }

    // VULN 4: Stored XSS - User input in database
    @PostMapping("/review")
    public ResponseEntity<?> addReview(@RequestParam String productId, @RequestParam String review) {
        // VULNERABLE - Review stored without sanitization
        storeInDatabase(productId, review);  // Review will be displayed to other users without escaping
        return ResponseEntity.ok("Review added");
    }

    // VULN 5: DOM-based XSS
    @GetMapping("/filter")
    public String filterProducts(@RequestParam String category) {
        // VULNERABLE - JavaScript generated with user input
        return "<script>" +
               "var category = '" + category + "';" +  // XSS if category contains quote
               "document.getElementById('result').innerHTML = category;" +
               "</script>";
    }

    // VULN 6: URL Redirect vulnerability (Open Redirect)
    @GetMapping("/redirect")
    public String redirectToProduct(@RequestParam String url) {
        // VULNERABLE - No validation on redirect URL
        // Can be exploited: /redirect?url=http://malicious.com
        return "redirect:" + url;
    }

    // VULN 7: Insecure direct object reference (IDOR)
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable String productId) {
        // VULNERABLE - No authorization check
        // User can access any product by guessing IDs
        return ResponseEntity.ok(getProductFromDB(productId));
    }

    // VULN 8: Path traversal in file download
    @GetMapping("/download")
    public ResponseEntity<?> downloadProductManual(@RequestParam String filename) {
        // VULNERABLE - No path validation
        // Can download: /download?filename=../../../../etc/passwd
        try {
            byte[] fileContent = readFile("/products/manuals/" + filename);
            return ResponseEntity.ok(fileContent);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // VULN 9: Weak authentication - predictable tokens
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestParam String username, @RequestParam String password) {
        // VULNERABLE - Token based on predictable data
        String token = username + "_" + System.currentTimeMillis();
        return ResponseEntity.ok("{\"token\":\"" + token + "\"}");
    }

    // VULN 10: Information disclosure - stack trace in response
    @GetMapping("/details/{id}")
    public ResponseEntity<?> getProductDetails(@PathVariable String id) {
        try {
            Integer.parseInt(id);
            return ResponseEntity.ok(getProductData(id));
        } catch (Exception e) {
            // VULNERABLE - Exposes stack trace
            return ResponseEntity.badRequest().body(e.toString() + "\n" + e.getStackTrace());
        }
    }

    // VULN 11: Missing rate limiting
    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseProduct(@RequestParam String productId, @RequestParam int quantity) {
        // VULNERABLE - No rate limiting, brute force possible
        processPurchase(productId, quantity);
        return ResponseEntity.ok("Purchase successful");
    }

    // VULN 12: Insecure HTTP headers
    @GetMapping("/export")
    public ResponseEntity<?> exportProductList() {
        // VULNERABLE - Missing security headers
        // No: X-Content-Type-Options: nosniff
        // No: X-Frame-Options: DENY
        // No: Content-Security-Policy
        return ResponseEntity.ok("Product data");
    }

    // VULN 13: Zip bomb vulnerability
    @PostMapping("/bulk-import")
    public ResponseEntity<?> importProductsFromZip(@RequestParam String zipFile) {
        // VULNERABLE - No validation on zip file size or contents
        try {
            extractAndProcess(zipFile);  // Can be exploited with compressed files
            return ResponseEntity.ok("Import successful");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    // VULN 14: Missing input validation
    @PostMapping("/batch-update")
    public ResponseEntity<?> batchUpdateProducts(@RequestBody String[] productIds) {
        // VULNERABLE - No input validation
        for (String id : productIds) {
            updateProduct(id);  // Can contain malicious data
        }
        return ResponseEntity.ok("Batch update complete");
    }

    // VULN 15: Sensitive data exposure
    @GetMapping("/user/{userId}/orders")
    public ResponseEntity<?> getUserOrders(@PathVariable String userId) {
        // VULNERABLE - Returns sensitive data without encryption over HTTP
        // Should use HTTPS and sensitive data should not be logged
        return ResponseEntity.ok(getUserOrderHistory(userId));
    }

    // VULN 16: Insecure HTTP method
    @RequestMapping(value = "/config", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<?> manageConfig(@RequestParam String key, @RequestParam String value) {
        // VULNERABLE - Allows GET on state-changing operations
        return ResponseEntity.ok("Config updated");
    }

    // Helper methods
    private void storeInDatabase(String productId, String review) {}
    private Object getProductFromDB(String productId) { return null; }
    private byte[] readFile(String path) throws IOException { return new byte[0]; }
    private Object getProductData(String id) { return null; }
    private void processPurchase(String productId, int quantity) {}
    private void extractAndProcess(String zipFile) throws Exception {}
    private void updateProduct(String id) {}
    private Object getUserOrderHistory(String userId) { return null; }
}
