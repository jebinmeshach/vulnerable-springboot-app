package com.example.demo.service;

import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.owasp.encoder.Encode;

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
        return "Comment posted: " + Encode.forHtml(comment);
    }

    public String executeCode(String code)  {
       throw new UnsupportedOperationException("Code execution is disabled.");
    }


    public String getConfig() {
        return "Config retrieval is disabled for security reasons.";
    }
}
