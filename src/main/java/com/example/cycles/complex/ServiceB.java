package com.example.cycles.complex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ServiceB: Part of cycle A↔B.
 */
@Service
public class ServiceB {

    @Autowired
    private ServiceA serviceA; // Cycle 2: A → B → A

    @Autowired
    private ServiceC serviceC;

    public void performB(String action) {
        System.out.println("ServiceB performing: " + action);
    }

    public void callA() {
        String status = serviceA.getStatusA();
        System.out.println("ServiceA status: " + status);
    }

    public String getStatusB() {
        return "B_READY";
    }
}
