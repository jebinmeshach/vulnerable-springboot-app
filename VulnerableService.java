package com.example.demo.service;

import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.sql.*;

@Service
public class VulnerableService {

    @Autowired
    private UserRepository userRepository;

    public String getUser(String username) throws SQLException {
        // SQL Injection Vulnerability
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM users WHERE username = '" + username + "'";
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            return "User: " + rs.getString("username");
        }
        return "User not found";
    }

    public String postComment(String comment) {
        // XSS Vulnerability
        return "Comment posted: " + comment;
    }

    public String executeCode(String code) throws ScriptException, javax.script.ScriptException {
        // Insecure Deserialization / RCE
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        Object result = engine.eval(code);
        return "Executed: " + result;
    }

    public String getConfig() {
        // Sensitive Data Exposure
        return "DB Password: password123, API Key: secret-api-key";
    }
}