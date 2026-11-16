package com.poly.sof309.controllers;

import java.time.LocalDate;

/**
 * Entity đại diện cho Phiếu giảm giá / Voucher khuyến mãi trong hệ thống Tea POS.
 * Dự án: Hệ thống quản lý & Bán hàng quán Trà sữa (Tea POS)
 * Sinh viên thực hiện: Sơn Trần Phú Quý - MSSV: Tv00383 - Lớp: SD21307
 */
public class Voucher {
    private String code;
    private String name;
    private int discountType; // 1: Giảm tiền mặt trực tiếp, 2: Giảm theo tỷ lệ %
    private double discountValue; // Số tiền giảm (loại 1) hoặc % giảm (loại 2)
    private double maxDiscount; // Số tiền giảm tối đa (mức trần cho loại 2)
    private double minOrderValue; // Giá trị đơn hàng tối thiểu để áp dụng
    private LocalDate expireDate; // Ngày hết hạn voucher
    private boolean active; // Trạng thái hoạt động

    public Voucher() {
    }

    public Voucher(String code, String name, int discountType, double discountValue, double maxDiscount, double minOrderValue, LocalDate expireDate, boolean active) {
        this.code = code;
        this.name = name;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.maxDiscount = maxDiscount;
        this.minOrderValue = minOrderValue;
        this.expireDate = expireDate;
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscountType() {
        return discountType;
    }

    public void setDiscountType(int discountType) {
        this.discountType = discountType;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public double getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(double maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public double getMinOrderValue() {
        return minOrderValue;
    }

    public void setMinOrderValue(double minOrderValue) {
        this.minOrderValue = minOrderValue;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}