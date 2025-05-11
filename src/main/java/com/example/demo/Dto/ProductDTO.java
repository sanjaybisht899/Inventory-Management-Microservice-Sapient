package com.example.demo.Dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String category;
    private int quantity;
    private String location;
}
