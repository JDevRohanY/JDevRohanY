package com.rohan.productservice.services;

import com.rohan.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public Product getSingleProduct(Long id);

    public Product CreateProduct(String title, String description, String image, String category, double price);

    public List<Product> getAllProducts();
}
