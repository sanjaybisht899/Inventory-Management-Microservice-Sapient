package com.example.demo.Controller;

import com.example.demo.Service.SupplierCoordinatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierCoordinatorService supplierCoordinatorService;

    // Endpoint to simulate restocking request to suppliers
    @PostMapping("/coordinateWithSupplier")
    public ResponseEntity<String> coordinateWithSupplier(
            @RequestParam Long productId,
            @RequestParam int requiredQuantity,
            @RequestParam String urgency) {

        // Call the service method to send the restocking request
        String response = supplierCoordinatorService.requestRestock(productId, requiredQuantity, urgency);
        return ResponseEntity.ok(response);
    }

    // Endpoint to update inventory when supplier delivers the stock
    @PostMapping("/updateInventoryUponDelivery")
    public ResponseEntity<String> updateInventoryUponDelivery(
            @RequestParam Long productId,
            @RequestParam int deliveredQuantity) {

        // Call the service method to update inventory
        String response = supplierCoordinatorService.updateInventoryOnSupplierDelivery(productId, deliveredQuantity);
        return ResponseEntity.ok(response);
    }
}
