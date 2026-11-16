package com.poly.sof309.controllers;

import java.util.List;

/**
 * Controller điều hướng nghiệp vụ Quản lý Thực đơn Món nước (Tea POS).
 * Dự án: Hệ thống quản lý & Bán hàng quán Trà sữa (Tea POS)
 * Sinh viên thực hiện: Sơn Trần Phú Quý - MSSV: Tv00383 - Lớp: SD21307
 */
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

    public List<Product> searchByName(String keyword) {
        return productService.searchByName(keyword);
    }

    public int countActiveProducts() {
        return productService.countActiveProducts();
    }
}