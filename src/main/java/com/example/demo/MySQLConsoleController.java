package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/console")
public class MySQLConsoleController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/execute")
    public String executeSQL(@RequestBody String sql) {
        try {
            jdbcTemplate.execute(sql);
            return "SQL executed successfully!";
        } catch (Exception e) {
            return "Error executing SQL: " + e.getMessage();
        }
    }

    @GetMapping("/query")
    public List<Map<String, Object>> querySQL(@RequestParam String sql) {
        try {
            return jdbcTemplate.queryForList(sql);
        } catch (Exception e) {
            return List.of(Map.of("error", e.getMessage()));
        }
    }
}