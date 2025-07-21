<CODE>
package com.example.demo.service;

import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class VulnerableService {

    @Autowired
    private UserRepository userRepository;

    public String getUser(String username) throws SQLException {
        
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        String query = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return "User: " + rs.getString("username");
                }
            }
        }
        return "User not found";
    }

    public String postComment(String comment) {
       
        return "Comment posted: " + comment;
    }

    public String executeCode(String code) throws ScriptException, javax.script.ScriptException {
       
        throw new UnsupportedOperationException("Arbitrary code execution is disabled due to security risks.");
    }

    public String getConfig() {
       
        return "Config retrieval disabled due to security concerns";
    }
}