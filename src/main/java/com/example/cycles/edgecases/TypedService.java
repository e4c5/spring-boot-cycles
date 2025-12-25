package com.example.cycles.edgecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TypedService: Part of cycle with GenericService.
 * 
 * Tests generic type handling in circular dependencies.
 */
@Service
public class TypedService<T> {

    @Autowired
    private GenericService<String> genericService;  // Generic dependency

    public T process(String input) {
        String data = genericService.getData();
        return (T) ("Processed: " + input + " from " + data);
    }
}

