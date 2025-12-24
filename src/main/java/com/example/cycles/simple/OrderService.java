package com.example.cycles.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Simple A→B cycle: OrderService depends on PaymentService.
 * PaymentService depends back on OrderService.
 */
@Service
public class OrderService {

    @Autowired
    private PaymentService paymentService;

    public void placeOrder(Long orderId) {
        System.out.println("Placing order: " + orderId);
        paymentService.processPayment(orderId);
    }

    public void updateOrderStatus(Long orderId, String status) {
        System.out.println("Order " + orderId + " status updated to: " + status);
    }

    public String getOrderDetails(Long orderId) {
        return "Order-" + orderId;
    }
}
