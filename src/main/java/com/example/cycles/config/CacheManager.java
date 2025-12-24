package com.example.cycles.config;

/**
 * Simple cache manager for demonstrating @Bean cycle.
 */
public class CacheManager {

    private final ConnectionPool connectionPool;

    public CacheManager(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public void cache(String key, Object value) {
        System.out.println("Caching: " + key);
    }

    public Object get(String key) {
        return "cached-" + key;
    }
}
