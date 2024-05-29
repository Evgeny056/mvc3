package com.mvc3.service;

import com.mvc3.model.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Long productId);

    Product createProduct(Product product);

    Product updateProduct(Long productId, Product productDetails);

    void deleteProduct(Long productId);
}
