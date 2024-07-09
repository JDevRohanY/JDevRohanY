package com.rohan.productservice.services;

import com.rohan.productservice.dtos.FakeStoreCategoryDto;
import com.rohan.productservice.dtos.FakeStoreProductDto;
import com.rohan.productservice.models.Category;
import com.rohan.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
//U can also use @component in place of service, service is specialized version of component

@Service
public class FakeProductService implements ProductService {
    RestTemplate restTemplate;

    public FakeProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product CreateProduct(String title, String description, String image, String category, double price) {
        //It  will return fakeStoreProduct
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setPrice(price);

        FakeStoreProductDto response = restTemplate
                .postForObject(
                        "https://fakestoreapi.com/products",
                        fakeStoreProductDto,
                        FakeStoreProductDto.class
                );
        assert response != null;
        return response.toProduct();
    }

    @Override
    public Product getSingleProduct(Long id){
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.
                getForEntity(
                        "https://fakestoreapi.com/products/" + id,
                        FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageURL(fakeStoreProductDto.getImage());

        Category category = new Category();
        category.setCategoryName(fakeStoreProductDto.getTitle());
        product.setCategory(category);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        //the second parameter is to in which data type you want to convert to (Jackson will convert that json to ur object)
        //check what the problem is with using List<FakeStoreProductDto>
        FakeStoreProductDto[] response = restTemplate.
                getForObject(
                        "https://fakestoreapi.com/products",
                        FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        assert response != null;
        for(FakeStoreProductDto fakeStoreProductDto:response){
            products.add(fakeStoreProductDto.toProduct());
        }
        return products;
    }

    @Override
    public List<Category> getAllCategories() {
        //The response is not returning me array of object, it is just returning me the array of string that's why we cannot use array of object
        String[] response = restTemplate.getForObject("https://fakestoreapi.com/products/categories",
                String[].class);
        List<Category> categories = new ArrayList<>();
        assert response!=null;
        for(String categoryName: response){
            FakeStoreCategoryDto fakeStoreCategoryDto = new FakeStoreCategoryDto(categoryName);
            categories.add(fakeStoreCategoryDto.toCategory());
        }
        return categories;
    }

    @Override
    public Product UpdateProduct(Long id, String title, double price, String description, String image, String category) {
        //This method of patch is throwing an error of Invalid HTTP method: PATCH
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setCategory(category);

        FakeStoreProductDto response = restTemplate.patchForObject(
                "https://fakestoreapi.com/products/"+ id,
                fakeStoreProductDto,
                FakeStoreProductDto.class);
        assert response != null;
        return response.toProduct();
    }
}
