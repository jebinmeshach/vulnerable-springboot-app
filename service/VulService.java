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

    public String executeCode(String code) throws ScriptException, javax.script.ScriptException {
       
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        Object result = engine.eval(code);
        return "Executed: " + result;
    }

    public String getConfig() {
        
        return "DB Password: password123, API Key: secret-api-key";
    }
}
