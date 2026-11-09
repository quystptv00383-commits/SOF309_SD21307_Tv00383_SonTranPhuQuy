package com.poly.sof309.controllers;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private final List<Product> productList = new ArrayList<>();

    public ProductService() {
        // Khởi tạo dữ liệu mẫu phục vụ kiểm thử
        productList.add(new Product("P01", "Mũ bảo hiểm nửa đầu", 150000.0, 50, "ACTIVE"));
        productList.add(new Product("P02", "Kính chắn gió", 80000.0, 30, "ACTIVE"));
    }

    public Product addProduct(Product product) {
        if (product == null) {
            throw new ValidationException("Sản phẩm không được để trống!");
        }
        if (product.getPrice() <= 0) {
            throw new ValidationException("Giá sản phẩm phải lớn hơn 0!");
        }
        productList.add(product);
        return product;
    }

    public Product getProductById(String id) {
        return productList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean deleteProduct(String id) {
        return productList.removeIf(p -> p.getId().equals(id));
    }

    public List<Product> listProducts() {
        return new ArrayList<>(productList);
    }
}