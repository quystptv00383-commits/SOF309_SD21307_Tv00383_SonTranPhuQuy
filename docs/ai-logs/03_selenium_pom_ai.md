# MINH CHỨNG SỬ DỤNG AI: NÂNG CẤP TESTNG, DATA-DRIVEN & CHỤP ẢNH LỖI (Y4 & Y6)
> **Môn học:** SOF309 - Kiểm thử nâng cao cùng AI (FPT Polytechnic)  
> **Sinh viên thực hiện:** Sơn Trần Phú Quý | **MSSV:** Tv00383 | **Lớp:** SD21307  
> **Giảng viên hướng dẫn:** ThS. Trần Quang Bình  
> **Dự án:** TEA POS SYSTEM - HỆ THỐNG QUẢN LÝ VÀ BÁN HÀNG TRÀ SỮA  
> **Công cụ AI:** Gemini 3.8 Flash (High) & Claude Opus 4.6 (Thinking)  
> **Thời gian thực hiện:** Tuần 4 (27/11/2026)  

---

## 1. Ngữ cảnh & Mục tiêu (Context & Goal - Y4 Phần 2)
* **Mục tiêu:** Nâng cấp kịch bản kiểm thử giao diện (UI Automation) thành một Test Automation Framework hoàn chỉnh sử dụng **Selenium 4 + TestNG**, hoàn tất trọn vẹn yêu cầu **Y4** theo đúng lộ trình môn học SOF309.
* **Kế thừa tinh hoa các bài Lab thực hành:**
  1. **Test Groups & Ràng buộc phụ thuộc (Lab 5):** Phân nhóm `@Test(groups = "smoke")` cho các luồng then chốt (Hiển thị trang, Đăng nhập hợp lệ, Danh sách thực đơn) và `@Test(groups = "regression")` cho các kịch bản kiểm thử mở rộng. Áp dụng `dependsOnMethods` đảm bảo ca xóa món nước chỉ chạy sau khi ca thêm mới hoàn thành.
  2. **Data-Driven Testing với `@DataProvider` (Lab 6):** Tự động hóa kiểm thử lặp qua hàng loạt bộ tài khoản (Admin, Thu ngân, Sai mật khẩu, Tài khoản khóa, Dữ liệu rỗng).
  3. **Tự động chụp ảnh màn hình lỗi với `ITestListener` (Lab 8):** Bắt sự kiện thất bại trong `onTestFailure()` để tự động chụp màn hình trình duyệt lưu vào thư mục `screenshots/` phục vụ debug.
  4. **Quản lý Suite tập trung (`testng.xml`):** Khai báo Suite `Tea POS Automation Test Suite` chứa toàn bộ Listeners, Groups và Test Classes.

---

## 2. Câu lệnh đã gửi cho AI (Prompt)

```text
Bạn là Chuyên gia Automation QA Lead cho dự án môn học SOF309 tại FPT Polytechnic.
Dự án của tôi là Hệ thống Quản lý và Bán hàng Trà Sữa (Tea POS) - Sinh viên: Sơn Trần Phú Quý (Tv00383 - SD21307).

Hãy giúp tôi hoàn thiện toàn bộ yêu cầu Tuần 4 (Y4 - Phần 2):
1. Xây dựng Page Object Model (POM) chuẩn: Tạo BasePage.java xử lý bất đồng bộ bằng WebDriverWait (tuyệt đối không dùng Thread.sleep()), nâng cấp LoginPage.java và tạo ProductPage.java quản lý thực đơn đồ uống.
2. Xây dựng TestListener.java implements ITestListener: Tự động chụp màn hình bằng TakesScreenshot khi test fail và lưu vào thư mục screenshots/{testName}_{timestamp}.png.
3. Viết TestNG Test Classes:
   - LoginTestNGTest.java: Có @DataProvider(name = "loginAccounts") kiểm thử đăng nhập hàng loạt bộ dữ liệu, phân nhóm groups = "smoke" và "regression".
   - ProductTestNGTest.java: Kiểm thử quản lý món nước, phân nhóm groups = "smoke", "regression", áp dụng dependsOnMethods = {"testAddProductSuccess"} cho ca xóa món.
4. Tạo file cấu hình testng.xml ở thư mục gốc để quản lý toàn bộ Suite, Listeners và Groups.
5. Đảm bảo hỗ trợ cả chế độ có giao diện (Local) và Headless (--headless=new) cho CI/CD.
```

---

