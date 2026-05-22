package com.technoavi.fisheriz.cs.service;

import java.io.*;

public class CommandExecutor {

    // VULN 1: OS Command Injection
    public String executeSystemCommand(String userInput) {
        try {
            String command = "ls -la " + userInput;  // VULNERABLE - User input directly concatenated
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            return output.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 2: Shell Metacharacter Injection
    public void processFile(String filename) {
        try {
            String cmd = "cat " + filename + " | grep pattern";  // VULNERABLE - Pipes user input
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // VULN 3: Command Injection with pipes and redirects
    public String readUserFile(String userId, String filePath) {
        try {
            // VULNERABLE - User input in file path
            String command = "cat /home/" + userId + "/" + filePath;
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 4: Bash command injection
    public String listProducts(String filter) {
        try {
            // VULNERABLE - Filter directly in bash
            String[] cmd = {"/bin/sh", "-c", "echo 'Products: ' && ls products | grep " + filter};
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            return output.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 5: Command injection with zip/tar
    public void archiveFiles(String sourceDir) {
        try {
            // VULNERABLE - Directory name in command
            String command = "tar -czf archive.tar.gz " + sourceDir;
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // VULN 6: ImageMagick/GraphicsMagick injection
    public void processImage(String imagePath, String outputPath) {
        try {
            // VULNERABLE - Paths concatenated directly
            String command = "convert " + imagePath + " -resize 100x100 " + outputPath;
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // VULN 7: Unsafe use of ProcessBuilder
    public void executeWithArray(String userCommand) {
        try {
            String[] cmdArray = new String[]{"bash", "-c", userCommand};  // VULNERABLE
            Runtime.getRuntime().exec(cmdArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // VULN 8: Script injection through template
    public String generateReport(String reportName) {
        try {
            String scriptPath = "/tmp/" + reportName + ".sh";  // VULNERABLE - Path traversal
            String command = "bash " + scriptPath;
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 9: Parameter pollution
    public void sendEmail(String recipient, String subject) {
        try {
            // VULNERABLE - Parameters not escaped
            String command = "mail -s '" + subject + "' " + recipient;
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // VULN 10: LDAP injection via command
    public void queryCatalog(String productCode) {
        try {
            String command = "ldapsearch -x cn=" + productCode;  // VULNERABLE
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
