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
        // Do not allow arbitrary JavaScript execution from user input.
        // This method is fundamentally insecure if 'code' is directly from users.
        //  Consider a safer alternative. This example simply rejects user code.
        throw new ScriptException("Arbitrary code execution is disabled for security reasons.");

    }


    public String getConfig() {
        // Avoid hardcoding sensitive information.  Retrieve these values securely,
        // e.g., environment variables or secrets management.
        return "Retrieving sensitive configuration securely.";

    }
}