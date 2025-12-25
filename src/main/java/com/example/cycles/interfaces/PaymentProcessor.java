package com.example.cycles.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PaymentProcessor: Calls many methods on OrderService.
 * 
 * This class calls 8+ methods on OrderService, making it an ideal candidate
 * for InterfaceExtractionStrategy. The strategy should:
 * 1. Find all methods called on orderService
 * 2. Generate an interface (IOrderService) with those method signatures
 * 3. Make OrderService implement the interface
 * 4. Change the field type to IOrderService
 * 
 * This scenario tests InterfaceExtractionStrategy's ability to:
 * - Identify all method calls on a dependency
 * - Generate a complete interface
 * - Handle multiple method signatures
 */
@Service
public class PaymentProcessor {

    @Autowired
    private OrderService orderService;  // Calls 8+ methods - perfect for interface extraction

    /**
     * Calls multiple methods on orderService - all should be in the interface
     */
    public void processPayment(Long orderId, Double amount) {
        // Method 1: getOrderDetails
        String details = orderService.getOrderDetails(orderId);
        
        // Method 2: updateOrderStatus
        orderService.updateOrderStatus(orderId, "PROCESSING");
        
        // Method 3: addPaymentNote
        orderService.addPaymentNote(orderId, "Payment initiated: $" + amount);
        
        // Method 4: notifyCustomer
        orderService.notifyCustomer(orderId, "Payment processing");
        
        // Method 5: getOrderTotal
        Double total = orderService.getOrderTotal(orderId);
        
        // Method 6: validateOrder
        boolean valid = orderService.validateOrder(orderId);
        
        // Method 7: lockOrder
        orderService.lockOrder(orderId);
        
        // Method 8: getOrderMetadata
        String metadata = orderService.getOrderMetadata(orderId);
        
        System.out.println("Processing payment for order: " + details);
        System.out.println("Order total: " + total);
        System.out.println("Order valid: " + valid);
        System.out.println("Metadata: " + metadata);
    }

    /**
     * Calls more methods - these should also be in the interface
     */
    public void refundPayment(Long orderId) {
        // Method 9: getOrderStatus
        String status = orderService.getOrderStatus(orderId);
        
        // Method 10: cancelOrder
        orderService.cancelOrder(orderId);
        
        System.out.println("Refunding payment for order: " + orderId);
        System.out.println("Previous status: " + status);
    }
}

