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

    // Field that needs to be moved to mediator
    private String orderPrefix = "ORD-";

    private int orderCounter = 0;

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
