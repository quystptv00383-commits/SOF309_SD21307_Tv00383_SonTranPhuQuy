package com.poly.sof309.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service xử lý nghiệp vụ Quản lý Thực đơn Món nước (Tea POS).
 * Khởi tạo dữ liệu thực tế từ hệ thống quản lý quán trà sữa để phục vụ kiểm thử đơn vị độc lập.
 */
public class ProductService {
    private final List<Product> productList = new ArrayList<>();

    public ProductService() {
        // Khởi tạo thực đơn món nước mẫu thực tế của quán Tea POS
        productList.add(new Product("SP01", "Trà sữa Oolong Sữa Dừa", 35000.0, 50, "ACTIVE", "Trà sữa", "M"));
        productList.add(new Product("SP02", "Trà Đào Cam Sả", 32000.0, 40, "ACTIVE", "Trà trái cây", "M"));
        productList.add(new Product("SP03", "Cà Phê Muối Biển", 29000.0, 35, "ACTIVE", "Cà phê", "M"));
    }

    public Product addProduct(Product product) {
        if (product == null) {
            throw new ValidationException("Sản phẩm không được để trống!");
        }
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new ValidationException("Tên món nước không được để trống!");
        }
        if (product.getPrice() <= 0) {
            throw new ValidationException("Giá sản phẩm phải lớn hơn 0!");
        }
        // Kiểm tra trùng mã sản phẩm
        if (product.getId() != null) {
            boolean exists = productList.stream().anyMatch(p -> p.getId().equalsIgnoreCase(product.getId()));
            if (exists) {
                throw new ValidationException("Mã món nước đã tồn tại trong thực đơn: " + product.getId());
            }
        }
        productList.add(product);
        return product;
    }

    public Product getProductById(String id) {
        if (id == null) return null;
        return productList.stream()
                .filter(p -> p.getId().equalsIgnoreCase(id.trim()))
                .findFirst()
                .orElse(null);
    }

    public boolean deleteProduct(String id) {
        if (id == null) return false;
        return productList.removeIf(p -> p.getId().equalsIgnoreCase(id.trim()));
    }

    public List<Product> listProducts() {
        return new ArrayList<>(productList);
    }

    public List<Product> searchByName(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>(productList);
        }
        String lowerKeyword = keyword.trim().toLowerCase();
        return productList.stream()
                .filter(p -> p.getName().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
    }

    public int countActiveProducts() {
        return (int) productList.stream()
                .filter(p -> "ACTIVE".equalsIgnoreCase(p.getStatus()))
                .count();
    }
}