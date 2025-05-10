package com.example.demo.Service;

import com.example.demo.Entity.Product;
import com.example.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RealTimeUpdater realTimeUpdater;

    public ResponseEntity<?> getInventoryLevels(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.ok(optionalProduct.get());
    }

    public ResponseEntity<String> updateInventory(Long productId, int quantityChange, String location) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }

        Product product = optionalProduct.get();

        if (!product.getLocation().equalsIgnoreCase(location)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid location for the product.");
        }

        product.setQuantity(product.getQuantity() + quantityChange);
        productRepository.save(product);

        // Trigger real-time inventory update
        realTimeUpdater.updateInventory(productId, quantityChange);

        return ResponseEntity.ok("Inventory updated successfully. New quantity: " + product.getQuantity());
    }
}
