package com.rohan.productservice.controllers;

import com.rohan.productservice.dtos.CreateProductRequestDto;
import com.rohan.productservice.dtos.FakeStoreProductDto;
import com.rohan.productservice.dtos.UpdateProductRequestDto;
import com.rohan.productservice.models.Category;
import com.rohan.productservice.models.Product;
import com.rohan.productservice.services.ProductService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    ProductService productService;

    //Use dependency injection
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto productRequestDto){
        return productService.CreateProduct(
                productRequestDto.getTitle(),
                productRequestDto.getImage(),
                productRequestDto.getDescription(),
                productRequestDto.getCategory(),
                productRequestDto.getPrice()
        );
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        ResponseEntity<List<Product>> responseEntity = new ResponseEntity<>(productService.getAllProducts(), HttpStatusCode.valueOf(202));
        return responseEntity;
    }
    // Jackson -> It will convert JavaObject to JSON, and vice versa
    @RequestMapping(value = "/products/{id}",method = RequestMethod.GET)
    //@GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getSingleProduct(id);
    }

    @GetMapping("/products/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        ResponseEntity<List<Category>> responseEntity = new ResponseEntity<>(productService.getAllCategories(), HttpStatusCode.valueOf(202));
        return responseEntity;
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PATCH)
    public Product updateProduct(@PathVariable("id") Long id,@RequestBody UpdateProductRequestDto updateProductRequestDto){
        return productService.UpdateProduct(
                id,
                updateProductRequestDto.getTitle(),
                updateProductRequestDto.getPrice(),
                updateProductRequestDto.getImage(),
                updateProductRequestDto.getDescription(),
                updateProductRequestDto.getCategory());
    }
}
