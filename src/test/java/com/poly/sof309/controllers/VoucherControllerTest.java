package com.poly.sof309.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * =========================================================================
 * BỘ UNIT TEST CHỨC NĂNG VOUCHER & KHUYẾN MÃI (TEA POS VOUCHER CONTROLLER)
 * Dự án: Hệ thống quản lý & Bán hàng quán Trà sữa (Tea POS)
 * Sinh viên thực hiện: Sơn Trần Phú Quý - MSSV: Tv00383 - Lớp: SD21307
 * Chuẩn kiểm thử: JUnit 5 (Khớp kịch bản TC-KM-001 đến TC-KM-005 trong TEST_CASES.md)
 * Kỹ thuật áp dụng: Parameterized Test, Exception Testing, Timeout Testing
 * =========================================================================
 */
public class VoucherControllerTest {
    private VoucherService voucherService;

    @BeforeEach
    public void setUp() {
        voucherService = new VoucherService();
    }

    @Test
    @DisplayName("TC_KM_01: Áp dụng voucher giảm tiền mặt trực tiếp 15.000đ cho đơn hàng")
    public void testDirectDiscountSuccess() {
        Voucher voucher = voucherService.getVoucherByCode("GIAM15K");
        assertNotNull(voucher);

        double orderTotal = 80000.0; // Đơn 2 ly trà sữa 80.000đ
        double discount = voucherService.calculateDiscount(voucher, orderTotal);

        assertEquals(15000.0, discount, 0.001, "Mức giảm tiền mặt phải là 15.000đ");
    }

    @Test
    @DisplayName("TC_KM_02: Áp dụng voucher giảm 20% có mức giảm tối đa 30.000đ (chặn trần)")
    public void testPercentageDiscountWithCap() {
        Voucher voucher = voucherService.getVoucherByCode("TEA20");
        assertNotNull(voucher);

        // Đơn hàng lớn 200.000đ: 20% = 40.000đ nhưng bị chặn trần maxDiscount 30.000đ
        double orderTotal = 200000.0;
        double discount = voucherService.calculateDiscount(voucher, orderTotal);

        assertEquals(30000.0, discount, 0.001, "Mức giảm phải bị chặn trần tại 30.000đ");
    }

    @Test
    @DisplayName("TC_KM_03: Ném ngoại lệ khi đơn hàng chưa đạt giá trị tối thiểu (50.000đ)")
    public void testMinOrderValueValidationThrowsException() {
        Voucher voucher = voucherService.getVoucherByCode("TEA20");
        assertNotNull(voucher);

        // Đơn hàng chỉ 35.000đ (dưới mức tối thiểu 50.000đ)
        double orderTotal = 35000.0;

        ValidationException ex = assertThrows(ValidationException.class, () -> {
            voucherService.calculateDiscount(voucher, orderTotal);
        });

        assertTrue(ex.getMessage().contains("chưa đạt giá trị tối thiểu"));
    }

    @Test
    @DisplayName("TC_KM_04: Ném ngoại lệ khi voucher đã hết hạn sử dụng")
    public void testExpiredVoucherThrowsException() {
        Voucher voucher = voucherService.getVoucherByCode("EXPIRED");
        assertNotNull(voucher);

        double orderTotal = 100000.0;

        ValidationException ex = assertThrows(ValidationException.class, () -> {
            voucherService.calculateDiscount(voucher, orderTotal);
        });

        assertTrue(ex.getMessage().contains("quá hạn sử dụng"));
    }

    @Test
    @DisplayName("TC_KM_05: Ném ngoại lệ khi voucher đang bị vô hiệu hóa")
    public void testInactiveVoucherThrowsException() {
        Voucher voucher = voucherService.getVoucherByCode("INACTIVE");
        assertNotNull(voucher);

        double orderTotal = 100000.0;

        ValidationException ex = assertThrows(ValidationException.class, () -> {
            voucherService.calculateDiscount(voucher, orderTotal);
        });

        assertTrue(ex.getMessage().contains("ngừng hoạt động"));
    }

    @ParameterizedTest(name = "Đơn {0}đ áp dụng TEA20 (20% max 30k) -> Giảm: {1}đ")
    @CsvSource({
        "60000.0, 12000.0",
        "100000.0, 20000.0",
        "150000.0, 30000.0",
        "250000.0, 30000.0"
    })
    @DisplayName("TC_KM_06: Kiểm thử tham số hóa (@ParameterizedTest) tính toán giảm giá nhiều mức đơn hàng")
    public void testParameterizedVoucherDiscount(double orderTotal, double expectedDiscount) {
        Voucher voucher = voucherService.getVoucherByCode("TEA20");
        double actualDiscount = voucherService.calculateDiscount(voucher, orderTotal);
        assertEquals(expectedDiscount, actualDiscount, 0.001);
    }

    @Test
    @Timeout(value = 3, unit = TimeUnit.SECONDS)
    @DisplayName("TC_KM_07: Kiểm tra thời gian phản hồi (@Timeout) tính toán voucher")
    public void testDiscountCalculationPerformance() {
        Voucher voucher = voucherService.getVoucherByCode("TEA20");
        for (int i = 0; i < 1000; i++) {
            voucherService.calculateDiscount(voucher, 100000.0);
        }
    }
}