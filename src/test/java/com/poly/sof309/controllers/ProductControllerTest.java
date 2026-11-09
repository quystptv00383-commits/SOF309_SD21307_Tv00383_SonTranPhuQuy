package com.poly.sof309.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * BÀI TEST MINH HỌA KIỂM THỬ ĐƠN VỊ CHO CHỨC NĂNG CRUD SẢN PHẨM (SLIDE 1)
 */
public class ProductControllerTest {
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        ProductService productService = new ProductService();
        productController = new ProductController(productService);
    }

    @Test
    @DisplayName("TC_PROD_01: Thêm mới sản phẩm hợp lệ thành công")
    public void testAddProductSuccess() {
        Product newProd = new Product("P03", "Mũ bảo hiểm Eco Smart", 350000.0, 20, "ACTIVE");
        Product created = productController.addProduct(newProd);

        assertNotNull(created);
        assertEquals("P03", created.getId());
        assertEquals("Mũ bảo hiểm Eco Smart", created.getName());
    }

    @Test
    @DisplayName("TC_PROD_02: Ném ngoại lệ khi thêm sản phẩm có giá <= 0")
    public void testAddProductInvalidPriceThrowsException() {
        Product invalidProd = new Product("P04", "Găng tay đi xe", -50000.0, 10, "ACTIVE");

        ValidationException ex = assertThrows(ValidationException.class, () -> {
            productController.addProduct(invalidProd);
        });
        assertNotNull(ex);
    }

    @Test
    @DisplayName("TC_PROD_03: Lấy thông tin sản phẩm theo ID")
    public void testGetProductByIdSuccess() {
        Product prod = productController.getProductById("P01");
        assertNotNull(prod);
        assertEquals("P01", prod.getId());
    }

    @Test
    @DisplayName("TC_PROD_04: Xóa sản phẩm thành công")
    public void testDeleteProductSuccess() {
        boolean deleted = productController.deleteProduct("P02");
        assertTrue(deleted);
    }

    @Test
    @DisplayName("TC_PROD_05: Lấy danh sách tất cả sản phẩm")
    public void testListProducts() {
        List<Product> products = productController.listProducts();
        assertNotNull(products);
        assertFalse(products.isEmpty());
    }
}