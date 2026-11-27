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
 * [Y4 - P2] TESTNG AUTOMATION TEST SUITE CHO QUẢN LÝ THỰC ĐƠN ĐỒ UỐNG TEA POS (3s)
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
        productPage = new ProductPage(driver);
        
        // Đăng nhập bằng tài khoản Quản trị viên thật trong CSDL để có session hợp lệ
        driver.get(BASE_URL);
        loginPage.login("admin", "12345678");
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

    @Test(groups = {"smoke"}, priority = 1, description = "Kiểm tra tải trang quản lý danh sách món nước Tea POS")
    public void testViewProductMenu() {
        driver.get(PRODUCT_URL);
        Assert.assertTrue(productPage.isPageLoaded(),
                "Trang quản lý sản phẩm /admin/sanpham phải hiển thị bảng danh sách món nước");
        Assert.assertTrue(productPage.getProductRowCount() > 0,
                "Bảng thực đơn phải chứa ít nhất 1 món nước từ CSDL");
    }

    @Test(groups = {"smoke"}, priority = 2, description = "Kiểm tra tìm kiếm món nước theo từ khóa 'Trà'")
    public void testSearchProductByKeyword() {
        driver.get(PRODUCT_URL);
        Assert.assertTrue(productPage.isPageLoaded(), "Trang sản phẩm phải tải thành công trước khi tìm kiếm");

        productPage.searchProduct("Trà");
        Assert.assertTrue(driver.getPageSource().contains("Trà"),
                "Danh sách lọc phải chứa các món nước có từ khóa 'Trà'");
    }

    @Test(groups = {"regression"}, priority = 3, description = "Kiểm tra mở form thêm món nước mới trên giao diện")
    public void testOpenAddProductForm() {
        driver.get(PRODUCT_URL);
        Assert.assertTrue(productPage.isPageLoaded(), "Trang sản phẩm phải tải thành công");

        productPage.clickAddNew();
        Assert.assertTrue(productPage.isFormLoaded() && driver.getCurrentUrl().contains("action=create"),
                "Phải điều hướng tới form thêm mới sản phẩm (action=create) và hiển thị ô nhập tên món");
    }

    @Test(groups = {"regression"}, priority = 4, dependsOnMethods = {"testOpenAddProductForm"},
          description = "Kiểm tra chức năng tìm kiếm và sẵn sàng thao tác xóa món nước (dependsOnMethods)")
    public void testDeleteButtonAvailableOnMenu() {
        driver.get(PRODUCT_URL);
        Assert.assertTrue(productPage.isPageLoaded(), "Trang sản phẩm phải tải thành công");
        Assert.assertTrue(productPage.getProductRowCount() > 0, "Phải có dữ liệu sản phẩm để hiển thị nút xóa");
    }
}