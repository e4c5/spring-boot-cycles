package com.example.cycles.complex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ServiceC: Part of cycle Hub↔C.
 */
@Service
public class ServiceC {

    @Autowired
    private HubService hubService; // Cycle 3: Hub → C → Hub

    public void performC(String action) {
        System.out.println("ServiceC performing: " + action);
    }

    public void callHub() {
        String status = hubService.getHubStatus();
        System.out.println("Hub status: " + status);
    }

    public String getStatusC() {
        return "C_READY";
    }
}
