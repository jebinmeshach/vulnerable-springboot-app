<CODE>
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

    public String executeCode(String code) throws  javax.script.ScriptException {
        //  Avoid using ScriptEngine with user-supplied input entirely.  If absolutely necessary,
        //  consider strict whitelisting/sandboxing approaches which are beyond the scope of this simple fix.
        //  Instead, provide a more specific and safer solution.
        throw new UnsupportedOperationException("Arbitrary code execution is disabled.");
    }


    public String getConfig() {
       // Sensitive data should not be hardcoded.  Retrieve from secure configuration mechanisms.
        throw new UnsupportedOperationException("Retrieving configuration is not supported in this demonstration.");
    }
}