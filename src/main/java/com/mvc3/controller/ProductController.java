package com.mvc3.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvc3.exception.CreateProductFailedException;
import com.mvc3.model.entity.Product;
import com.mvc3.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<String> getProductById(@PathVariable("productId") Long productId) {
        Product product = productService.getProductById(productId);
            try {
                return ResponseEntity.ok(objectMapper.writeValueAsString(product.getName()));
            } catch (JsonProcessingException e) {
                return ResponseEntity.status(500).body("Error converting product to JSON");
            }
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody String productJson) {
       try {
           Product product = objectMapper.readValue(productJson, Product.class);
           productService.createProduct(product);
           return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
       } catch (JsonProcessingException e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON");
       }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable("productId") Long productId,
                                                @RequestBody String productJson)
    {
        try {
            Product productDetails = objectMapper.readValue(productJson, Product.class);
            productService.updateProduct(productId, productDetails);
            return ResponseEntity.ok("Product updated successfully");
        } catch (JsonProcessingException e) {
            throw new CreateProductFailedException("Create product failed");
        }

    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product deleted successfully");
    }
}
