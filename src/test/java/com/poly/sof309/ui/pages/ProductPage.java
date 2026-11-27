package com.poly.sof309.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * =========================================================================
 * [Y4] PAGE OBJECT MODEL (POM) CHO TRANG QUẢN LÝ SẢN PHẨM / THỰC ĐƠN TEA POS
 * Khớp 100% với các phần tử trên san_pham.jsp và sanpham-form.jsp
 * Sinh viên: Sơn Trần Phú Quý - MSSV: Tv00383 - Lớp: SD21307
 * =========================================================================
 */
public class ProductPage extends BasePage {

    // Locator trên san_pham.jsp
    private final By pageTitle = By.xpath("//h3[contains(text(),'Quản Lý Sản Phẩm')]");
    private final By btnAddProduct = By.xpath("//a[contains(@href, 'action=create')]");
    private final By txtSearch = By.id("productSearchInput");
    private final By tableProducts = By.xpath("//table");
    private final By btnFirstDelete = By.cssSelector(".btn-action-delete");
    private final By btnSwalConfirm = By.cssSelector(".swal2-confirm");

    // Locator trên sanpham-form.jsp
    private final By txtProductName = By.id("tenSp");
    private final By btnSave = By.xpath("//form[@id='productForm']//button[@type='submit'] | //button[@type='submit']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl == null || !currentUrl.contains("/sanpham")) {
            return false;
        }
        return isElementDisplayed(pageTitle) && isElementDisplayed(tableProducts);
    }

    public void searchProduct(String keyword) {
        type(txtSearch, keyword);
    }

    public void clickAddNew() {
        click(btnAddProduct);
    }

    public boolean isFormLoaded() {
        return isElementDisplayed(txtProductName);
    }

    public void createProduct(String name) {
        clickAddNew();
        type(txtProductName, name);
        click(btnSave);
    }

    public int getProductRowCount() {
        try {
            return driver.findElements(By.xpath("//table//tbody/tr")).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void deleteFirstMatchingProduct() {
        click(btnFirstDelete);
        if (isElementDisplayed(btnSwalConfirm)) {
            click(btnSwalConfirm);
        }
    }
}