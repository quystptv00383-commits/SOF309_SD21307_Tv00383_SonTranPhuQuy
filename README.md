# SOF309: KIỂM THỬ NÂNG CAO CÙNG AI
> **Assignment Master Template — FPT Polytechnic**  
> **Giảng viên hướng dẫn:** ThS. Trần Quang Bình (binhtq7 - Thầy Mười Giáo)  
> 📖 [Xem Cẩm nang hướng dẫn chi tiết 6 tuần](./docs/HUONG_DAN_CHI_TIET_6_TUAN.md)

---

## 📌 THÔNG TIN SINH VIÊN

* **Họ và tên:** **Sơn Trần Phú Quý**
* **Mã số sinh viên (MSSV):** **Tv00383**
* **Lớp:** **SD21307**
* **Tên Repo chuẩn:** `SOF309_SD21307_Tv00383_SonTranPhuQuy`
* **Kho lưu trữ GitHub:** [https://github.com/quystptv00383-commits/SOF309_SD21307_Tv00383_SonTranPhuQuy](https://github.com/quystptv00383-commits/SOF309_SD21307_Tv00383_SonTranPhuQuy)
* **Đề tài Web cá nhân:** **Hệ Thống Quản Lý & Điểm Bán Hàng Trà Sữa (Tea POS & CRM Loyalty)**

---

## 📅 TIẾN ĐỘ THỰC HIỆN ASSIGNMENT

| Giai đoạn & Tuần học | Mục tiêu kỹ thuật | Trạng thái nghiệm thu |
|:---|:---|:---:|
| **Tuần 1 (02/11 - 08/11)** | **[Y1] Khởi tạo Repo từ Template, đưa Web cá nhân vào `src/main/` và chạy mượt local (`localhost:8080`)** | ✅ **HOÀN THÀNH (Y1)** |
| **Tuần 2 (09/11 - 15/11)** | [Y3] Viết Unit Test Controller bằng JUnit 5 (`@ParameterizedTest`, `assertThrows`) | ⏳ *Chưa tới thời hạn* |
| **Tuần 3 (16/11 - 22/11)** | [Y4 - P1] Thiết kế khung Selenium Page Object Model (POM) | ⏳ *Chưa tới thời hạn* |
| **Tuần 4 (23/11 - 29/11)** | [Y4 - P2] Nâng cấp TestNG Framework (Groups, Data-Driven, Chụp ảnh lỗi) | ⏳ *Chưa tới thời hạn* |
| **Tuần 5 (30/11 - 06/12)** | [Y5] Cấu hình CI/CD GitHub Actions (`test.yml`) chạy Chrome Headless | ⏳ *Chưa tới thời hạn* |
| **Tuần 6 (07/12 - 10/12)** | [Y6] Hoàn thiện hồ sơ minh chứng AI (Điểm A) & Slide bảo vệ | ⏳ *Chưa tới thời hạn* |

---

## 🚀 HƯỚNG DẪN KHỞI CHẠY WEB LOCAL (Y1)

### 1. Yêu cầu môi trường
* **JDK:** Java 17 LTS (hoặc Java 21/25)
* **Application Server:** Apache Tomcat 11.0.x
* **Cơ sở dữ liệu:** Microsoft SQL Server (Database: `QuanLyQuanCafehetcuu`)
* **IDE:** IntelliJ IDEA Ultimate

### 2. Cấu hình CSDL trong `src/main/resources/application.properties`
```properties
db.url=jdbc:sqlserver://localhost:1433;databaseName=QuanLyQuanCafehetcuu;encrypt=true;trustServerCertificate=true;
db.user=HAKAI
db.password=14112009
```

### 3. Các bước khởi chạy và kiểm tra Web
1. Mở dự án trong IntelliJ IDEA.
2. Chọn Run configuration: **Tomcat 11.0.211**.
3. Bấm **Run** (`Shift + F10`) để triển khai ứng dụng lên máy chủ Tomcat.
4. Truy cập các đường dẫn kiểm tra:
   * **Cổng khách hàng (Portal):** [`http://localhost:8080/TEA_POS_PROJECT_war/home`](http://localhost:8080/TEA_POS_PROJECT_war/home)
   * **Đăng nhập quản trị & POS:** [`http://localhost:8080/TEA_POS_PROJECT_war/login`](http://localhost:8080/TEA_POS_PROJECT_war/login)
     * *Tài khoản Quản trị viên (Admin):* `admin` / `12345678`
     * *Tài khoản Thu ngân (Staff):* `staff` / `12345678`
5. **Kết quả:** Máy chủ Tomcat phản hồi HTTP **200 OK**, giao diện web và các chức năng bán hàng POS, quản lý menu, tài khoản hoạt động mượt mà ở local.