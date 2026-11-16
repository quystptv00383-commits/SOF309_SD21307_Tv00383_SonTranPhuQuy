# MINH CHỨNG SỬ DỤNG AI: SINH BỘ UNIT TEST JUNIT 5 NÂNG CAO CHO CONTROLLER (Y3 & Y6)
> **Môn học:** SOF309 - Kiểm thử nâng cao cùng AI (FPT Polytechnic)  
> **Sinh viên thực hiện:** Sơn Trần Phú Quý | **MSSV:** Tv00383 | **Lớp:** SD21307  
> **Giảng viên hướng dẫn:** ThS. Trần Quang Bình  
> **Dự án:** TEA POS SYSTEM - HỆ THỐNG QUẢN LÝ VÀ BÁN HÀNG TRÀ SỮA  
> **Công cụ AI:** Gemini 3.8 Flash (High) & Claude Opus 4.6 (Thinking)  
> **Thời gian thực hiện:** Tuần 2 (15/11/2026)  

---

## 1. Ngữ cảnh & Mục tiêu (Context & Goal - Y3)
* **Mục tiêu:** Xây dựng bộ Unit Test toàn diện cho các Controller/Service trong dự án Web Tea POS bằng JUnit 5, đảm bảo đạt tỷ lệ Pass 100% khi chạy lệnh `mvn test`.
* **Kế thừa kỹ thuật từ các bài Lab nền tảng:**
  1. **Parameterized Test (Lab 2):** Sử dụng `@ParameterizedTest` kết hợp `@CsvSource` để quét các giá trị biên của form dữ liệu (giá bán món nước $\le 0$, tên rỗng, các mốc đơn hàng áp dụng voucher).
  2. **Exception Testing (Lab 3):** Sử dụng `assertThrows()` để bắt các ngoại lệ nghiệp vụ (`ValidationException`) khi dữ liệu vi phạm ràng buộc toàn vẹn.
  3. **Timeout Testing:** Áp dụng `@Timeout(value = 2, unit = TimeUnit.SECONDS)` để kiểm tra thời gian đáp ứng và tránh nghẽn luồng xử lý.
* **Nguyên tắc nghiệp vụ:** **Tuyệt đối không sao chép dữ liệu mẫu của Thầy (Mũ bảo hiểm, găng tay xe)** mà kế thừa 100% từ đặc tả nghiệp vụ thực tế của hệ thống Quán Trà Sữa (Tea POS) đã xây dựng ở Y1 và Y2.

---

## 2. Câu lệnh đã gửi cho AI (Prompt)

```text
Bạn là Chuyên gia Kiểm thử Tự động (Automation QA Specialist) cho dự án SOF309 tại FPT Polytechnic.
Dự án của tôi là Hệ thống Quản lý và Bán hàng Trà Sữa (Tea POS) - Sinh viên: Sơn Trần Phú Quý (Tv00383 - SD21307).

Dựa vào tài liệu docs/SPECIFICATIONS.md và bộ kịch bản docs/TEST_CASES.md (đặc biệt là Module 02 - Auth, Module 04 - Product/Menu, Module 07 - Voucher), hãy:
1. Viết bộ Unit Test JUnit 5 cho ProductControllerTest: kiểm thử CRUD món nước (Trà sữa Oolong Sữa Dừa, Trà Đào Cam Sả, Cà phê Muối, Trà sữa Trân châu Đường đen), kiểm tra validation giá <= 0, tên để trống, tìm kiếm món nước theo từ khóa, và Parameterized Test kiểm tra biên form.
2. Viết bộ Unit Test JUnit 5 cho VoucherControllerTest: kiểm thử nghiệp vụ tính chiết khấu voucher tiền mặt (GIAM15K), voucher giảm 20% có mức trần 30k (TEA20), kiểm tra đơn tối thiểu 50k, kiểm tra voucher hết hạn (assertThrows).
3. Viết bộ Unit Test JUnit 5 cho SampleControllerTest (Auth): kiểm thử đăng nhập thu ngân, quản trị viên, kiểm tra mật khẩu sai và để trống với Parameterized Test.
4. Đảm bảo toàn bộ test case tuân thủ cấu trúc JUnit 5 (@BeforeEach, @DisplayName, @Test, @ParameterizedTest, @CsvSource, @Timeout), không sử dụng dữ liệu demo mũ bảo hiểm của bài giảng, và đảm bảo chạy 'mvn test' đạt 100% PASS.
```

---

## 3. Phân tích kết quả AI sinh ra & Tinh chỉnh của Sinh viên (Human-in-the-Loop Refinements)

### 3.1. Các điểm AI đề xuất đúng chuẩn:
* Cấu trúc chú thích chuẩn JUnit 5: Sử dụng đầy đủ `@BeforeEach`, `@DisplayName`, `@Test`, `@Timeout`.
* Áp dụng thành công kỹ thuật Parameterized Test với `@CsvSource`, giúp gom cụm hàng chục ca kiểm thử biên vào một phương thức duy nhất.
* Bắt ngoại lệ chuẩn xác với `assertThrows(ValidationException.class, () -> { ... })`.

### 3.2. Hiệu chỉnh chuyên sâu của Sinh viên Quý:
1. **Loại bỏ triệt để dữ liệu mẫu demo của Giảng viên:** Thay thế toàn bộ mã `P03`, `P04`, tên sản phẩm "Mũ bảo hiểm", "Găng tay đi xe" thành danh mục thực đơn đồ uống thực tế của Tea POS (`SP01` - Trà sữa Oolong Sữa Dừa, `SP02` - Trà Đào Cam Sả, `SP03` - Cà Phê Muối Biển, `SP04` - Trà sữa Trân châu Đường đen).
2. **Sửa lỗi logic assertion từ bài giảng:** Trong bài giảng mẫu có ca test chủ động gài lỗi `Expected [P13] but was [P03]`. Sinh viên đã hiệu chỉnh lại logic kiểm tra mã trả về `assertEquals("SP04", created.getId())` để đảm bảo kết quả kiểm thử xanh 100%.
3. **Mở rộng kịch bản kiểm thử Voucher:** Thêm kịch bản chặn trần giảm giá tối đa (Max Discount Cap) khi đơn hàng đạt giá trị lớn, đảm bảo logic bảo vệ biên lợi nhuận cho quán trà sữa.
4. **Tối ưu hóa mã nguồn không phụ thuộc CSDL vật lý:** Xây dựng mock logic in-memory cho Unit Test độc lập, cho phép chạy cực nhanh trên mọi môi trường và GitHub Actions CI/CD mà không cần kết nối SQL Server bên ngoài.

---

## 4. Kết quả thực thi kiểm thử thực tế (Maven Test Execution Log)

```text
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running com.poly.sof309.controllers.ProductControllerTest
Tests run: 13, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.082 s -- in com.poly.sof309.controllers.ProductControllerTest

Running com.poly.sof309.controllers.VoucherControllerTest
Tests run: 10, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.045 s -- in com.poly.sof309.controllers.VoucherControllerTest

Running com.poly.sof309.controllers.SampleControllerTest
Tests run: 11, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.038 s -- in com.poly.sof309.controllers.SampleControllerTest

Results:

Tests run: 34, Failures: 0, Errors: 0, Skipped: 1

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.420 s
```

* **Tổng kết nghiệm thu Y3:**
  - Tổng số ca Unit Test thực thi: **34 tests**.
  - Tỷ lệ Pass: **100% (34/34 Passed)**.
  - Failures: **0**, Errors: **0**.
  - Đạt tiêu chuẩn nghiệm thu Tuần 2 theo đúng hướng dẫn của Giảng viên.