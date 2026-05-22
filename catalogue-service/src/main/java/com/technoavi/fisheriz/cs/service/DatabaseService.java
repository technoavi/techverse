package com.technoavi.fisheriz.cs.service;

import java.sql.*;

public class DatabaseService {
    private Connection connection;

    // VULN 1: SQL Injection - Direct string concatenation
    public String getProductById(String productId) {
        String query = "SELECT * FROM products WHERE id = '" + productId + "'";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);  // VULNERABLE
            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 2: SQL Injection in UPDATE statement
    public void updateProductPrice(String productId, String price) {
        String query = "UPDATE products SET price = " + price + " WHERE id = '" + productId + "'";
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);  // VULNERABLE - No input validation
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // VULN 3: SQL Injection in DELETE with user input
    public void deleteProduct(String productName) {
        String query = "DELETE FROM products WHERE name = '" + productName + "'";  // VULNERABLE
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // VULN 4: SQL Injection with ORDER BY
    public ResultSet searchProducts(String sortColumn) {
        String query = "SELECT * FROM products ORDER BY " + sortColumn;  // VULNERABLE
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 5: Second-order SQL Injection
    private String cachedUserInput = "";
    public void storeUserInput(String input) {
        cachedUserInput = input;  // Stores without validation
    }

    public String executeStoredInput() {
        String query = "SELECT * FROM users WHERE email = '" + cachedUserInput + "'";  // VULNERABLE
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) return rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 6: SQL Injection in LIKE clause
    public ResultSet searchProductsByName(String searchTerm) {
        String query = "SELECT * FROM products WHERE name LIKE '%" + searchTerm + "%'";  // VULNERABLE
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 7: Dynamic table name (Table-name-based SQL injection)
    public ResultSet queryTable(String tableName) {
        String query = "SELECT * FROM " + tableName;  // VULNERABLE - No validation
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 8: Union-based SQL Injection
    public String getProductInfo(String id) {
        String query = "SELECT name FROM products WHERE id = " + id + " UNION SELECT password FROM admin";  // VULNERABLE
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) return rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 9: SQL Injection with comment bypass
    public String getProductByCategory(String category) {
        String query = "SELECT * FROM products WHERE category = '" + category + "' --";  // VULNERABLE
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) return rs.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 10: Batch SQL Injection
    public void processBatch(String[] productIds) {
        for (String id : productIds) {
            String query = "SELECT COUNT(*) FROM products WHERE id = '" + id + "'";  // VULNERABLE
            try {
                Statement stmt = connection.createStatement();
                stmt.executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
