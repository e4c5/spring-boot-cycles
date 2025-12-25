package com.example.cycles.extraction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PaymentProcessingService: Part of cycle with OrderProcessingService.
 * 
 * This class also has methods with complex transitive dependencies:
 * - processPayment() calls orderProcessingService and has helper methods
 * - Uses private fields and helper methods
 * 
 * This creates a bidirectional cycle where both sides have complex methods
 * that need to be extracted.
 */
@Service
public class PaymentProcessingService {

    @Autowired
    private OrderProcessingService orderProcessingService;

    // Field that needs to be moved to mediator
    private String paymentPrefix = "PAY-";
    private double totalProcessed = 0.0;

    /**
     * Cycle-causing method: Calls orderProcessingService and uses helper methods.
     * This method and its dependencies should be extracted to a mediator.
     */
    public void processPayment(String orderNumber, Double amount) {
        String paymentId = generatePaymentId(orderNumber);  // Helper method
        validatePayment(paymentId, amount);  // Another helper
        orderProcessingService.getOrderStatus(orderNumber);
        recordPayment(paymentId, amount);  // Another helper
        updateTotal(amount);  // Uses field
    }

    /**
     * Helper method that uses a field - must be moved with processPayment()
     */
    private String generatePaymentId(String orderNumber) {
        return paymentPrefix + orderNumber;
    }

    /**
     * Helper method - must be moved with processPayment()
     */
    private void validatePayment(String paymentId, Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount for payment: " + paymentId);
        }
        System.out.println("Validated payment: " + paymentId);
    }

    /**
     * Helper method - must be moved with processPayment()
     */
    private void recordPayment(String paymentId, Double amount) {
        System.out.println("Payment recorded: " + paymentId + " - $" + amount);
    }

    /**
     * Helper method that modifies a field - must be moved with processPayment()
     */
    private void updateTotal(Double amount) {
        totalProcessed += amount;
    }

    /**
     * Non-cycle-causing method - should NOT be moved
     */
    public double getTotalProcessed() {
        return totalProcessed;
    }

    /**
     * Non-cycle-causing method - should NOT be moved
     */
    public void refundPayment(String paymentId) {
        System.out.println("Refunding payment: " + paymentId);
    }
}

