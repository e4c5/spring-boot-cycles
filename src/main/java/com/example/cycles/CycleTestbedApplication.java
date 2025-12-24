package com.example.cycles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 2.5 testbed with intentional circular dependencies.
 * Used to test cycle detection and elimination tools.
 */
@SpringBootApplication
public class CycleTestbedApplication {

    public static void main(String[] args) {
        SpringApplication.run(CycleTestbedApplication.class, args);
    }
}
