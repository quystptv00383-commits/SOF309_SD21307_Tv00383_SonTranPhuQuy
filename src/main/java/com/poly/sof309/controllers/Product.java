package com.poly.sof309.controllers;

public class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;
    private String status;

    public Product() {
    }

    public Product(String id, String name, double price, int quantity, String status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
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
}