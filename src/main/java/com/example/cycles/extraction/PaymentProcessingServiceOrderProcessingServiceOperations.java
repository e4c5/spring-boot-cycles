package com.example.cycles.extraction;

@org.springframework.stereotype.Service()
public class PaymentProcessingServiceOrderProcessingServiceOperations {

    private double totalProcessed = 0.0;

    // Field that needs to be moved to mediator
    private String paymentPrefix = "PAY-";

    private int orderCounter = 0;

    // Field that needs to be moved to mediator
    private String orderPrefix = "ORD-";

    /**
     * Helper method that uses a field - must be moved with processPayment()
     */
    public String generatePaymentId(String orderNumber) {
        return paymentPrefix + orderNumber;
    }

    /**
     * Helper method - must be moved with processPayment()
     */
    public void validatePayment(String paymentId, Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount for payment: " + paymentId);
        }
        System.out.println("Validated payment: " + paymentId);
    }

    /**
     * Cycle-causing method: Calls orderProcessingService and uses helper methods.
     * This method and its dependencies should be extracted to a mediator.
     */
    public void processPayment(String orderNumber, Double amount) {
        // Helper method
        String paymentId = generatePaymentId(orderNumber);
        // Another helper
        validatePayment(paymentId, amount);
        orderProcessingService.getOrderStatus(orderNumber);
        // Another helper
        recordPayment(paymentId, amount);
        // Uses field
        updateTotal(amount);
    }

    /**
     * Helper method that modifies a field - must be moved with processPayment()
     */
    public void updateTotal(Double amount) {
        totalProcessed += amount;
    }

    /**
     * Helper method - must be moved with processPayment()
     */
    public void recordPayment(String paymentId, Double amount) {
        System.out.println("Payment recorded: " + paymentId + " - $" + amount);
    }

    /**
     * Helper method that modifies a field - must be moved with placeOrder()
     */
    public void incrementCounter() {
        orderCounter++;
    }

    /**
     * Helper method - must be moved with placeOrder()
     */
    public void validateOrder(String orderNumber, Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount for order: " + orderNumber);
        }
        System.out.println("Validated order: " + orderNumber);
    }

    /**
     * Cycle-causing method: Calls paymentProcessingService and uses helper methods.
     * This method and its dependencies should be extracted to a mediator.
     */
    public void placeOrder(Long orderId, Double amount) {
        // Helper method
        String orderNumber = generateOrderNumber(orderId);
        // Another helper
        validateOrder(orderNumber, amount);
        paymentProcessingService.processPayment(orderNumber, amount);
        // Another helper
        logOrder(orderNumber);
        // Uses field
        incrementCounter();
    }

    /**
     * Helper method - must be moved with placeOrder()
     */
    public void logOrder(String orderNumber) {
        System.out.println("Order logged: " + orderNumber);
    }

    /**
     * Helper method that uses a field - must be moved with placeOrder()
     */
    public String generateOrderNumber(Long id) {
        return orderPrefix + id + "-" + orderCounter;
    }
}
