package com.technoavi.fisheriz.cs.service;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.Document;

public class SerializationService {

    // VULN 1: Unsafe Java deserialization (RCE)
    public Object deserializeObject(byte[] data) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            return ois.readObject();  // VULNERABLE - No gadget chain validation
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 2: XXE (XML External Entity) - File disclosure
    public String parseXMLFile(String xmlContent) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // VULNERABLE - XXE not disabled
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new ByteArrayInputStream(xmlContent.getBytes()));
            return doc.getDocumentElement().getTextContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 3: XXE - SSRF attack
    public String loadProductXML(String xmlUrl) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // VULNERABLE - Allows external DTD
            dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlUrl);  // VULNERABLE - URL parsing with XXE
            return doc.getDocumentElement().getTextContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 4: XXE - Billion laughs attack
    public String parseProductConfig(String xmlData) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // VULNERABLE - Entity expansion not limited
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new ByteArrayInputStream(xmlData.getBytes()));
            return doc.getDocumentElement().getTextContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 5: Path Traversal in file upload
    public void uploadProductImage(String filename, InputStream fileContent) {
        try {
            // VULNERABLE - No path validation, allows ../ traversal
            String uploadDir = "/var/www/uploads/";
            String filePath = uploadDir + filename;  // Can be exploited with ../../etc/passwd
            FileOutputStream fos = new FileOutputStream(filePath);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fileContent.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // VULN 6: Zip Slip vulnerability
    public void extractZipFile(String zipPath, String extractPath) {
        try {
            java.util.zip.ZipInputStream zis = new java.util.zip.ZipInputStream(
                new FileInputStream(zipPath));
            java.util.zip.ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                // VULNERABLE - No path traversal check
                String filePath = extractPath + java.io.File.separator + zipEntry.getName();
                FileOutputStream fos = new FileOutputStream(filePath);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
                fos.close();
            }
            zis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // VULN 7: Unsafe file type validation
    public boolean validateImageUpload(String filename) {
        // VULNERABLE - Only checks extension, not actual file content
        return filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".gif");
    }

    // VULN 8: Arbitrary file write
    public void saveProductData(String filename, String data) {
        try {
            // VULNERABLE - No sanitization
            FileWriter fw = new FileWriter("/tmp/" + filename);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // VULN 9: JSON deserialization with arbitrary code
    public Object deserializeJSON(String jsonString) {
        try {
            // VULNERABLE - Using default serialization without validation
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            mapper.enableDefaultTyping();  // VULNERABLE - Enables type polymorphism
            return mapper.readValue(jsonString, Object.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 10: Template injection in serialization
    public String serializeWithTemplate(String template, String data) {
        try {
            // VULNERABLE - Template directly evaluated
            String result = template.replace("${data}", data);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
