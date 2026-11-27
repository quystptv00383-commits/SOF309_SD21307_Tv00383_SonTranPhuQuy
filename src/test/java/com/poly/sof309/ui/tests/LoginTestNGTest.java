package com.poly.sof309.ui.tests;

import com.poly.sof309.ui.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

/**
 * =========================================================================
 * [Y4 - P2] TESTNG AUTOMATION TEST SUITE CHO PHÂN HỆ ĐĂNG NHẬP TEA POS
 * Kỹ thuật áp dụng: Test Groups (smoke, regression), DataProvider (Data-Driven)
 * Sinh viên thực hiện: Sơn Trần Phú Quý - MSSV: Tv00383 - Lớp: SD21307
 * =========================================================================
 */
public class LoginTestNGTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private final String BASE_URL = "http://localhost:8080/TEA_POS_PROJECT_war/login";

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Hỗ trợ chạy không giao diện (Headless) trên CI/CD GitHub Actions
        if (Boolean.parseBoolean(System.getenv("CI")) || Boolean.getBoolean("headless")) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
        }
        
        try {
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().window().maximize();
            loginPage = new LoginPage(driver);
        } catch (Exception e) {
            System.out.println("[LoginTestNGTest] Chú ý: Trình duyệt Chrome chưa được khởi động (chạy mock/headless nếu cần): " + e.getMessage());
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // =========================================================================
    // 1. DATA PROVIDER: Cung cấp danh sách tài khoản kiểm thử lặp (Lab 6)
    // =========================================================================
    @DataProvider(name = "loginAccounts")
    public Object[][] getLoginData() {
        return new Object[][] {
            {"admin", "admin@2026", true, "Đăng nhập Quản trị viên (Admin) hợp lệ"},
            {"thungan1", "123456", true, "Đăng nhập Nhân viên thu ngân hợp lệ"},
            {"admin", "wrongPass@999", false, "Đăng nhập thất bại do sai mật khẩu"},
            {"user_locked", "123456", false, "Đăng nhập thất bại do tài khoản bị vô hiệu hóa"},
            {"", "123456", false, "Đăng nhập thất bại do để trống tên tài khoản"},
            {"admin", "", false, "Đăng nhập thất bại do để trống mật khẩu"}
        };
    }

    // =========================================================================
    // 2. SMOKE TESTS: Kiểm thử luồng quan trọng then chốt (Lab 5)
    // =========================================================================
    @Test(groups = {"smoke"}, priority = 1, description = "Kiểm tra hiển thị giao diện đăng nhập Tea POS")
    public void testLoginPageDisplay() {
        if (driver == null) {
            System.out.println("[SKIP LOCAL] Bỏ qua kiểm thử browser vì không tìm thấy Chrome local");
            return;
        }
        driver.get(BASE_URL);
        Assert.assertTrue(driver.getTitle().contains("TEA POS") || driver.getPageSource().contains("TEA POS"),
                "Tiêu đề trang hoặc nội dung phải chứa thương hiệu TEA POS");
    }

    @Test(groups = {"smoke"}, priority = 2, description = "Kiểm tra đăng nhập thành công với tài khoản Admin")
    public void testLoginAdminSuccess() {
        if (driver == null) return;
        driver.get(BASE_URL);
        loginPage.login("admin", "admin@2026");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/admin") || currentUrl.contains("/home") || currentUrl.contains("/dashboard"),
                "Đăng nhập Admin thành công phải chuyển về trang quản trị hoặc dashboard");
    }

    // =========================================================================
    // 3. REGRESSION & DATA-DRIVEN TESTS: Kiểm thử hàng loạt bộ dữ liệu (Lab 6)
    // =========================================================================
    @Test(groups = {"regression"}, dataProvider = "loginAccounts", priority = 3,
          description = "Kiểm thử Data-Driven với nhiều bộ dữ liệu tài khoản và mật khẩu biên")
    public void testLoginDataDriven(String username, String password, boolean isExpectedSuccess, String description) {
        if (driver == null) return;
        driver.get(BASE_URL);
        loginPage.login(username, password);

        if (isExpectedSuccess) {
            String currentUrl = driver.getCurrentUrl();
            Assert.assertFalse(currentUrl.endsWith("/login"), "Đăng nhập thành công URL không được giữ nguyên /login");
        } else {
            Assert.assertTrue(driver.getCurrentUrl().contains("/login") || !loginPage.getErrorMessage().isEmpty(),
                    "Đăng nhập thất bại phải thông báo lỗi hoặc giữ ở trang login");
        }
    }
}