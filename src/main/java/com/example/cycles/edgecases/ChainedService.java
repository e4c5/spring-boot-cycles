package com.example.cycles.edgecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ChainedService: Part of cycle with NestedCallService.
 * 
 * Tests nested and chained method call tracking.
 */
@Service
public class ChainedService {

    @Autowired
    private NestedCallService nestedCallService;

    public Order getOrder(Long orderId) {
        nestedCallService.processOrder(orderId);
        return new Order(orderId);
    }

    public Processor process(Long orderId) {
        return new Processor(orderId);
    }

    // Inner classes for chaining
    public static class Order {
        private final Long orderId;

        public Order(Long orderId) {
            this.orderId = orderId;
        }

        public String getDetails() {
            return "Order-" + orderId;
        }

        public void updateStatus(String status) {
            System.out.println("Order " + orderId + " status: " + status);
        }
    }

    public static class Processor {
        private final Long orderId;

        public Processor(Long orderId) {
            this.orderId = orderId;
        }

        public void notify(String message) {
            System.out.println("Notification for order " + orderId + ": " + message);
        }
    }
}

