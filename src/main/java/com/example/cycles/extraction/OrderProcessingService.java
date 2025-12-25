package com.example.cycles.extraction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * OrderProcessingService: Part of cycle with PaymentProcessingService.
 * 
 * This class has methods with complex transitive dependencies:
 * - placeOrder() calls paymentService and has helper methods
 * - Uses private fields that need to be moved to mediator
 * - Has multiple helper methods that are called by the cycle-causing method
 * 
 * This scenario tests MethodExtractionStrategy's ability to:
 * 1. Identify cycle-causing methods
 * 2. Collect transitive dependencies (helper methods, fields)
 * 3. Move everything to a mediator class
 */
@Service
public class OrderProcessingService {

    @Autowired
    private PaymentProcessingService paymentProcessingService;

    // Field that needs to be moved to mediator
    private String orderPrefix = "ORD-";
    private int orderCounter = 0;

    /**
     * Cycle-causing method: Calls paymentProcessingService and uses helper methods.
     * This method and its dependencies should be extracted to a mediator.
     */
    public void placeOrder(Long orderId, Double amount) {
        String orderNumber = generateOrderNumber(orderId);  // Helper method
        validateOrder(orderNumber, amount);  // Another helper
        paymentProcessingService.processPayment(orderNumber, amount);
        logOrder(orderNumber);  // Another helper
        incrementCounter();  // Uses field
    }

    /**
     * Helper method that uses a field - must be moved with placeOrder()
     */
    private String generateOrderNumber(Long id) {
        return orderPrefix + id + "-" + orderCounter;
    }

    /**
     * Helper method - must be moved with placeOrder()
     */
    private void validateOrder(String orderNumber, Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount for order: " + orderNumber);
        }
        System.out.println("Validated order: " + orderNumber);
    }

    /**
     * Helper method - must be moved with placeOrder()
     */
    private void logOrder(String orderNumber) {
        System.out.println("Order logged: " + orderNumber);
    }

    /**
     * Helper method that modifies a field - must be moved with placeOrder()
     */
    private void incrementCounter() {
        orderCounter++;
    }

    /**
     * Non-cycle-causing method - should NOT be moved
     */
    public String getOrderStatus(String orderNumber) {
        return "PROCESSING";
    }

    /**
     * Non-cycle-causing method - should NOT be moved
     */
    public void cancelOrder(String orderNumber) {
        System.out.println("Cancelling order: " + orderNumber);
    }
}

