# SOF309: KIỂM THỬ NÂNG CAO CÙNG AI
> **Assignment Master Template — FPT Polytechnic**  
> **Giảng viên hướng dẫn:** ThS. Trần Quang Bình (binhtq7 - Thầy Mười Giáo)  
> 📖 [Xem Cẩm nang hướng dẫn chi tiết 6 tuần](./docs/HUONG_DAN_CHI_TIET_6_TUAN.md)

---

## 📌 THÔNG TIN SINH VIÊN & ĐỀ TÀI

* **Họ và tên:** **Sơn Trần Phú Quý**
* **Mã số sinh viên (MSSV):** **Tv00383**
* **Lớp:** **SD21307**
* **Tên Repo chuẩn:** `SOF309_SD21307_Tv00383_SonTranPhuQuy`
* **Link Repo GitHub:** [https://github.com/quystptv00383-commits/SOF309_SD21307_Tv00383_SonTranPhuQuy](https://github.com/quystptv00383-commits/SOF309_SD21307_Tv00383_SonTranPhuQuy)
* **Đề tài Web cá nhân:** **Hệ Thống Quản Lý & Điểm Bán Hàng Trà Sữa (Tea POS & CRM Loyalty)**
* **URL chạy Local (Y1):** `http://localhost:8080/TEA_POS_PROJECT_war/home`

---

## 🏆 KẾT QUẢ NGHIỆM THU TUẦN 1 (08/11/2026)

| Tiêu chí | Sản phẩm bàn giao | Đường dẫn tài liệu | Trạng thái |
|:---:|:---|:---|:---:|
| **Tên Repo chuẩn** | Đúng quy chuẩn `SOF309_<LỚP>_<MSSV>_<TÊN>` | [Link Repo](https://github.com/quystptv00383-commits/SOF309_SD21307_Tv00383_SonTranPhuQuy) | ✅ **HOÀN THÀNH** |
| **Y1: Web chạy local** | Chạy trên Tomcat 11 cổng 8080 | [Hướng dẫn khởi chạy](#-hướng-dẫn-khởi-chạy-web-local-y1) | ✅ **200 OK** |
| **Y2: Đặc tả nghiệp vụ** | Đặc tả 13 module, 19 bảng DB, endpoints | 📋 [docs/SPECIFICATIONS.md](./docs/SPECIFICATIONS.md) | ✅ **HOÀN THÀNH** |
| **Y2: Kế hoạch kiểm thử** | Kế hoạch kiểm thử tổng thể (Test Plan) | 📋 [docs/TEST_PLAN.md](./docs/TEST_PLAN.md) | ✅ **HOÀN THÀNH** |
| **Y2: Kịch bản kiểm thử** | 80 Test Cases chi tiết (Positive & Negative) | 📊 [docs/TEST_CASES.md](./docs/TEST_CASES.md) | ✅ **78/80 PASS (97.5%)** |
| **Y3 & Y4: Khung Test mẫu** | JUnit 5 (`SampleControllerTest`) & Selenium POM | `src/test/java/com/poly/sof309/` | ✅ **BUILD SUCCESS** |
| **Y5: CI/CD Pipeline** | GitHub Actions tự động chạy `mvn clean test` | [.github/workflows/test.yml](./.github/workflows/test.yml) | ✅ **PASS** |
| **Y6: Minh chứng AI (Điểm A)** | Nhật ký Prompt, sinh Test Plan/Case & Debug | 📜 [docs/ai-logs/](./docs/ai-logs/) | ✅ **ĐỦ MINH CHỨNG** |

---

## 📁 CẤU TRÚC THƯ MỤC DỰ ÁN (ĐÚNG QUY CHUẨN)

```text
SOF309_SD21307_Tv00383_SonTranPhuQuy/
├── pom.xml                               # Đã tích hợp JUnit 5, Selenium 4, Tomcat & SQL Server
├── .github/
│   └── workflows/
│       └── test.yml                     # [Y5] Tự động chạy test CI/CD khi push code
├── docs/                                # [Y2 & Y6] Tài liệu & Minh chứng AI
│   ├── HUONG_DAN_CHI_TIET_6_TUAN.md     # Cẩm nang hướng dẫn chi tiết 6 tuần của Giảng viên
│   ├── SPECIFICATIONS.md                # [Y1] Đặc tả nghiệp vụ Web Tea POS
│   ├── TEST_PLAN.md                     # [Y2] Kế hoạch kiểm thử toàn diện
│   ├── TEST_CASES.md                    # [Y2] Bộ 80 kịch bản Test Case chi tiết
│   ├── FIXED_BUGS.md                    # Báo cáo các file lỗi đã được sửa & Root Cause
│   └── ai-logs/                         # [Y6] Minh chứng tương tác AI (Điểm A)
│       ├── README.md                    # Hướng dẫn ghi chép minh chứng AI
│       ├── 01_test_design_ai.md         # Minh chứng AI RAG sinh Test Plan & Test Cases
│       ├── 04_debug_logs_ai.md          # Minh chứng AI debug lỗi runtime SQL & Tomcat
│       ├── PROMPT_HISTORY.md            # Toàn bộ lịch sử prompt và model AI qua các phiên
│       └── TONG_HOP_PROMPT.txt          # Bản văn bản tổng hợp prompt
├── src/
│   ├── main/                            # [Y1] Mã nguồn Web Tea POS của sinh viên
│   │   ├── java/                        # 113 file mã nguồn Java (Controller, Filter, DAO, Model, Config)
│   │   ├── resources/                   # application.properties (SQL Server Connection)
│   │   └── webapp/                      # Giao diện JSP, CSS, JS, Dashboard, POS
│   └── test/java/com/poly/sof309/
│       ├── controllers/                 # [Y3] Unit Test cho Controller bằng JUnit 5
│       │   └── SampleControllerTest.java
│       └── ui/                          # [Y4] Selenium UI Test (Page Object Model)
│           ├── pages/                   # Các class Page Object (LoginPage.java...)
│           │   └── LoginPage.java
│           └── tests/                   # Kịch bản test chạy trên trình duyệt (Data-Driven, Groups)
│               └── LoginTest.java
├── screenshots/                         # [Y4 & Y5] Thư mục tự động lưu ảnh chụp khi test fail
├── .gitignore
└── README.md
```

---

## 🚀 HƯỚNG DẪN KHỞI CHẠY WEB LOCAL (Y1)

### 1. Yêu cầu môi trường
* **JDK:** Java 17 LTS (hoặc Java 21/25)
* **Application Server:** Apache Tomcat 11.0.x
* **Cơ sở dữ liệu:** Microsoft SQL Server (Database: `QuanLyQuanCafehetcuu`)
* **Công cụ xây dựng:** Apache Maven 3.9+ / IntelliJ IDEA Ultimate

### 2. Cấu hình CSDL trong `src/main/resources/application.properties`
```properties
db.url=jdbc:sqlserver://localhost:1433;databaseName=QuanLyQuanCafehetcuu;encrypt=true;trustServerCertificate=true;
db.user=HAKAI
db.password=14112009
```

### 3. Khởi chạy và kiểm tra Web
1. Mở dự án trong IntelliJ IDEA $\rightarrow$ Chọn Run configuration **Tomcat 11.0.211**.
2. Bấm **Run** (`Shift + F10`).
3. Truy cập các đường dẫn kiểm tra:
   * **Cổng khách hàng (Portal):** `http://localhost:8080/TEA_POS_PROJECT_war/home`
   * **Đăng nhập quản trị & POS:** `http://localhost:8080/TEA_POS_PROJECT_war/login`
     * *Admin:* `admin` / `12345678`
     * *Nhân viên:* `staff` / `12345678`

---

## 🧪 LỆNH CHẠY KIỂM THỬ TỰ ĐỘNG (MAVEN TEST)

Sinh viên và Giảng viên có thể chạy lệnh kiểm thử tự động tại thư mục gốc của dự án:
```bash
mvn clean test
```
* **Kết quả:** `BUILD SUCCESS` (100% test pass).