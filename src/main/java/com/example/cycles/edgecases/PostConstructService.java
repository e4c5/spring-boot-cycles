package com.example.cycles.edgecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

/**
 * PostConstructService: Tests scenarios where @Lazy won't work.
 * 
 * This class has a @PostConstruct method that calls a dependency.
 * @Lazy won't help here because @PostConstruct methods run during initialization.
 * This scenario requires MethodExtractionStrategy to extract the initialization logic.
 */
@Service
public class PostConstructService {

    @Autowired
    private InitializationService initializationService;

    private boolean initialized = false;

    /**
     * This method MUST run during initialization.
     * @Lazy won't work here - need method extraction.
     */
    @PostConstruct
    public void initialize() {
        initializationService.setup();
        initialized = true;
        System.out.println("PostConstructService initialized");
    }

    public void doWork() {
        if (!initialized) {
            throw new IllegalStateException("Service not initialized");
        }
        initializationService.performWork();
    }
}

