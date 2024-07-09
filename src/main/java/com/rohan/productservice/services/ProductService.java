package com.rohan.productservice.services;

import com.rohan.productservice.models.Category;
import com.rohan.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public Product getSingleProduct(Long id);

    public Product CreateProduct(String title, String description, String image, String category, double price);

    public List<Product> getAllProducts();

    public List<Category> getAllCategories();

    public Product UpdateProduct(Long id,String title, double price, String description, String image, String category);

}
