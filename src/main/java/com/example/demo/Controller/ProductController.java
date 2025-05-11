package com.example.demo.Controller;

import com.example.demo.Entity.Product;
import com.example.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // CREATE: Add a new product
    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    // READ: Get all products
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return productService.getAllProducts();
    }

    // UPDATE: Update an existing product
    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody Product updatedProduct) {
        return productService.updateProduct(updatedProduct);
    }

    // DELETE: Remove a product
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }
}

