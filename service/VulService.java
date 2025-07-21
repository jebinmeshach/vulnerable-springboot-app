package com.example.demo.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

@Service
public class AnotherVulnerableService {

    private static final String SECRET_KEY = "hardcoded-secret-key";

    public String readFile(String filePath) throws IOException {
        // Path Traversal Vulnerability
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        byte[] data = fis.readAllBytes();
        fis.close();
        return new String(data);
    }

    public String generateWeakToken() {
        // Insecure Randomness
        Random random = new Random();
        return "Token: " + random.nextInt();
    }

    public String hashPassword(String password) {
        // Weak Hashing with MD5 (insecure)
        return DigestUtils.md5Hex(password);
    }

    public String authenticate(String username, String password) {
        // Logging sensitive data (bad practice)
        System.out.println("Authenticating user: " + username + " with password: " + password);
        if ("admin".equals(username) && "admin123".equals(password)) {
            return "Authenticated";
        }
        return "Failed";
    }

    public String getSecretKey() {
        // Sensitive Data Exposure
        return "Secret Key: " + SECRET_KEY;
    }
}
