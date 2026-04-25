package com.casonreir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class StatusController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/api/status")
    public Map<String, String> getStatus() {
        Map<String, String> status = new HashMap<>();
        status.put("app", "UP");
        try {
            jdbcTemplate.execute("SELECT 1");
            status.put("database", "CONNECTED");
        } catch (Exception e) {
            status.put("database", "DISCONNECTED: " + e.getMessage());
        }
        return status;
    }
}