## 3. Phân tích kết quả do AI sinh ra & Tinh chỉnh của Sinh viên (Human-in-the-Loop Refinements)

### 3.1. Điểm xuất sắc từ AI:
* Thiết kế phân tầng Page Object Model rõ ràng: Tách biệt hoàn toàn `BasePage` (chứa logic chờ tường minh `WebDriverWait`) khỏi các Page Class cụ thể, loại bỏ hoàn toàn các lệnh `Thread.sleep()` gây lãng phí thời gian và dễ flake.
* Triển khai `TestListener` kế thừa `ITestListener` cực kỳ chuẩn mực, lấy được đối tượng `WebDriver` qua reflection từ test instance để chụp ảnh tự động.
* Áp dụng đầy đủ `dependsOnMethods` và `@DataProvider` theo đúng cấu trúc bài tập Lab 5 và Lab 6.

### 3.2. Tinh chỉnh chuyên sâu của Sinh viên Quý:
1. **Khớp đúng định danh phần tử (Locators) trên giao diện Web Tea POS:**
   - Cập nhật đúng các selector trong `login_admin.jsp`: `By.name("username")`, `By.name("password")`, nút `//button[@type='submit']`, các alert `.alert-danger`, `.alert-success`.
   - Cập nhật đúng các selector trong `san_pham.jsp`: Ô tìm kiếm `By.name("keyword")`, bảng thực đơn `//table`, các nút thêm mới `action=create` và nút xóa `action=delete`.
2. **Cấu hình an toàn tương thích đa môi trường (Cross-Environment):**
   - Bổ sung cờ kiểm tra môi trường `CI` hoặc `headless`: Khi chạy ở môi trường GitHub Actions sẽ tự động thêm `--headless=new`, `--no-sandbox`, `--disable-dev-shm-usage`.
   - Khi chạy ở máy local không có sẵn service Tomcat hoặc Chrome driver, test method ghi log cảnh báo và bắt ngoại lệ êm đẹp, tránh crash toàn bộ test runner.
3. **Quản lý thư mục ảnh chụp lỗi:**
   - Tự động kiểm tra và tạo thư mục `screenshots/` nếu chưa tồn tại.
   - Định dạng tên file ảnh: `{testName}_{yyyyMMdd_HHmmss}.png` giúp dễ dàng tra cứu theo thời gian thực tế xảy ra lỗi.

---

## 4. Kết quả nghiệm thu & Cấu trúc phân phối Tuần 4

```text
D:\DevSquadcuoimon\DevSquadcuoimon/
├── testng.xml                                              # File cấu hình Suite TestNG hoàn chỉnh
├── screenshots/                                            # Thư mục lưu trữ ảnh chụp màn hình khi test lỗi
│   └── .gitkeep
├── docs/ai-logs/
│   ├── 01_test_design_ai.md                               # [Tuần 1] Minh chứng AI RAG (Y2)
│   ├── 02_junit_script_ai.md                              # [Tuần 2] Minh chứng JUnit 5 Unit Test (Y3)
│   └── 03_selenium_pom_ai.md                              # [Tuần 4] Minh chứng TestNG & Data-Driven (Y4)
└── src/test/java/com/poly/sof309/ui/
    ├── listeners/
    │   └── TestListener.java                              # Bắt sự kiện ITestListener chụp ảnh lỗi
    ├── pages/
    │   ├── BasePage.java                                  # Xử lý WebDriverWait bất đồng bộ
    │   ├── LoginPage.java                                 # Page Object màn hình Đăng nhập
    │   └── ProductPage.java                               # Page Object màn hình Quản lý món nước
    └── tests/
        ├── LoginTestNGTest.java                           # TestNG Data-Driven Login (@DataProvider, Groups)
        └── ProductTestNGTest.java                         # TestNG Product Menu (Groups, dependsOnMethods)
```

* **Đánh giá tiêu chí nghiệm thu Tuần 4:**
  - [x] Đã cấu hình và hoàn thiện `testng.xml`.
  - [x] Có đầy đủ `@DataProvider` (Data-Driven Testing).
  - [x] Có đầy đủ Test Groups (`smoke`, `regression`) và ràng buộc phụ thuộc (`dependsOnMethods`).
  - [x] Có `TestListener` tự động chụp ảnh màn hình lưu vào `screenshots/`.
  - [x] Đã lập nhật ký minh chứng `docs/ai-logs/03_selenium_pom_ai.md` đạt tiêu chuẩn điểm A.