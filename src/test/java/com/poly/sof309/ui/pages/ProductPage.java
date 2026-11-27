package com.poly.sof309.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * =========================================================================
 * [Y4] PAGE OBJECT MODEL (POM) CHO TRANG QUẢN LÝ SẢN PHẨM / THỰC ĐƠN TEA POS
 * Sinh viên: Sơn Trần Phú Quý - MSSV: Tv00383 - Lớp: SD21307
 * =========================================================================
 */
public class ProductPage extends BasePage {

    private final By pageTitle = By.xpath("//h3[contains(text(),'Quản Lý Sản Phẩm')]");
    private final By btnAddProduct = By.xpath("//a[contains(@href, 'action=create')]");
    private final By txtSearch = By.name("keyword");
    private final By btnSearch = By.xpath("//button[contains(@class,'btn-search') or @type='submit']");
    private final By tableProducts = By.xpath("//table");
    private final By firstProductName = By.xpath("//table//tbody/tr[1]//td[contains(@class,'product-name') or 2]");
    private final By btnFirstDelete = By.xpath("//table//tbody/tr[1]//a[contains(@href, 'action=delete') or contains(@class,'btn-delete')]");

    private final By txtProductName = By.name("ten_sp");
    private final By txtProductPrice = By.name("gia_ban");
    private final By btnSave = By.xpath("//button[@type='submit']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl == null || !currentUrl.contains("/sanpham")) {
            return false;
        }
        return isElementDisplayed(pageTitle) || isElementDisplayed(tableProducts);
    }

    public void searchProduct(String keyword) {
        type(txtSearch, keyword);
        click(btnSearch);
    }

    public void clickAddNew() {
        click(btnAddProduct);
    }

    public void createProduct(String name, String price) {
        clickAddNew();
        type(txtProductName, name);
        type(txtProductPrice, price);
        click(btnSave);
    }

    public int getProductRowCount() {
        try {
            return driver.findElements(By.xpath("//table//tbody/tr")).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public String getFirstProductName() {
        return getText(firstProductName);
    }

    public void deleteFirstProduct() {
        click(btnFirstDelete);
    }
}