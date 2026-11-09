package com.poly.sof309.controllers;

import java.util.List;

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public Product addProduct(Product product) {
        return productService.addProduct(product);
    }

    public Product getProductById(String id) {
        return productService.getProductById(id);
    }

    public boolean deleteProduct(String id) {
        return productService.deleteProduct(id);
    }

    public List<Product> listProducts() {
        return productService.listProducts();
    }
}