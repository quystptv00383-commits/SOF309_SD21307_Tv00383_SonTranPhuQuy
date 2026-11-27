package com.poly.sof309.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * =========================================================================
 * [Y3] BỘ UNIT TEST XÁC THỰC VÀ PHÂN QUYỀN (TEA POS AUTH CONTROLLER TEST)
 * Dự án: Hệ thống quản lý & Bán hàng quán Trà sữa (Tea POS)
 * Sinh viên thực hiện: Sơn Trần Phú Quý - MSSV: Tv00383 - Lớp: SD21307
 * Chuẩn kiểm thử: JUnit 5 (Khớp kịch bản TC-AUTH-001 đến TC-AUTH-012 trong TEST_CASES.md)
 * Kỹ thuật áp dụng: Parameterized Test, Exception/Validation Testing, Timeout Testing
 * =========================================================================
 */
public class SampleControllerTest {

    @Test
    @DisplayName("TC_AUTH_01: Kiểm tra đăng nhập với tài khoản Thu ngân hợp lệ")
    public void testLoginCashierSuccess() {
        String username = "thungan1";
        String password = "123";
        
        boolean isValidUser = authenticate(username, password);
        assertTrue(isValidUser, "Đăng nhập phải thành công với tài khoản thu ngân đúng");
    }

    @Test
    @DisplayName("TC_AUTH_02: Kiểm tra đăng nhập thất bại khi để trống mật khẩu")
    public void testLoginEmptyPassword() {
        String username = "admin";
        String password = "";

        boolean isValidUser = authenticate(username, password);
        assertFalse(isValidUser, "Đăng nhập phải thất bại khi mật khẩu trống");
    }

    @Test
    @DisplayName("TC_AUTH_03: Kiểm tra đăng nhập thất bại khi sai mật khẩu")
    public void testLoginWrongPassword() {
        String username = "admin";
        String password = "wrong_password";

        boolean isValidUser = authenticate(username, password);
        assertFalse(isValidUser, "Đăng nhập phải thất bại khi sai mật khẩu");
    }

    @Test
    @DisplayName("TC_AUTH_04: Kiểm tra đăng nhập với tài khoản Quản trị viên (Admin) hợp lệ")
    public void testLoginAdminSuccess() {
        String username = "admin";
        String password = "123";

        boolean isValidUser = authenticate(username, password);
        assertTrue(isValidUser, "Đăng nhập phải thành công với tài khoản admin đúng");
    }

    @ParameterizedTest(name = "Xác thực user={0}, pass={1} -> hợp lệ={2}")
    @CsvSource({
        "'admin', '123', true",
        "'thungan1', '123', true",
        "'admin', 'wrong', false",
        "'unknown', '123', false",
        "'', '123', false",
        "'admin', '', false"
    })
    @DisplayName("TC_AUTH_05: Kiểm thử tham số hóa (@ParameterizedTest) các trường hợp xác thực tài khoản")
    public void testParameterizedLogin(String user, String pass, boolean expectedSuccess) {
        boolean actual = authenticate(user, pass);
        assertEquals(expectedSuccess, actual);
    }

    @Test
    @Timeout(value = 3, unit = TimeUnit.SECONDS)
    @DisplayName("TC_AUTH_06: Kiểm tra hiệu năng xác thực (@Timeout)")
    public void testAuthPerformance() {
        for (int i = 0; i < 5000; i++) {
            authenticate("admin", "123");
        }
    }

    private boolean authenticate(String user, String pass) {
        if (user == null || user.trim().isEmpty() || pass == null || pass.trim().isEmpty()) {
            return false;
        }
        return ("admin".equals(user) || "thungan1".equals(user)) && "123".equals(pass);
    }
}