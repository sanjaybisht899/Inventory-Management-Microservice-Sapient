package com.example.demo.Service;

import com.example.demo.Dto.ProductDTO;
import com.example.demo.Entity.Product;
import com.example.demo.Repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    public ProductServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setCategory("Test");
        product.setQuantity(10);
        product.setLocation("Warehouse");

        when(productRepository.findAll()).thenReturn(Arrays.asList(product));

        // Act
        List<ProductDTO> products = productService.getAllProducts();

        // Assert
        assertEquals(1, products.size());
        assertEquals("Test Product", products.get(0).getName());
        verify(productRepository, times(1)).findAll();
    }
}
