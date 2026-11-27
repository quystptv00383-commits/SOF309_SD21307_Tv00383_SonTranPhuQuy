package com.poly.sof309.ui.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * =========================================================================
 * [Y4 - P2] TESTNG LISTENER - TỰ ĐỘNG CHỤP ẢNH MÀN HÌNH KHI TEST THẤT BÀI
 * Bắt sự kiện ITestListener lưu screenshot vào thư mục screenshots/ (Lab 8)
 * Dự án: Hệ thống quản lý & Bán hàng quán Trà sữa (Tea POS)
 * Sinh viên thực hiện: Sơn Trần Phú Quý - MSSV: Tv00383 - Lớp: SD21307
 * =========================================================================
 */
public class TestListener implements ITestListener {

    private static final String SCREENSHOT_DIR = "screenshots";

    @Override
    public void onStart(ITestContext context) {
        System.out.println("=======================================================");
        System.out.println("[TEST SUITE START] Bắt đầu thực thi Suite: " + context.getName());
        System.out.println("=======================================================");
        createScreenshotDirectory();
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("=======================================================");
        System.out.println("[TEST SUITE FINISH] Hoàn thành Suite: " + context.getName());
        System.out.println("Passed: " + context.getPassedTests().size() + 
                           " | Failed: " + context.getFailedTests().size() + 
                           " | Skipped: " + context.getSkippedTests().size());
        System.out.println("=======================================================");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("  ▶ [START] " + result.getMethod().getMethodName() + 
                           " (Group: " + String.join(", ", result.getMethod().getGroups()) + ")");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("  ✅ [PASS] " + result.getMethod().getMethodName() + 
                           " (" + (result.getEndMillis() - result.getStartMillis()) + " ms)");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.err.println("  ❌ [FAIL] " + testName + " - Lý do: " + result.getThrowable());

        Object testInstance = result.getInstance();
        WebDriver driver = null;
        try {
            java.lang.reflect.Method getDriverMethod = testInstance.getClass().getMethod("getDriver");
            driver = (WebDriver) getDriverMethod.invoke(testInstance);
        } catch (Exception e) {
            // Không tìm thấy method getDriver
        }

        if (driver != null) {
            captureScreenshot(driver, testName);
        } else {
            System.out.println("  ⚠️ [SCREENSHOT] Không tìm thấy WebDriver để chụp màn hình cho: " + testName);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("  ⏭️ [SKIPPED] " + result.getMethod().getMethodName());
    }

    private void createScreenshotDirectory() {
        try {
            Path path = Paths.get(SCREENSHOT_DIR);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
                System.out.println("[TestListener] Đã khởi tạo thư mục screenshots: " + path.toAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("[TestListener] Không thể tạo thư mục screenshots: " + e.getMessage());
        }
    }

    private void captureScreenshot(WebDriver driver, String testName) {
        try {
            createScreenshotDirectory();
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            Path targetPath = Paths.get(SCREENSHOT_DIR, fileName);

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(srcFile.toPath(), targetPath);
            System.out.println("  📸 [SCREENSHOT SAVED] Đã lưu ảnh chụp lỗi: " + targetPath.toAbsolutePath());
        } catch (Exception e) {
            System.err.println("  ⚠️ [SCREENSHOT ERROR] Lỗi khi chụp màn hình: " + e.getMessage());
        }
    }
}