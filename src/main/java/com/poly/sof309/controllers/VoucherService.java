package com.poly.sof309.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Service xử lý tính toán và kiểm tra tính hợp lệ của Voucher (Tea POS).
 * Khớp với kịch bản kiểm thử Phân hệ Quản lý Voucher & Khuyến mãi (TC-KM-001 đến TC-KM-005).
 */
public class VoucherService {
    private final List<Voucher> voucherList = new ArrayList<>();

    public VoucherService() {
        // Khởi tạo các mã voucher mẫu thực tế của quán Tea POS
        voucherList.add(new Voucher("GIAM15K", "Giảm ngay 15.000đ tiền mặt", 1, 15000.0, 0.0, 40000.0, LocalDate.now().plusMonths(1), true));
        voucherList.add(new Voucher("TEA20", "Giảm 20% tối đa 30.000đ cho đơn từ 50k", 2, 20.0, 30000.0, 50000.0, LocalDate.now().plusMonths(1), true));
        voucherList.add(new Voucher("EXPIRED", "Voucher tri ân khai trương hết hạn", 1, 20000.0, 0.0, 50000.0, LocalDate.now().minusDays(5), true));
        voucherList.add(new Voucher("INACTIVE", "Voucher sự kiện chưa kích hoạt", 1, 10000.0, 0.0, 30000.0, LocalDate.now().plusDays(10), false));
    }

    public double calculateDiscount(Voucher voucher, double orderTotal) {
        if (voucher == null) {
            throw new ValidationException("Voucher không tồn tại!");
        }
        if (!voucher.isActive()) {
            throw new ValidationException("Voucher đang ở trạng thái ngừng hoạt động hoặc đã bị vô hiệu hóa!");
        }
        if (voucher.getExpireDate() != null && voucher.getExpireDate().isBefore(LocalDate.now())) {
            throw new ValidationException("Voucher đã quá hạn sử dụng!");
        }
        if (orderTotal < voucher.getMinOrderValue()) {
            throw new ValidationException("Đơn hàng chưa đạt giá trị tối thiểu " + (long) voucher.getMinOrderValue() + "đ để áp dụng voucher!");
        }

        if (voucher.getDiscountType() == 1) {
            // Loại 1: Giảm tiền mặt trực tiếp (không giảm quá tổng giá trị đơn hàng)
            return Math.min(voucher.getDiscountValue(), orderTotal);
        } else if (voucher.getDiscountType() == 2) {
            // Loại 2: Giảm theo tỷ lệ % có giới hạn mức trần tối đa
            double discount = orderTotal * (voucher.getDiscountValue() / 100.0);
            if (voucher.getMaxDiscount() > 0) {
                discount = Math.min(discount, voucher.getMaxDiscount());
            }
            return Math.min(discount, orderTotal);
        } else {
            throw new ValidationException("Loại voucher không hợp lệ!");
        }
    }

    public Voucher getVoucherByCode(String code) {
        if (code == null) return null;
        return voucherList.stream()
                .filter(v -> v.getCode().equalsIgnoreCase(code.trim()))
                .findFirst()
                .orElse(null);
    }
}