# 🍵 DỰ ÁN HỆ THỐNG QUẢN LÝ VÀ BÁN HÀNG TRÀ SỮA — TEA POS
### MÔN HỌC: SOF309 — KIỂM THỬ NÂNG CAO | TUẦN 1: NGHIỆM THU DỰ ÁN

---

## 📌 THÔNG TIN SINH VIÊN

* **Lớp:** `SD21307`
* **Mã số sinh viên (MSSV):** `Tv00383`
* **Họ và tên:** `Sơn Trần Phú Quý`
* **Tên Repo chuẩn quy ước:** `SOF309_SD21307_Tv00383_SơnTrầnPhúQuý`
* **Kho lưu trữ GitHub:** [https://github.com/quystptv00383-commits/desktop-tutorial](https://github.com/quystptv00383-commits/desktop-tutorial)

---

## 📁 DANH MỤC SẢN PHẨM NGHIỆM THU TUẦN 1 (08/11/2026)

| STT | Tên sản phẩm nghiệm thu | File tài liệu tương ứng | Trạng thái nghiệm thu |
|:---:|:---|:---|:---:|
| 1 | **Tên Repo chuẩn quy ước** | `SOF309_SD21307_Tv00383_SơnTrầnPhúQuý` | ✅ ĐÃ ĐẶT CHUẨN |
| 2 | **Web chạy mượt trên Local** | Chạy trên Tomcat 11 cổng `http://localhost:8080/` | ✅ 200 OK |
| 3 | **Đặc tả yêu cầu phần mềm** | 📋 [SPECIFICATIONS.md](./SPECIFICATIONS.md) | ✅ HOÀN THÀNH (Y2) |
| 4 | **Kế hoạch kiểm thử (Test Plan)** | 📋 [TEST_PLAN.md](./TEST_PLAN.md) | ✅ HOÀN THÀNH (Y2) |
| 5 | **Kịch bản kiểm thử (Test Cases)**| 📊 [TEST_CASES.md](./TEST_CASES.md) | ✅ 80 TCs (97.5% PASS) |
| 6 | **Báo cáo sửa lỗi CSDL & Code** | 🛠️ [FIXED_BUGS.md](./FIXED_BUGS.md) | ✅ ĐÃ KHẮC PHỤC 100% |
| 7 | **Minh chứng chuỗi Prompt AI** | 📜 [PROMPT_HISTORY.md](./PROMPT_HISTORY.md) & [TONG_HOP_PROMPT.txt](./TONG_HOP_PROMPT.txt) | ✅ 15 PROMPTS |

---

## 🚀 CÁCH KHỞI CHẠY DỰ ÁN TRÊN LOCAL (TOMCAT 11 + SQL SERVER)

### 1. Yêu cầu môi trường
* **JDK:** Java 17 LTS hoặc Java 25
* **Web Server:** Apache Tomcat 11.0.x
* **Database:** Microsoft SQL Server 2019/2022
* **Công cụ:** IntelliJ IDEA Ultimate / Apache Maven 3.9+

### 2. Cấu hình Cơ sở dữ liệu
* Chạy file kịch bản SQL `CSDL.sql` để tạo database `QuanLyQuanCafehetcuu` (19 bảng, 7 sequences, 7 stored procedures, 5 views).
* Cấu hình kết nối trong `src/main/resources/application.properties`:
  ```properties
  db.url=jdbc:sqlserver://localhost:1433;databaseName=QuanLyQuanCafehetcuu;encrypt=true;trustServerCertificate=true;
  db.user=HAKAI
  db.password=14112009
  ```

### 3. Khởi chạy trên IntelliJ IDEA
1. Mở dự án trong IntelliJ IDEA.
2. Chọn Run Configuration: **Tomcat 11.0.211**.
3. Bấm **Run** (`Shift + F10`).
4. Truy cập các đường dẫn:
   * **Cổng khách hàng (Portal):** [`http://localhost:8080/TEA_POS_PROJECT_war/home`](http://localhost:8080/TEA_POS_PROJECT_war/home)
   * **Quản trị & Bán hàng (Login):** [`http://localhost:8080/TEA_POS_PROJECT_war/login`](http://localhost:8080/TEA_POS_PROJECT_war/login)
     * *Quản trị viên (Admin):* `admin` / `12345678`
     * *Thu ngân (Staff):* `staff` / `12345678`

---

## 🏆 KẾT QUẢ KIỂM THỬ TỔNG QUAN

* **Tổng số ca kiểm thử:** 80 Test Cases
* **Số ca kiểm thử đạt (PASS):** **78 / 80 (97.5%)**
* **Số ca cần lưu ý:** 2 (Bảo mật Webhook bypass và nâng cấp hash BCrypt khi production)
* **Xác thực thực tế:** Đã ghi nhận phản hồi HTTP **200 OK** từ máy chủ Tomcat cục bộ.
