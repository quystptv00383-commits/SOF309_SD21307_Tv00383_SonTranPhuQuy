# 📋 ĐẶC TẢ YÊU CẦU DỰ ÁN (SPECIFICATIONS)
## MÔN HỌC: SOF309 — KIỂM THỬ NÂNG CAO

---

## 👤 THÔNG TIN SINH VIÊN & HỌC PHẦN

| Mục | Chi tiết |
|---|---|
| **Môn học** | SOF309 — Kiểm thử phần mềm nâng cao |
| **Lớp học** | **SD21307** |
| **Mã số sinh viên (MSSV)** | **Tv00383** |
| **Họ và tên sinh viên** | **Sơn Trần Phú Quý** |
| **Tên dự án** | TEA POS — Hệ thống quản lý và bán hàng trà sữa trực tuyến & tại quầy |
| **Repo GitHub** | `SOF309_SD21307_Tv00383_SơnTrầnPhúQuý` |
| **Thời điểm nghiệm thu** | Tuần 1 (08/11/2026) |

---

## 🎯 1. TỔNG QUAN HỆ THỐNG (SYSTEM OVERVIEW)

**TEA POS** là giải pháp phần mềm quản lý và bán hàng toàn diện dành cho chuỗi cửa hàng đồ uống và trà sữa, kết hợp giữa mô hình bán hàng tại quầy (**POS - Point of Sale**) và cổng đặt hàng trực tuyến dành cho khách hàng (**Customer Portal**), cùng hệ thống quản trị chuyên sâu (**Admin Management**).

### 1.1 Mục tiêu hệ thống
* Tối ưu hóa tốc độ gọi món và thanh toán tại quầy thu ngân với thời gian phản hồi < 1 giây.
* Hỗ trợ khách hàng đặt trước đồ uống trực tuyến, tự chọn tùy chỉnh kích cỡ, mức đá, mức đường và các loại toppings ăn kèm.
* Quản lý chăm sóc khách hàng (**CRM**), tự động tích lũy điểm thưởng và thăng hạng hội viên (Đồng, Bạc, Vàng, Kim Cương).
* Hỗ trợ thanh toán đa kênh: Tiền mặt và Chuyển khoản ngân hàng tự động qua mã VietQR kết hợp SePay Webhook.
* Hộp đen kiểm toán (**Audit Trail**) ghi nhận toàn bộ lịch sử thao tác của nhân viên để chống gian lận.

---

## 🏗️ 2. KIẾN TRÚC KỸ THUẬT (TECHNICAL ARCHITECTURE)

### 2.1 Công nghệ sử dụng (Tech Stack)
* **Ngôn ngữ lập trình:** Java (JDK 17 LTS / JDK 25)
* **Nền tảng Web:** Jakarta EE 10 / Jakarta Servlet 6.0, JSP, JSTL 3.0
* **Máy chủ ứng dụng (Web Server):** Apache Tomcat 11.0.21 (chạy tại `localhost:8080`)
* **Hệ quản trị cơ sở dữ liệu:** Microsoft SQL Server 2022
* **Quản lý kết nối CSDL (Connection Pool):** HikariCP 5.0.0 (Tối ưu 20 kết nối đồng thời, Fail-fast 20s)
* **Thư viện tiện ích:** Gson 2.10.1 (JSON parsing), Eclipse Angus Mail 2.0.3 (SMTP gửi OTP)
* **Quản lý dự án & Build:** Apache Maven 3.9+
* **Mô hình kiến trúc:** MVC (Model - View - Controller) kết hợp Service Layer và DAO/Repository Pattern

### 2.2 Sơ đồ phân tầng kiến trúc (Layers)
```
[ Browser / Client ] (Giao diện JSP + CSS + JS)
        │
        ▼ (HTTP Requests: GET/POST /teapos/*)
[ Filter Layer ] (UTF8EncodingFilter, AdminAuthFilter, POSAuthFilter, PortalAuthFilter)
        │
        ▼
[ Controller Layer ] (Admin, POS, Portal, Auth Controllers)
        │
        ▼
[ Service Layer ] (IDanhMucService, ISanPhamService, IDonHangService, IKhachHangService...)
        │
        ▼
[ Repository Layer ] (JdbcHelper, RowMapper, DBConnect - HikariCP Pool)
        │
        ▼
[ Database ] (Microsoft SQL Server: QuanLyQuanCafehetcuu)
```

