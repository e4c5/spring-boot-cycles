package com.example.cycles.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration cycle: CacheManager bean depends on ConnectionPool,
 *                and ConnectionPool depends on CacheManager.
 * 
 *                This is a "hard" cycle that Spring CANNOT resolve - it will
 *                fail with
 *                BeanCurrentlyInCreationException even in Spring Boot 2.5.
 */
@Configuration
public class AppConfig {

    @Bean
    public CacheManager cacheManager(ConnectionPool connectionPool) {
        return new CacheManager(connectionPool);
    }

    @Bean
    public ConnectionPool connectionPool(CacheManager cacheManager) {
        // This creates a cycle that Spring cannot resolve
        return new ConnectionPool(cacheManager);
    }
}
