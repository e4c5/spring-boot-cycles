package com.example.cycles.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Simple B→A cycle: PaymentService depends on OrderService.
 * Creates cycle with OrderService.
 */
@Service
public class PaymentService {

    @Autowired
    private OrderService orderService;

    public void processPayment(Long orderId) {
        System.out.println("Processing payment for order: " + orderId);
        // After payment, update order status - creates circular call
        orderService.updateOrderStatus(orderId, "PAID");
    }

    public void refundPayment(Long orderId) {
        String details = orderService.getOrderDetails(orderId);
        System.out.println("Refunding payment for: " + details);
    }
}