---

## 🗄️ 3. ĐẶC TẢ CƠ SỞ DỮ LIỆU (DATABASE SPECIFICATION)

### 3.1 Cấu trúc thực thể (19 bảng vật lý chuẩn 3NF)
1. `VAI_TRO`: Quản lý vai trò nhân sự (1: Quản lý/Admin, 2: Thu ngân/Staff).
2. `HANG_THANH_VIEN`: Quản lý 4 mốc phân hạng CRM (Đồng: 0đ, Bạc: 50đ, Vàng: 200đ, Kim Cương: 500đ).
3. `KICH_CO`: Kích cỡ ly chuẩn (S, M, L).
4. `PHUONG_THUC_THANH_TOAN`: Phương thức thanh toán (Tiền mặt, Chuyển khoản QR).
5. `DANH_MUC`: Phân loại đồ uống (Trà sữa, Trà trái cây, Cà phê, Bánh ngọt...).
6. `SAN_PHAM`: Thực đơn sản phẩm mẹ (Tên, mô tả, ảnh, cờ best-seller, cờ new).
7. `SAN_PHAM_KICH_CO`: Bảng map biến thể kích cỡ, đơn giá và định lượng tương ứng.
8. `TOPPING`: Danh sách món thêm ăn kèm (Trân châu, Kem cheese, Pudding...).
9. `NHAN_VIEN`: Tài khoản nhân sự (Họ tên, SĐT, Email, Mật khẩu hash SHA-256).
10. `KHACH_HANG`: Hồ sơ khách hàng loyalty (Điểm tích lũy, hạng thành viên, trạng thái kích hoạt).
11. `GIO_HANG`: Giỏ hàng online của từng khách hàng.
12. `CHI_TIET_GIO_HANG`: Chi tiết ly nước trong giỏ (Size, số lượng, đá, đường, ghi chú, toggle chọn mua).
13. `CHI_TIET_TOPPING_GIO_HANG`: Topping đi kèm của từng ly trong giỏ hàng.
14. `CHUONG_TRINH_KHUYEN_MAI`: Quản lý Voucher (Giảm theo % hoặc tiền mặt, đơn tối thiểu, số lượng, hạn dùng).
15. `DON_HANG`: Đơn hàng POS và Online (Tổng tiền, giảm giá, điểm trừ, trạng thái thanh toán, trạng thái đơn).
16. `CHI_TIET_DON_HANG`: Chi tiết ly nước đã chốt giá trong đơn hàng.
17. `CHI_TIET_TOPPING`: Topping đã chốt giá đi kèm ly nước trong đơn hàng.
18. `NHAT_KY_HOAT_DONG`: Hộp đen kiểm toán lưu vết thao tác (IP, nhân viên, bảng tác động, dữ liệu cũ/mới).
19. `THONG_KE_NGAY`: Bảng dữ liệu tổng hợp phục vụ báo cáo doanh thu nhanh.

### 3.2 Hệ thống 7 Sequences phát sinh mã tự động (NO CACHE)
* `seq_DanhMuc` ➔ Định dạng `DM00001`
* `seq_SanPham` ➔ Định dạng `SP00001`
* `seq_Topping` ➔ Định dạng `TP00001`
* `seq_Voucher` ➔ Định dạng `KM00001`
* `seq_NhanVien` ➔ Định dạng `NV00001`
* `seq_KhachHang` ➔ Định dạng `KH00001`
* `seq_DonHang` ➔ Định dạng `DH00001`

---

## 📱 4. ĐẶC TẢ CÁC PHÂN HỆ CHỨC NĂNG (FUNCTIONAL SPECIFICATIONS)

