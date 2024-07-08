package com.rohan.productservice.dtos;

import com.rohan.productservice.models.Category;
import com.rohan.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String image;
    private String description;
    private String category;
    private double price;

    //It will convert fakeStoreProduct to product
    public Product toProduct(){
        Product product = new Product();
        product.setId(getId());
        product.setTitle(getTitle());
        product.setImageURL(getImage());
        product.setDescription(getDescription());

        Category category = new Category();
        category.setTitle(getTitle());
        product.setCategory(category);

        return product;
    }
}
