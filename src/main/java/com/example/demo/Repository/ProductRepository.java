package com.example.demo.Repository;

import com.example.demo.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryAndLocation(String category, String location);
    List<Product> findByCategory(String category);
    List<Product> findByLocation(String location);
    Optional<Product> findById(Long id);
}