### 4.1 Phân hệ Xác thực & Phân quyền (Authentication & Authorization)
* **UC-AUTH-01 (Đăng nhập nhân viên):** Xác thực tài khoản Admin/Staff qua username + password (SHA-256). Có cơ chế khóa tạm thời nếu đăng nhập sai liên tiếp. Điều hướng theo quyền: Admin ➔ `/admin/dashboard`, Staff ➔ `/pos/ban-hang`.
* **UC-AUTH-02 (Đăng ký khách hàng):** Khách hàng đăng ký trên Portal ➔ Hệ thống tự động tạo mã `KH00001`, gán hạng Đồng, gửi mã OTP xác thực qua Email để kích hoạt tài khoản.
* **UC-AUTH-03 (Quên mật khẩu OTP):** Người dùng nhập email ➔ Nhận mã OTP 6 số qua Gmail SMTP ➔ Xác thực OTP ➔ Đặt mật khẩu mới.
* **UC-AUTH-04 (Bảo mật Filters):** 
  * `AdminAuthFilter`: Chặn truy cập trái phép vào `/admin/*`.
  * `POSAuthFilter`: Bảo vệ khu vực bán hàng `/pos/*`.
  * `PortalAuthFilter`: Bảo vệ giỏ hàng và thanh toán cá nhân.

### 4.2 Phân hệ Quản trị (Admin Module)
* **UC-ADM-01 (Dashboard KPI & Báo cáo):** Thống kê tổng doanh thu, số đơn hoàn tất, top 10 sản phẩm bán chạy, top 10 nhân viên bán hàng xuất sắc, biểu đồ doanh thu theo ngày và tháng.
* **UC-ADM-02 (Quản lý Danh mục):** Xem danh sách, tạo mới danh mục với mã tự sinh, cập nhật, xóa (có kiểm tra ràng buộc khóa ngoại không cho xóa danh mục đang chứa sản phẩm).
* **UC-ADM-03 (Quản lý Thực đơn & Biến thể Size):** Thêm món mới, cấu hình giá bán linh hoạt cho từng size (S, M, L), gắn cờ New / Bestseller, cơ chế xóa mềm (`trang_thai = 0`).
* **UC-ADM-04 (Quản lý Topping):** Quản lý giá bán, định lượng, hình ảnh và bật/tắt phục vụ topping.
* **UC-ADM-05 (Quản lý Khuyến mãi Voucher):** Tạo mã giảm giá theo %, tiền mặt, cấu hình đơn hàng tối thiểu, giới hạn lượt dùng cá nhân và ngày hết hạn.
* **UC-ADM-06 (Quản lý Khách hàng CRM):** Xem thông tin hạng thành viên, lịch sử điểm tích lũy.
* **UC-ADM-07 (Quản lý Nhân sự & Phân quyền):** Cấp tài khoản nhân viên, reset mật khẩu, xem nhật ký kiểm toán.

### 4.3 Phân hệ Bán hàng tại quầy (POS Module)
* **UC-POS-01 (Bán hàng nhanh tại quầy):** Giao diện POS cảm ứng, chọn món, chọn size, thêm topping, ghi chú riêng cho từng ly.
* **UC-POS-02 (Xử lý thanh toán Tiền mặt):** Tính tiền thối, áp dụng voucher, hoàn tất đơn hàng và in hóa đơn tại chỗ (`trang_thai_don = 4`).
* **UC-POS-03 (Xử lý thanh toán QR VietQR):** Tạo đơn hàng tạm thời, hiển thị mã QR kèm mã đơn hàng trong nội dung chuyển khoản ➔ Lắng nghe Webhook SePay để tự động khớp lệnh thanh toán.
* **UC-POS-04 (Tiếp nhận & Xử lý đơn online):** Thu ngân nhận đơn hàng do khách đặt từ Portal, xác nhận chế biến, thông báo hoàn thành đơn cho khách đến lấy.

