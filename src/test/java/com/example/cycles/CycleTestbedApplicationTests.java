package com.example.cycles;

import org.junit.jupiter.api.Test;

/**
 * This test file is intentionally minimal.
 * 
 * The project contains intentional circular dependencies that WILL FAIL at
 * runtime.
 * The purpose is to test the cycle detection tool, which performs static
 * analysis.
 * 
 * Do NOT run SpringBootTest here - it will fail due to:
 * 1. Constructor injection cycle (UserService ↔ NotificationService)
 * 2. @Bean method cycle (CacheManager ↔ ConnectionPool)
 */
class CycleTestbedApplicationTests {

    @Test
    void projectCompiles() {
        // This test just verifies the project compiles
        // The cycle detection tool will analyze the source code statically
    }
}
