package com.example.demo.Config;

import com.example.demo.Entity.Product;
import com.example.demo.Repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        return args -> {
            repository.save(new Product(null, "Laptop", "Electronics", 50, "Warehouse A"));
            repository.save(new Product(null, "T-Shirt", "Clothing", 200, "Warehouse B"));
            repository.save(new Product(null, "Shoes", "Footwear", 100, "Warehouse A"));
        };
    }
}
