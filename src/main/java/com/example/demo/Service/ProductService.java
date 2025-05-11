package com.example.demo.Service;

import com.example.demo.Entity.Product;
import com.example.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // CREATE: Add a new product
    public ResponseEntity<String> addProduct(Product product) {
        productRepository.save(product);
        return ResponseEntity.ok("Product added successfully.");
    }

    // READ: Get all products
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    // UPDATE: Update an existing product
    public ResponseEntity<String> updateProduct(Product updatedProduct) {
        Optional<Product> optionalProduct = productRepository.findById(updatedProduct.getId());

        if (optionalProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }

        Product product = optionalProduct.get();
        product.setName(updatedProduct.getName());
        product.setCategory(updatedProduct.getCategory());
        product.setLocation(updatedProduct.getLocation());

        productRepository.save(product);
        return ResponseEntity.ok("Product updated successfully.");
    }

    // DELETE: Remove a product
    public ResponseEntity<String> deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }

        productRepository.deleteById(id);
        return ResponseEntity.ok("Product deleted successfully.");
    }
}