### 4.4 Phân hệ Đặt hàng trực tuyến Khách hàng (Portal Module)
* **UC-POR-01 (Xem thực đơn & Tùy biến món):** Khách chọn món, chọn dung tích ly, chọn mức đá (0%, 30%, 50%, 100%), mức đường (0%, 30%, 50%, 100%) và danh sách topping ăn kèm.
* **UC-POR-02 (Quản lý giỏ hàng):** Thêm vào giỏ, cập nhật số lượng, xóa món, tick chọn các món cần thanh toán.
* **UC-POR-03 (Checkout & Đặt hàng):** Chọn giờ hẹn lấy món, áp dụng voucher giảm giá, chọn trừ điểm loyalty để giảm tiền.

---

## ⚡ 5. ĐẶC TẢ GIAO DIỆN & DANH SÁCH ENDPOINTS (URL MAPPINGS)

| Phân hệ | URL Endpoint | Phương thức | Mô tả chức năng |
|---|---|:---:|---|
| **Portal** | `/home` | GET | Trang chủ thực đơn cho khách hàng |
| **Portal** | `/portal/cart` | GET / POST | Xem và thao tác giỏ hàng |
| **Portal** | `/portal/checkout` | GET / POST | Đặt hàng và thanh toán trực tuyến |
| **Portal** | `/portal/profile` | GET / POST | Xem hồ sơ và điểm thưởng cá nhân |
| **Auth** | `/login` | GET / POST | Đăng nhập nhân sự (Admin & Staff) |
| **Auth** | `/dang-nhap-khach` | GET / POST | Đăng nhập khách hàng thành viên |
| **Auth** | `/dang-ky` | GET / POST | Đăng ký thành viên mới |
| **Auth** | `/xac-thuc-otp` | GET / POST | Xác thực OTP email kích hoạt tài khoản |
| **POS** | `/pos/ban-hang` | GET / POST | Màn hình bán hàng thu ngân tại quầy |
| **POS** | `/pos/nhan-don-online` | GET / POST | Màn hình tiếp nhận đơn hàng online |
| **Admin** | `/admin/dashboard` | GET | Màn hình Dashboard KPI thống kê |
| **Admin** | `/admin/san-pham` | GET / POST | Quản lý thực đơn đồ uống |
| **Admin** | `/admin/danh-muc` | GET / POST | Quản lý danh mục sản phẩm |
| **Admin** | `/admin/voucher` | GET / POST | Quản lý chương trình khuyến mãi |
| **Admin** | `/admin/topping` | GET / POST | Quản lý toppings ăn kèm |
| **Admin** | `/admin/nhan-vien` | GET / POST | Quản lý hồ sơ nhân sự |
| **Payment** | `/webhook/sepay` | POST | Webhook tiếp nhận tín hiệu thanh toán SePay |
| **Payment** | `/check-payment` | GET | API kiểm tra trạng thái thanh toán QR |

---

## 🧪 6. LIÊN KẾT TÀI LIỆU KIỂM THỬ (TEST ARTIFACTS)

Dự án đã được tích hợp bộ tài liệu kiểm thử toàn diện sinh bởi AI RAG (Claude Opus 4.6 Thinking & Gemini 3.8 Flash):
* 📋 **[TEST_PLAN.md](./TEST_PLAN.md)**: Kế hoạch kiểm thử tổng thể, chiến lược kiểm thử, phân tích rủi ro và tiêu chuẩn nghiệm thu.
* 📊 **[TEST_CASES.md](./TEST_CASES.md)**: Chi tiết 80 kịch bản kiểm thử trên 13 module (Đạt tỷ lệ **97.5% PASS**).
* 🛠️ **[FIXED_BUGS.md](./FIXED_BUGS.md)**: Báo cáo khắc phục lỗi kết nối CSDL, tài khoản `HAKAI` và cấu hình hệ thống.
* 📜 **[PROMPT_HISTORY.md](./PROMPT_HISTORY.md)** & **[TONG_HOP_PROMPT.txt](./TONG_HOP_PROMPT.txt)**: Minh chứng tương tác toàn bộ chuỗi câu lệnh prompt với AI.
