package com.poly.sof309.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * =========================================================================
 * BỘ UNIT TEST CHỨC NĂNG QUẢN LÝ THỰC ĐƠN MÓN NƯỚC (TEA POS PRODUCT CONTROLLER)
 * Dự án: Hệ thống quản lý & Bán hàng quán Trà sữa (Tea POS)
 * Sinh viên thực hiện: Sơn Trần Phú Quý - MSSV: Tv00383 - Lớp: SD21307
 * Chuẩn kiểm thử: JUnit 5 (Khớp 100% với kịch bản kiểm thử trong docs/TEST_CASES.md)
 * Kỹ thuật áp dụng: Parameterized Test, Exception Testing, Timeout Testing
 * =========================================================================
 */
public class ProductControllerTest {
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        ProductService productService = new ProductService();
        productController = new ProductController(productService);
    }

    @Test
    @DisplayName("TC_PROD_01: Thêm mới món nước trà sữa hợp lệ thành công")
    public void testAddProductSuccess() {
        // Dữ liệu thực tế: Món Trà sữa Trân châu Đường đen (Size L, giá 40.000đ)
        Product newProd = new Product("SP04", "Trà sữa Trân châu Đường đen", 40000.0, 30, "ACTIVE", "Trà sữa", "L");
        Product created = productController.addProduct(newProd);

        assertNotNull(created, "Món nước sau khi thêm không được null");
        assertEquals("SP04", created.getId(), "Mã món nước phải khớp với SP04");
        assertEquals("Trà sữa Trân châu Đường đen", created.getName(), "Tên món nước phải chính xác");
        assertEquals(40000.0, created.getPrice(), 0.001, "Giá bán niêm yết phải là 40.000đ");
        assertEquals("ACTIVE", created.getStatus(), "Trạng thái phục vụ phải là ACTIVE");
    }

    @Test
    @DisplayName("TC_PROD_02: Ném ngoại lệ khi thêm món nước có giá <= 0")
    public void testAddProductInvalidPriceThrowsException() {
        // Kiểm tra validation biên: Giá bán âm (-35.000đ) không hợp lệ
        Product invalidProd = new Product("SP05", "Trà Xanh Macchiato", -35000.0, 10, "ACTIVE");

        ValidationException ex = assertThrows(ValidationException.class, () -> {
            productController.addProduct(invalidProd);
        }, "Phải ném ValidationException khi đơn giá âm hoặc bằng 0");

        assertTrue(ex.getMessage().contains("Giá sản phẩm phải lớn hơn 0"), "Thông báo lỗi phải cảnh báo giá bán");
    }

    @Test
    @DisplayName("TC_PROD_03: Ném ngoại lệ khi tên món nước rỗng hoặc khoảng trắng")
    public void testAddProductEmptyNameThrowsException() {
        Product emptyNameProd = new Product("SP06", "   ", 35000.0, 10, "ACTIVE");

        ValidationException ex = assertThrows(ValidationException.class, () -> {
            productController.addProduct(emptyNameProd);
        }, "Phải ném ValidationException khi tên món nước để trống");

        assertTrue(ex.getMessage().contains("Tên món nước không được để trống"));
    }

    @Test
    @DisplayName("TC_PROD_04: Lấy thông tin món nước theo mã SP01 (Trà sữa Oolong Sữa Dừa)")
    public void testGetProductByIdSuccess() {
        Product prod = productController.getProductById("SP01");
        assertNotNull(prod, "Sản phẩm SP01 phải tồn tại trong menu ban đầu");
        assertEquals("SP01", prod.getId());
        assertEquals("Trà sữa Oolong Sữa Dừa", prod.getName());
        assertEquals(35000.0, prod.getPrice(), 0.001);
    }

    @Test
    @DisplayName("TC_PROD_05: Xóa món nước khỏi thực đơn thành công")
    public void testDeleteProductSuccess() {
        // Xóa món SP02 (Trà Đào Cam Sả)
        boolean deleted = productController.deleteProduct("SP02");
        assertTrue(deleted, "Xóa món nước SP02 phải trả về true");

        // Kiểm tra sau khi xóa thì không còn tìm thấy trong menu
        Product notFound = productController.getProductById("SP02");
        assertNull(notFound, "Món nước SP02 sau khi xóa phải trả về null");
    }

    @Test
    @DisplayName("TC_PROD_06: Lấy danh sách toàn bộ thực đơn đồ uống")
    public void testListProducts() {
        List<Product> products = productController.listProducts();
        assertNotNull(products, "Danh sách thực đơn không được null");
        assertEquals(3, products.size(), "Thực đơn ban đầu phải có đúng 3 món nước");
    }

    @Test
    @DisplayName("TC_PROD_07: Tìm kiếm món nước trà sữa theo từ khóa")
    public void testSearchProductsByName() {
        List<Product> results = productController.searchByName("Trà");
        assertNotNull(results);
        assertEquals(2, results.size(), "Phải tìm thấy 2 món có chữ 'Trà' (Oolong và Đào Cam Sả)");
    }

    @ParameterizedTest(name = "Kiểm thử thêm món: mã={0}, tên={1}, giá={2}đ -> kỳ vọng={3}")
    @CsvSource({
        "'SP10', 'Hồng Trà Sữa Nướng', 38000.0, true",
        "'SP11', 'Trà Vải Hoa Hồng', 36000.0, true",
        "'SP12', '', 30000.0, false",
        "'SP13', 'Trà Sữa Khoai Môn', -15000.0, false",
        "'SP14', 'Trà Sữa Thái Xanh', 0.0, false"
    })
    @DisplayName("TC_PROD_08: Kiểm thử tham số hóa (@ParameterizedTest) với các bộ dữ liệu biên món nước")
    public void testParameterizedAddProduct(String id, String name, double price, boolean expectedValid) {
        Product prod = new Product(id, name, price, 20, "ACTIVE");
        if (expectedValid) {
            Product created = productController.addProduct(prod);
            assertNotNull(created);
            assertEquals(id, created.getId());
        } else {
            assertThrows(ValidationException.class, () -> {
                productController.addProduct(prod);
            });
        }
    }

    @Test
    @Timeout(value = 3, unit = TimeUnit.SECONDS)
    @DisplayName("TC_PROD_09: Kiểm tra thời gian phản hồi (@Timeout) khi tải toàn bộ danh mục thực đơn")
    public void testListProductsTimeout() {
        List<Product> products = productController.listProducts();
        assertNotNull(products);
        assertTrue(products.size() >= 3);
    }
}