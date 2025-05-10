package com.example.demo.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SupplierCoordinatorService {

    public ResponseEntity<String> requestRestock(Long productId, int quantity, String urgency) {
        String message = "Restock request sent for Product ID " + productId +
                ", Quantity: " + quantity +
                ", Urgency: " + urgency;
        return ResponseEntity.ok(message);
    }
}
