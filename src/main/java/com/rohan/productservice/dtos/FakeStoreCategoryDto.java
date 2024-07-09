package com.rohan.productservice.dtos;

import com.rohan.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCategoryDto {
    private String categoryName;
    public FakeStoreCategoryDto(String categoryName) {
        this.categoryName = categoryName;
    }
    public Category toCategory(){
        Category category = new Category();
        category.setCategoryName(getCategoryName());
        return category;
    }
}