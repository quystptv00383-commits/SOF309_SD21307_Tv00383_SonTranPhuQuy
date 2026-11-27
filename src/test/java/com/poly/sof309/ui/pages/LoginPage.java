package com.poly.sof309.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * =========================================================================
 * [Y4] PAGE OBJECT MODEL (POM) CHO TRANG ĐĂNG NHẬP TEA POS
 * Kế thừa BasePage để quản lý Locator và hành động với WebDriverWait chuẩn.
 * Sinh viên: Sơn Trần Phú Quý - MSSV: Tv00383 - Lớp: SD21307
 * =========================================================================
 */
public class LoginPage extends BasePage {

    // 1. Locator trang đăng nhập nhân viên/admin Tea POS
    private final By txtUsername = By.name("username");
    private final By txtPassword = By.name("password");
    private final By btnLogin = By.xpath("//button[@type='submit']");
    private final By alertError = By.cssSelector(".alert-danger, .swal2-error, .text-danger");
    private final By alertSuccess = By.cssSelector(".alert-success, .swal2-success");
    private final By brandTitle = By.xpath("//h4[contains(text(),'TEA POS')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // 2. Hành động nghiệp vụ
    public void enterUsername(String username) {
        type(txtUsername, username);
    }

    public void enterPassword(String password) {
        type(txtPassword, password);
    }

    public void clickLogin() {
        click(btnLogin);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public boolean isBrandTitleDisplayed() {
        return isElementDisplayed(brandTitle);
    }

    public boolean isLoginButtonEnabled() {
        try {
            return driver.findElement(btnLogin).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        try {
            return getText(alertError);
        } catch (Exception e) {
            return "";
        }
    }
}