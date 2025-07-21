<CODE>
package com.example.demo.service;

import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.script.ScriptException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class VulnerableService {

    @Autowired
    private UserRepository userRepository;

    public String executeCode(String code) throws ScriptException {
        // Remove the use of ScriptEngine entirely as it's unsafe with user-supplied input
        // Instead, implement a safe alternative based on the actual requirement.
        // Example: If the intent was to evaluate simple mathematical expressions:
        try {
            Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
            Matcher matcher = pattern.matcher(code);
            if (matcher.find()) {
                double number = Double.parseDouble(matcher.group());
                return "Executed: " + number;
            } else {
              return "Invalid Input";
            }

        } catch (NumberFormatException e) {
            return "Invalid Input";
        }


    }

    public String getConfig() {
        // Never hardcode sensitive information like passwords or API keys in source code.
        // These should be fetched from secure configuration mechanisms like environment variables
        // or a secrets management service.  This is just a placeholder, replace with your actual mechanism
        String dbPassword = System.getenv("DB_PASSWORD");  // Replace with appropriate method
        String apiKey = System.getenv("API_KEY");          // Replace with appropriate method
        return "DB Password: " + (dbPassword != null ? "********" : "Not configured") +
                ", API Key: " + (apiKey != null ? "********" : "Not configured");
    }
}