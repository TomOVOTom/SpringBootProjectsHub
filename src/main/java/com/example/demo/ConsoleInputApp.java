package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@Component
public class ConsoleInputApp implements CommandLineRunner {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter SQL command (or 'exit' to quit):");
            String sql = scanner.nextLine();
            if ("exit".equalsIgnoreCase(sql)) {
                break;
            }

            if (sql.trim().toLowerCase().startsWith("select")) {
                String result = restTemplate.getForObject("http://localhost:8080/console/query?sql=" + sql, String.class);
                System.out.println(result);
            } else {
                String result = restTemplate.postForObject("http://localhost:8080/console/execute", sql, String.class);
                System.out.println(result);
            }
        }
        scanner.close();
    }
}