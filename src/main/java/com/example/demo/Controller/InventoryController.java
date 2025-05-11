package com.example.demo.Controller;

import com.example.demo.Service.InventoryService;
import com.example.demo.Service.SupplierCoordinatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private SupplierCoordinatorService supplierCoordinatorService;

    @GetMapping("/getInventoryLevels")
    public ResponseEntity<?> getInventoryLevels(
            @RequestParam(required = false) Long productId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String location) {

        return inventoryService.getInventoryLevels(productId, category, location);
    }

    @PostMapping("/updateInventory")
    public ResponseEntity<String> updateInventory(
            @RequestParam Long productId,
            @RequestParam int quantityChange,
            @RequestParam String location) {

        return inventoryService.updateInventory(productId, quantityChange, location);
    }

    @PostMapping("/coordinateWithSupplier")
    public String coordinateWithSupplier(
            @RequestParam Long productId,
            @RequestParam int requiredQuantity,
            @RequestParam String urgency) {

        return supplierCoordinatorService.requestRestock(productId, requiredQuantity, urgency);
    }
}
