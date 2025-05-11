package com.example.demo.Service;


import com.example.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RealTimeUpdater {

    public void updateInventory(Long productId, int quantityChange) {
        System.out.println("Real-time update: Product ID " + productId +
                " changed by quantity: " + quantityChange);

        // No changes to product data here!
    }

}
