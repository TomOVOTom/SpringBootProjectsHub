package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseTestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/test-db")
    public String testDatabaseConnection() {
        try {
            jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS pet (name VARCHAR(20), sex CHAR(1), death DATE)");
            jdbcTemplate.execute("INSERT INTO pet (name, sex, death) VALUES ('jfjajd', 'f', '1990-03-30')");
            jdbcTemplate.execute("INSERT INTO pet (name, sex, death) VALUES ('fd', '男', '1990-03-30')");
            return "Database connection and data insertion are successful!";
        } catch (Exception e) {
            return "Database connection or data insertion failed: " + e.getMessage();
        }
    }

    @GetMapping("/get-pets")
    public String getPets() {
        try {
            StringBuilder result = new StringBuilder();
            jdbcTemplate.query("SELECT * FROM pet", (rs, rowNum) -> {
                result.append("Name: ").append(rs.getString("name"))
                      .append(", Sex: ").append(rs.getString("sex"))
                      .append(", Death: ").append(rs.getDate("death"))
                      .append("<br>");
                return null;
            });
            return result.toString();
        } catch (Exception e) {
            return "Failed to retrieve data: " + e.getMessage();
        }
    }

    @GetMapping("/update-pet")
    public String updatePet() {
        try {
            jdbcTemplate.execute("UPDATE pet SET name = 'updated_name' WHERE name = 'jfjajd'");
            return "Data update is successful!";
        } catch (Exception e) {
            return "Data update failed: " + e.getMessage();
        }
    }

    @GetMapping("/delete-pet")
    public String deletePet() {
        try {
            jdbcTemplate.execute("DELETE FROM pet WHERE name = 'fd'");
            return "Data deletion is successful!";
        } catch (Exception e) {
            return "Data deletion failed: " + e.getMessage();
        }
    }
}