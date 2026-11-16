package com.poly.sof309.controllers;

/**
 * Entity đại diện cho Sản phẩm / Món nước trong hệ thống Tea POS.
 * Dự án: Hệ thống quản lý & Bán hàng quán Trà sữa (Tea POS)
 * Sinh viên thực hiện: Sơn Trần Phú Quý - MSSV: Tv00383 - Lớp: SD21307
 */
public class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;
    private String status;
    private String category;
    private String size;

    public Product() {
    }

    public Product(String id, String name, double price, int quantity, String status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.category = "Trà sữa";
        this.size = "M";
    }

    public Product(String id, String name, double price, int quantity, String status, String category, String size) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.category = category;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}