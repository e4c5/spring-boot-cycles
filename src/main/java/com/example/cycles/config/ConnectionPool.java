package com.example.cycles.config;

/**
 * Simple connection pool for demonstrating @Bean cycle.
 */
public class ConnectionPool {

    private final CacheManager cacheManager;

    public ConnectionPool(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public Object getConnection() {
        return "connection";
    }

    public void releaseConnection(Object connection) {
        System.out.println("Releasing connection");
    }
}
