package com.example.cycles.transitive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Transitive cycle B→C: SupplierService depends on PurchaseService.
 */
@Service
public class SupplierService {

    @Autowired
    private PurchaseService purchaseService;

    public void requestRestock(String productId) {
        System.out.println("Requesting restock for: " + productId);
        purchaseService.createPurchaseOrder(productId, 50);
    }

    public String getSupplierInfo(String productId) {
        return "Supplier for " + productId;
    }
}
