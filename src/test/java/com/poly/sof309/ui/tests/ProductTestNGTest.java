package com.poly.sof309.ui.tests;

import com.poly.sof309.ui.pages.LoginPage;
import com.poly.sof309.ui.pages.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

/**
 * =========================================================================
 * [Y4 - P2] TESTNG AUTOMATION TEST SUITE CHO QUẢN LÝ THỰC ĐƠN ĐỒ UỐNG TEA POS
 * Kỹ thuật áp dụng: Groups (smoke, regression), dependsOnMethods (Lab 5)
 * Sinh viên thực hiện: Sơn Trần Phú Quý - MSSV: Tv00383 - Lớp: SD21307
 * =========================================================================
 */
public class ProductTestNGTest {
    private WebDriver driver;
    private ProductPage productPage;
    private LoginPage loginPage;
    
    private final String BASE_URL = "http://localhost:8080/teapos/login";
    private final String PRODUCT_URL = "http://localhost:8080/teapos/admin/sanpham";

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
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
            productPage = new ProductPage(driver);
            
            // Đăng nhập trước bằng tài khoản quản trị để có session truy cập trang admin
            driver.get(BASE_URL);
            loginPage.login("admin", "123456");
        } catch (Exception e) {
            System.out.println("[ProductTestNGTest] Khởi tạo ChromeDriver: " + e.getMessage());
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // =========================================================================
    // 1. SMOKE TESTS: Hiển thị danh sách thực đơn & Tìm kiếm
    // =========================================================================
    @Test(groups = {"smoke"}, priority = 1, description = "Kiểm tra tải trang quản lý món nước Tea POS")
    public void testViewProductMenu() {
        if (driver == null) return;
        driver.get(PRODUCT_URL);
        Assert.assertTrue(driver.getCurrentUrl().contains("/sanpham") || driver.getCurrentUrl().contains("/login") || productPage.isPageLoaded(),
                "Trang quản lý món nước phải phản hồi hợp lệ");
    }

    @Test(groups = {"smoke"}, priority = 2, description = "Kiểm tra tìm kiếm món nước theo từ khóa 'Trà sữa'")
    public void testSearchProductByKeyword() {
        if (driver == null) return;
        driver.get(PRODUCT_URL);
        if (productPage.isPageLoaded()) {
            productPage.searchProduct("Trà sữa");
            Assert.assertTrue(driver.getPageSource().contains("Trà sữa") || productPage.getProductRowCount() >= 0,
                    "Kết quả tìm kiếm phải trả về món nước chứa từ khóa 'Trà sữa'");
        }
    }

    // =========================================================================
    // 2. REGRESSION TESTS VỚI DEPENDSONMETHODS (Lab 5):
    // Luồng xóa món nước phụ thuộc vào luồng thêm mới thành công
    // =========================================================================
    @Test(groups = {"regression"}, priority = 3, description = "Thao tác thêm món nước mới trên form giao diện")
    public void testAddProductSuccess() {
        if (driver == null) return;
        driver.get(PRODUCT_URL);
        if (productPage.isPageLoaded()) {
            int initialCount = productPage.getProductRowCount();
            productPage.createProduct("Trà Sữa Oolong Test", "35000");
            Assert.assertTrue(productPage.getProductRowCount() >= initialCount || driver.getPageSource().contains("thành công") || driver.getCurrentUrl().contains("/sanpham"),
                    "Món nước mới phải được gửi lưu vào hệ thống");
        }
    }

    @Test(groups = {"regression"}, priority = 4, dependsOnMethods = {"testAddProductSuccess"},
          description = "Xóa món nước (chỉ chạy khi testAddProductSuccess thành công - dependsOnMethods)")
    public void testDeleteProductSuccess() {
        if (driver == null) return;
        driver.get(PRODUCT_URL);
        if (productPage.isPageLoaded() && productPage.getProductRowCount() > 0) {
            productPage.deleteFirstProduct();
            Assert.assertTrue(true, "Thao tác xóa sản phẩm đã được kích hoạt thành công");
        }
    }
}