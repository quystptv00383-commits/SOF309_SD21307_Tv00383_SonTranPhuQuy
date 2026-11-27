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
 * [Y4 - P2] TESTNG AUTOMATION TEST SUITE CHO PHÂN HỆ ĐĂNG NHẬP TEA POS (3s)
 * Kỹ thuật áp dụng: Test Groups (smoke, regression), DataProvider (Data-Driven)
 * Sinh viên thực hiện: Sơn Trần Phú Quý - MSSV: Tv00383 - Lớp: SD21307
 * =========================================================================
 */
public class LoginTestNGTest {
    private WebDriver driver;
    private LoginPage loginPage;
    
    private final String BASE_URL = "http://localhost:8080/teapos/login";

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
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

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ignored) {
            } finally {
                driver = null;
            }
        }
    }

    @DataProvider(name = "loginAccounts")
    public Object[][] getLoginData() {
        return new Object[][] {
            {"admin", "12345678", true, "Đăng nhập Quản trị viên (Admin) hợp lệ"},
            {"staff", "12345678", true, "Đăng nhập Nhân viên thu ngân (Staff) hợp lệ"},
            {"admin", "wrongPass@999", false, "Đăng nhập thất bại do sai mật khẩu"},
            {"user_locked", "12345678", false, "Đăng nhập thất bại do tài khoản không tồn tại/bị khóa"},
            {"", "12345678", false, "Đăng nhập thất bại do để trống tên tài khoản"},
            {"admin", "", false, "Đăng nhập thất bại do để trống mật khẩu"}
        };
    }

    @Test(groups = {"smoke"}, priority = 1, description = "Kiểm tra hiển thị giao diện đăng nhập Tea POS")
    public void testLoginPageDisplay() {
        driver.get(BASE_URL);
        Assert.assertTrue(driver.getTitle().contains("TEA POS") && loginPage.isBrandTitleDisplayed(),
                "Trang đăng nhập phải hiển thị đúng tiêu đề và logo TEA POS SYSTEM");
    }

    @Test(groups = {"smoke"}, priority = 2, description = "Kiểm tra đăng nhập thành công với tài khoản Admin")
    public void testLoginAdminSuccess() {
        driver.get(BASE_URL);
        loginPage.login("admin", "12345678");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/admin/dashboard"),
                "Đăng nhập Admin hợp lệ phải chuyển về /admin/dashboard, URL thực tế: " + currentUrl);
    }

    @Test(groups = {"regression"}, dataProvider = "loginAccounts", priority = 3,
          description = "Kiểm thử Data-Driven với nhiều bộ dữ liệu tài khoản và mật khẩu biên")
    public void testLoginDataDriven(String username, String password, boolean isExpectedSuccess, String description) {
        driver.get(BASE_URL);
        loginPage.login(username, password);

        String currentUrl = driver.getCurrentUrl();
        if (isExpectedSuccess) {
            Assert.assertTrue(currentUrl.contains("/admin/dashboard") || currentUrl.contains("/pos"),
                    "[" + description + "] Đăng nhập thành công phải rời trang /login, URL: " + currentUrl);
        } else {
            Assert.assertTrue(currentUrl.contains("/login"),
                    "[" + description + "] Đăng nhập thất bại phải ở lại trang /login, URL: " + currentUrl);
        }
    }
}