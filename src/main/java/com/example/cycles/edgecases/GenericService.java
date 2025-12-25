package com.example.cycles.edgecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * GenericService: Tests handling of generic types in dependencies.
 * 
 * This scenario tests if strategies correctly handle:
 * - Generic type parameters in field declarations
 * - Generic method signatures
 * - Type parameter preservation during refactoring
 */
@Service
public class GenericService<T> {

    @Autowired
    private TypedService<String> typedService;  // Generic dependency

    public void processData(T data) {
        String result = typedService.process(data.toString());
        System.out.println("Processed: " + result);
    }

    public T getData() {
        return null;  // Mock
    }
}

