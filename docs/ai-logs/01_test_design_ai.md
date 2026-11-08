# Nhật ký AI: Thiết Kế Test Plan & Test Cases Bằng AI RAG Cho Dự Án Tea POS

## 1. Ngữ cảnh & Mục tiêu
- **Mục tiêu:** Sử dụng đặc tả nghiệp vụ chi tiết của dự án Web Tea POS (`docs/SPECIFICATIONS.md`) làm ngữ cảnh (Context) để AI RAG sinh ra:
  1. Kế hoạch kiểm thử toàn diện (`docs/TEST_PLAN.md`).
  2. Bảng 80 kịch bản kiểm thử (`docs/TEST_CASES.md`) bao phủ đầy đủ 13 module chức năng của hệ thống (POS, Authentication, Menu, Bàn, Đơn hàng, Kho nguyên liệu, Nhân viên, Ca làm việc, Khách hàng & Loyalty, Khuyến mãi, Doanh thu, Cài đặt và Phân quyền).
- **Phạm vi:** Kiểm thử chức năng (Functional), Kiểm thử ngoại lệ (Negative/Exception), Phân tích giá trị biên (Boundary Value), và Kiểm thử phân quyền (RBAC).

## 2. Câu lệnh đã gửi cho AI (Prompt)
> "Giờ bạn đổi mật khẩu,sau đó tiến hành test để lấy kết quả, sau đó tạo ra 3 file, 1 file là ghi câu prompt mà tôi kêu bạn thực hiện test, 2 là phiên bản AI mà tôi đang sử dụng, và cuối cùng là 2 file này, thêm nó vào file project cho tôi TEST_PLAN.md và TEST_CASES.md"

## 3. Kết quả do AI sinh ra (Raw Output)
AI (Gemini 3.8 Flash High) đã thực hiện phân tích toàn bộ cấu trúc mã nguồn gồm:
- 113 file mã nguồn Java (Controllers, Filters, DAOs, Models, DTOs, Listeners).
- Hệ thống cơ sở dữ liệu SQL Server với 19 bảng và 7 sequences.
- Sinh ra:
  - `TEST_PLAN.md`: Kế hoạch kiểm thử với 6 giai đoạn, môi trường kiểm thử (Java 17, Tomcat 11.0.21, SQL Server, HikariCP), ma trận phân định trách nhiệm RACI và tiêu chí nghiệm thu.
  - `TEST_CASES.md`: 80 ca kiểm thử chi tiết từ `TC-AUTH-001` đến `TC-AUTH-006`, `TC-POS-001` đến `TC-POS-010`, `TC-PROD-001` đến `TC-SET-005` kèm dữ liệu kiểm thử (Test Data) và kết quả mong đợi.

## 4. Chỉnh sửa của Sinh viên & Lý do sửa
- **Những điểm phát hiện & hiệu chỉnh:**
  - AI ban đầu kiểm thử kết nối với tên database `QuanLyQuanCafehetcuu1`, nhưng database thực tế trên SQL Server là `QuanLyQuanCafehetcuu`. Sinh viên đã yêu cầu AI sửa lại thông số `databaseName=QuanLyQuanCafehetcuu` trong `application.properties`.
  - Sinh viên cung cấp tài khoản SQL chính xác `HAKAI / 14112009` và yêu cầu AI cấp quyền `sysadmin` để kết nối thông suốt.
  - Sau khi kết nối thành công, bổ sung thêm các ca kiểm thử liên quan đến cơ chế tính điểm tích lũy thành viên (Loyalty Tiers: Bronze, Silver, Gold, Diamond) và ca làm việc (Shift Tracking).
- **Tài liệu hoàn thiện:** Đã tích hợp đầy đủ vào `docs/TEST_PLAN.md` và `docs/TEST_CASES.md`.