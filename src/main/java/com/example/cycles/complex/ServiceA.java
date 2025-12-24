package com.example.cycles.complex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ServiceA: Part of cycle Hub↔A and cycle A↔B.
 */
@Service
public class ServiceA {

    @Autowired
    private HubService hubService; // Cycle 1: Hub → A → Hub

    @Autowired
    private ServiceB serviceB; // Part of cycle A → B → A

    public void performA(String action) {
        System.out.println("ServiceA performing: " + action);
    }

    public void callHub(String message) {
        hubService.notifyAll(message);
    }

    public void callB() {
        serviceB.performB("from A");
    }

    public String getStatusA() {
        return "A_READY";
    }
}
