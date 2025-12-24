package com.example.cycles.transitive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Transitive cycle C→A: PurchaseService depends on InventoryService.
 * Completes the cycle: Inventory → Supplier → Purchase → Inventory
 */
@Service
public class PurchaseService {

    @Autowired
    private InventoryService inventoryService; // Completes the cycle!

    public void createPurchaseOrder(String productId, int quantity) {
        System.out.println("Creating purchase order for " + quantity + " units of: " + productId);
        // After creating PO, reserve stock
        inventoryService.reserveStock(productId, quantity);
    }

    public void receivePurchaseOrder(String poId) {
        System.out.println("Receiving purchase order: " + poId);
    }
}
