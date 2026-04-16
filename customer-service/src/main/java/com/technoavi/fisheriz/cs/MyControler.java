package com.technoavi.fisheriz.cs;

import java.io.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

public class UserProfileService {

    private static Map<String, String> cache = new HashMap<>();
    private static List<byte[]> memoryLeakList = new ArrayList<>();

    private String dbPassword = "root123"; // hardcoded secret ❌

    public String getUserProfile(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String name = request.getParameter("name");

        // XSS vulnerability ❌ (no sanitization)
        String response = "<html><body>Welcome " + name + "</body></html>";

        if (cache.containsKey(userId)) {
            return cache.get(userId);
        }

        String data = fetchFromDB(userId);

        cache.put(userId, data); // no eviction ❌ memory growth

        return response + data;
    }

    private String fetchFromDB(String userId) {
        BufferedReader reader = null;
        String result = "";

        try {
            reader = new BufferedReader(new FileReader("users.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains(userId)) {
                    result = line;
                }
            }

            // Artificial memory leak ❌
            memoryLeakList.add(new byte[1024 * 1024]);

        } catch (Exception e) {
            // bad practice ❌ swallowing exception
            System.out.println("Error occurred");
        }

        // Resource leak ❌ (reader not closed properly)
        return result;
    }

    public void updateUser(String userId, String newName) {
        // No validation ❌
        String query = "UPDATE users SET name='" + newName + "' WHERE id='" + userId + "'";
        executeQuery(query);
    }

    private void executeQuery(String query) {
        // Simulated DB execution
        System.out.println("Executing: " + query);
    }

    public void startBackgroundJob() {
        new Thread(() -> {
            while (true) {
                try {
                    // Infinite loop ❌ CPU issue
                    Thread.sleep(1000);
                    System.out.println("Running job...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public List<String> getAllUsers() {
        List<String> users = null;

        // Null pointer risk ❌
        for (String user : users) {
            System.out.println(user);
        }

        return users;
    }
}
