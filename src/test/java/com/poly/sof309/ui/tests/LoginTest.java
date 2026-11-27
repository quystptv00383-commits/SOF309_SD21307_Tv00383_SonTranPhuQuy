package com.poly.sof309.ui.tests;

import com.poly.sof309.ui.pages.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

/**
 * =========================================================================
 * [Y4] KỊCH BẢN CHẠY SELENIUM TEST TRÊN TRÌNH DUYỆT THỰC TẾ (JUNIT 5 - TIMEOUT 3s)
 * Sinh viên: Sơn Trần Phú Quý - MSSV: Tv00383 - Lớp: SD21307
 * =========================================================================
 */
public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    
    private final String BASE_URL = "http://localhost:8080/teapos/login";

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(System.getenv("CI")) || Boolean.getBoolean("headless")) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
        }
        
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Test 01: Kiểm tra giao diện form đăng nhập Tea POS hiển thị chuẩn xác")
    public void testLoginPageDisplay() {
        driver.get(BASE_URL);
        Assertions.assertTrue(driver.getTitle().contains("TEA POS") && loginPage.isBrandTitleDisplayed(),
                "Trang đăng nhập Tea POS phải tải thành công và hiển thị tiêu đề TEA POS SYSTEM");
        Assertions.assertTrue(loginPage.isLoginButtonEnabled(), "Nút đăng nhập phải ở trạng thái sẵn sàng click");
    }

    @Test
    @DisplayName("Test 02: Kiểm tra đăng nhập thất bại khi nhập sai mật khẩu")
    public void testLoginWrongPassword() {
        driver.get(BASE_URL);
        loginPage.login("admin", "wrong_password_999");
        
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("/login"),
                "Đăng nhập sai mật khẩu phải giữ nguyên tại trang đăng nhập /login");
    }

    @Test
    @DisplayName("Test 03: Kiểm tra đăng nhập hợp lệ với tài khoản quản trị (Admin)")
    public void testLoginSuccessUI() {
        driver.get(BASE_URL);
        loginPage.login("admin", "12345678");
        
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("/admin/dashboard"),
                "Đăng nhập Admin hợp lệ phải chuyển hướng thành công tới /admin/dashboard, URL thực tế: " + currentUrl);
    }
}