package com.technoavi.fisheriz.cs.service;

import java.security.*;
import javax.crypto.Cipher;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {

    // VULN 1: Hardcoded credentials
    private static final String ADMIN_PASSWORD = "admin123";
    private static final String DATABASE_PASSWORD = "root@12345";
    private static final String API_KEY = "sk-1234567890abcdef";

    // VULN 2: Weak password hashing
    public String hashPassword(String password) {
        // VULNERABLE - MD5 is cryptographically broken
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 3: Plain text password storage
    private Map<String, String> userPasswords = new HashMap<>();
    public void storeUserPassword(String username, String password) {
        // VULNERABLE - Storing plaintext password
        userPasswords.put(username, password);
    }

    // VULN 4: Weak encryption - hardcoded key
    private static final String ENCRYPTION_KEY = "1234567890123456";  // Weak, hardcoded key
    public String encryptSensitiveData(String data) {
        try {
            // VULNERABLE - Using fixed key, no IV
            Cipher cipher = Cipher.getInstance("AES");
            javax.crypto.spec.SecretKeySpec key = new javax.crypto.spec.SecretKeySpec(
                ENCRYPTION_KEY.getBytes(), 0, 16, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(data.getBytes());
            return javax.xml.bind.DatatypeConverter.printBase64Binary(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 5: DES encryption (deprecated and broken)
    public String encryptWithDES(String data, String key) {
        try {
            // VULNERABLE - DES is cryptographically broken, 56-bit key
            Cipher cipher = Cipher.getInstance("DES");
            javax.crypto.spec.DESKeySpec dks = new javax.crypto.spec.DESKeySpec(key.getBytes());
            javax.crypto.SecretKey secretKey = javax.crypto.KeyFactory.getInstance("DES")
                .generateSecret(dks);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(data.getBytes());
            return encrypted.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 6: No salting in password hash
    public String unsafeHashPassword(String password) {
        try {
            // VULNERABLE - No salt used
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(password.getBytes());
            return java.util.Base64.getEncoder().encodeToString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 7: Weak authentication - equals comparison vulnerable to timing attacks
    public boolean validateToken(String token, String expected) {
        // VULNERABLE - Timing attack possible
        return token.equals(expected);
    }

    // VULN 8: Session fixation vulnerability
    private String sessionToken = "";
    public String createSession(String userId) {
        // VULNERABLE - Uses predictable token
        sessionToken = userId + "_" + System.currentTimeMillis();
        return sessionToken;
    }

    // VULN 9: Credentials in logs
    public void authenticateUser(String username, String password) {
        if (username.equals("admin") && password.equals(ADMIN_PASSWORD)) {
            // VULNERABLE - Logging credentials
            System.out.println("User logged in: " + username + " with password: " + password);
        }
    }

    // VULN 10: LDAP injection
    public boolean authenticateWithLDAP(String username, String password) {
        try {
            // VULNERABLE - User input not escaped
            String query = "(&(uid=" + username + ")(userPassword=" + password + "))";
            // Execute LDAP query with unescaped input
            return executeLDAPQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // VULN 11: JWT with none algorithm
    public String generateWeakJWT(String userId) {
        // VULNERABLE - Example of weak JWT (using pseudo-code)
        String header = javax.xml.bind.DatatypeConverter.printBase64Binary("{\"alg\":\"none\"}".getBytes());
        String payload = javax.xml.bind.DatatypeConverter.printBase64Binary(("{\"userId\":\"" + userId + "\"}").getBytes());
        return header + "." + payload + ".";  // No signature
    }

    // VULN 12: Buffer overflow potential
    public void processProductCode(String code) {
        // VULNERABLE - Fixed size array
        char[] buffer = new char[10];
        code.getChars(0, code.length(), buffer, 0);  // Can overflow
    }

    private boolean executeLDAPQuery(String query) {
        // Placeholder
        return false;
    }

    // VULN 13: Information disclosure - detailed error messages
    public String getProductData(int productId) {
        try {
            // VULNERABLE - Exposes internal details
            if (productId < 0) {
                throw new IllegalArgumentException("ProductId must be positive, received: " + productId);
            }
            // Code...
        } catch (Exception e) {
            throw new RuntimeException("Database connection failed at 192.168.1.100:5432 - " + e.getMessage());
        }
        return null;
    }

    // VULN 14: Credentials in URL
    public void connectToRemoteService(String userId, String password) {
        try {
            // VULNERABLE - Credentials in URL
            String url = "http://api.example.com/products?user=" + userId + "&pass=" + password;
            java.net.URL connection = new java.net.URL(url);
            // Make request
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
