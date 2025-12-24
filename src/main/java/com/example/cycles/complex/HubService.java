package com.example.cycles.complex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Hub service with multiple dependencies, creating multiple overlapping cycles.
 * This pattern tests the edge selection algorithm.
 */
@Service
public class HubService {

    @Autowired
    private ServiceA serviceA;

    @Autowired
    private ServiceB serviceB;

    @Autowired
    private ServiceC serviceC;

    public void orchestrate(String action) {
        System.out.println("Hub orchestrating: " + action);
        serviceA.performA(action);
        serviceB.performB(action);
        serviceC.performC(action);
    }

    public String getHubStatus() {
        return "HUB_ACTIVE";
    }

    public void notifyAll(String message) {
        System.out.println("Hub notifying all: " + message);
    }
}
