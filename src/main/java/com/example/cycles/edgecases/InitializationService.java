package com.example.cycles.edgecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * InitializationService: Part of cycle with PostConstructService.
 * 
 * Tests @PostConstruct scenario where method extraction is required.
 */
@Service
public class InitializationService {

    @Autowired
    private PostConstructService postConstructService;

    public void setup() {
        System.out.println("InitializationService setup");
    }

    public void performWork() {
        postConstructService.doWork();
        System.out.println("InitializationService performing work");
    }
}

