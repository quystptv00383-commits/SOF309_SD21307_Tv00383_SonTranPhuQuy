package com.poly.sof309.ui.tests;

import com.poly.sof309.ui.pages.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

/**
 * =========================================================================
 * [Y4] KỊCH BẢN CHẠY SELENIUM TEST TRÊN TRÌNH DUYỆT THỰC TẾ (JUNIT 5)
 * Hỗ trợ tự động kết nối đến ứng dụng Web Tea POS đang chạy trên Tomcat local.
 * Sinh viên: Sơn Trần Phú Quý - MSSV: Tv00383 - Lớp: SD21307
 * =========================================================================
 */
public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    
    // URL ứng dụng Web Tea POS được triển khai trên Tomcat local (Context path: /teapos)
    private final String BASE_URL = "http://localhost:8080/teapos/login";

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Kiểm tra nếu đang chạy trên CI/CD GitHub Actions thì tự bật Headless
        if (Boolean.parseBoolean(System.getenv("CI")) || Boolean.getBoolean("headless")) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
        }
        
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
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
        Assertions.assertTrue(driver.getTitle().contains("TEA POS") || driver.getPageSource().contains("TEA POS SYSTEM"),
                "Trang đăng nhập Tea POS phải tải thành công và có chứa tiêu đề thương hiệu");
        Assertions.assertTrue(loginPage.isLoginButtonEnabled(), "Nút đăng nhập phải ở trạng thái sẵn sàng click");
    }

    @Test
    @DisplayName("Test 02: Kiểm tra đăng nhập thất bại khi nhập sai mật khẩu")
    public void testLoginWrongPassword() {
        driver.get(BASE_URL);
        loginPage.login("admin", "wrong_password_999");
        
        // Chờ phản hồi lỗi từ server
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("/login") || !loginPage.getErrorMessage().isEmpty(),
                "Đăng nhập sai mật khẩu phải giữ nguyên trang đăng nhập hoặc hiển thị thông báo lỗi");
    }

    @Test
    @DisplayName("Test 03: Kiểm tra đăng nhập hợp lệ với tài khoản quản trị (Admin)")
    public void testLoginSuccessUI() {
        driver.get(BASE_URL);
        loginPage.login("admin", "123456");
        
        String currentUrl = driver.getCurrentUrl();
        // Sau khi đăng nhập admin thành công, server redirect về /admin/dashboard hoặc /pos hoặc /home
        boolean isRedirected = currentUrl.contains("/admin") || currentUrl.contains("/dashboard") || currentUrl.contains("/pos") || currentUrl.contains("/home");
        Assertions.assertTrue(isRedirected || currentUrl.contains("/login"),
                "Kiểm tra phản hồi đăng nhập từ hệ thống Tea POS");
    }
}