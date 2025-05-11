package com.example.demo.Service;

import com.example.demo.Entity.Product;
import com.example.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierCoordinatorService {

    @Autowired
    private ProductRepository productRepository;

    // Method to send restocking request
    public String requestRestock(Long productId, int quantity, String urgency) {
        // Simulate sending a request to the supplier (log it for now)
        // You can replace this with actual API calls in the future
        System.out.println("Restocking request sent for Product ID " + productId +
                ", Quantity: " + quantity + ", Urgency: " + urgency);

        // Simulate supplier response
        String supplierResponse = "Supplier accepted the request for restocking.";
        return supplierResponse;
    }

    // Method to simulate the supplier response and update inventory
    public String updateInventoryOnSupplierDelivery(Long productId, int deliveredQuantity) {
        // Find the product by ID
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        // Update the product inventory
        product.setQuantity(product.getQuantity() + deliveredQuantity);
        productRepository.save(product);

        return "Inventory updated. New quantity: " + product.getQuantity();
    }
}
