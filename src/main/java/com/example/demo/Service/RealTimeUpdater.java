package com.example.demo.Service;


import com.example.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RealTimeUpdater {

    @Autowired
    private ProductRepository productRepository;

    public void updateInventory(Long productId, int quantityChange) {
        productRepository.findById(productId).ifPresent(product -> {
            product.setQuantity(product.getQuantity() + quantityChange);
            productRepository.save(product);
            System.out.println("Real-time update: Product ID " + productId +
                    " new quantity: " + product.getQuantity());
        });
    }
}
