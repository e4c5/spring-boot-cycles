package com.example.cycles.transitive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Transitive cycle A→B: InventoryService depends on SupplierService.
 */
@Service
public class InventoryService {

    @Autowired
    private SupplierService supplierService;

    public void checkStock(String productId) {
        System.out.println("Checking stock for: " + productId);
        supplierService.requestRestock(productId);
    }

    public void reserveStock(String productId, int quantity) {
        System.out.println("Reserving " + quantity + " units of: " + productId);
    }

    public int getAvailableStock(String productId) {
        return 100; // Mock value
    }
}
