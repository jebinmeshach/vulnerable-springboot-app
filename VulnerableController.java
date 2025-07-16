package com.example.demo.controller;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.CodeDTO;
import com.example.demo.service.VulnerableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api")
class VulnerableController {

    @Autowired
    private VulnerableService service;

    @GetMapping("/user")
    public String getUser(@RequestParam String username) throws SQLException {
        return service.getUser(username);
    }

    @PostMapping("/comment")
    public String postComment(@RequestBody CommentDTO commentDTO) {
        return service.postComment(commentDTO.getComment());
    }

    @PostMapping("/execute")
    public String executeCode(@RequestBody CodeDTO codeDTO) throws ScriptException, javax.script.ScriptException {
        return service.executeCode(codeDTO.getCode());
    }

    @GetMapping("/config")
    public String getConfig() {
        return service.getConfig();
    }
}