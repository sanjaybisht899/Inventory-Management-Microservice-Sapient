package com.example.demo.Service;

import com.example.demo.Entity.Product;
import com.example.demo.Repository.ProductRepository;
import jakarta.persistence.Cacheable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // CREATE: Add a new product
    public ResponseEntity<String> addProduct(Product product) {
        productRepository.save(product);
        return ResponseEntity.ok("Product added successfully.");
    }
    private com.example.demo.Dto.ProductDTO convertToDTO(Product product) {
        log.info("Fetching product with ID: {}", id);

        com.example.demo.Dto.ProductDTO dto = new com.example.demo.Dto.ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setCategory(product.getCategory());
        dto.setQuantity(product.getQuantity());
        dto.setLocation(product.getLocation());
        return dto;
    }
    // READ: Get all products
    @Cacheable("allProducts")
    public List<com.example.demo.Dto.ProductDTO> getAllProducts() {
        log.info("Fetching all products from DB");
        return productRepository.findAll()
                .stream()
                .map(p -> new com.example.demo.Dto.ProductDTO(p.getId(), p.getName(), p.getCategory(), p.getQuantity(), p.getLocation()))
                .collect(Collectors.toList());
    }

    // UPDATE: Update an existing product
    @CacheEvict(value = "products", key = "#productDTO.id")
    public com.example.demo.Dto.ProductDTO updateProduct(com.example.demo.Dto.ProductDTO productDTO) {
        log.info("Updating product ID: {}", productDTO.getId());
        Product product = productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productDTO.getName());
        product.setCategory(productDTO.getCategory());
        product.setQuantity(productDTO.getQuantity());
        product.setLocation(productDTO.getLocation());
        productRepository.save(product);

        return productDTO;
    }


    // DELETE: Remove a product
    public ResponseEntity<String> deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }

        productRepository.deleteById(id);
        return ResponseEntity.ok("Product deleted successfully.");
    }
    @Cacheable(value = "products", key = "#id")
    public com.example.demo.Dto.ProductDTO getProductById(Long id) {
        log.info("Fetching product from DB for ID: {}", id);
        return productRepository.findById(id)
                .map(p -> new com.example.demo.Dto.ProductDTO(p.getId(), p.getName(), p.getCategory(), p.getQuantity(), p.getLocation()))
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
    @Autowired
    private InventoryEventPublisher eventPublisher;

    public ResponseEntity<String> updateInventory(Long productId, int quantityChange, String location) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }

        Product product = optionalProduct.get();

        if (!product.getLocation().equalsIgnoreCase(location)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid location for the product.");
        }

        // Update quantity
        product.setQuantity(product.getQuantity() + quantityChange);
        productRepository.save(product);

        // 🔥 Step 4: Publish inventory update event
        eventPublisher.publishInventoryUpdate(productId, quantityChange);

        return ResponseEntity.ok("Inventory updated successfully. New quantity: " + product.getQuantity());
    }
}
