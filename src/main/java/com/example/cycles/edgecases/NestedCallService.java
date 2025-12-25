package com.example.cycles.edgecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * NestedCallService: Tests handling of nested method calls.
 * 
 * This scenario tests if DepSolver correctly tracks nested calls:
 * - Method calls within method calls
 * - Chained method calls
 * - Complex expression trees
 */
@Service
public class NestedCallService {

    @Autowired
    private ChainedService chainedService;

    /**
     * Has nested method calls - tests DepSolver's ability to track them.
     */
    public void processOrder(Long orderId) {
        // Nested call: chainedService.getOrder().getDetails()
        String details = chainedService.getOrder(orderId).getDetails();
        
        // Nested call: chainedService.getOrder().updateStatus()
        chainedService.getOrder(orderId).updateStatus("PROCESSING");
        
        // Chained call: chainedService.process().notify()
        chainedService.process(orderId).notify("Order processed");
        
        System.out.println("Order processed: " + details);
    }
}

