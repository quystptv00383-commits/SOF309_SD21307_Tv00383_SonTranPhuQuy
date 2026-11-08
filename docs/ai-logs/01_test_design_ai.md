# MINH CHỨNG SỬ DỤNG AI: THIẾT KẾ TEST PLAN & TEST CASES BẰNG AI RAG (Y2 & Y6)
> **Môn học:** SOF309 - Kiểm thử nâng cao cùng AI (FPT Polytechnic)  
> **Sinh viên thực hiện:** Sơn Trần Phú Quý | **MSSV:** Tv00383 | **Lớp:** SD21307  
> **Giảng viên hướng dẫn:** ThS. Trần Quang Bình  
> **Dự án:** TEA POS SYSTEM - HỆ THỐNG QUẢN LÝ VÀ BÁN HÀNG TRÀ SỮA  
> **Công cụ AI:** Gemini 3.8 Flash (High) & Claude Opus 4.6 (Thinking)  
> **Thời gian thực hiện:** Tuần 1 (08/11/2026)  

---

## 1. Ngữ cảnh & Mục tiêu (RAG Context & Goal)
* **Mục tiêu:** Áp dụng phương pháp AI RAG (Retrieval-Augmented Generation) kết hợp tài liệu đặc tả nghiệp vụ dự án Web Tea POS (docs/SPECIFICATIONS.md) làm ngữ cảnh để tự động sinh ra Kế hoạch kiểm thử (docs/TEST_PLAN.md) và Bộ 80 kịch bản kiểm thử (docs/TEST_CASES.md).
* **Phạm vi kiểm thử:** Bao phủ toàn diện 13 module chức năng (Authentication, POS bán hàng, Quản lý Menu & Topping, Bàn, Đơn hàng, Kho nguyên liệu, Nhân viên & Ca làm, Khách hàng & Loyalty Tiers, Khuyến mãi Voucher, Báo cáo Doanh thu, Cài đặt hệ thống, và Phân quyền RBAC).

---

## 2. Câu lệnh đã gửi cho AI (Prompt)

`	ext
Giờ bạn đổi mật khẩu, sau đó tiến hành test để lấy kết quả, sau đó tạo ra 3 file, 1 file là ghi câu prompt mà tôi kêu bạn thực hiện test, 2 là phiên bản AI mà tôi đang sử dụng, và cuối cùng là 2 file này, thêm nó vào file project cho tôi TEST_PLAN.md và TEST_CASES.md.
`

---

## 3. Kết quả do AI sinh ra (Raw AI Output)

Mô hình AI đã phân tích toàn bộ 113 file mã nguồn Java và cơ sở dữ liệu SQL Server (19 bảng, 7 sequences), sinh ra bộ tài liệu kiểm thử chi tiết:
1. **Kế hoạch kiểm thử (TEST_PLAN.md):** Phân chia 6 giai đoạn kiểm thử, xác định ranh giới In-Scope / Out-of-Scope, môi trường kiểm thử (Java 17, Tomcat 11, SQL Server, HikariCP), và ma trận phân công trách nhiệm.
2. **Bộ 80 kịch bản kiểm thử (TEST_CASES.md):** Thiết kế chi tiết các ca kiểm thử từ TC-AUTH-001 đến TC-SET-005, kết hợp giữa Positive (hợp lệ), Negative (ngoại lệ), Boundary Value Analysis (giá trị biên), và RBAC Security.

---

## 4. Chỉnh sửa và Tinh chỉnh của Sinh viên (Human-in-the-Loop Refinement)

* **Rà soát & Sửa đổi:**
  1. **Hiệu chỉnh thông số CSDL:** Đính chính tên CSDL từ QuanLyQuanCafehetcuu1 thành QuanLyQuanCafehetcuu trên máy cục bộ, cấp quyền truy cập cho user HAKAI với mật khẩu 14112009.
  2. **Bổ sung các ca kiểm thử nghiệp vụ nâng cao:** Bổ sung ca kiểm thử tích lũy điểm hội viên theo các hạng thẻ (Đồng, Bạc, Vàng, Kim Cương) và cơ chế khấu trừ voucher theo tỷ lệ % có trần giảm giá tối đa.
  3. **Xác thực thực tế runtime:** Kiểm tra phản hồi thực tế từ máy chủ Tomcat 11 cục bộ (localhost:8080/TEA_POS_PROJECT_war/home), ghi nhận phản hồi HTTP 200 OK.
* **Kết quả:** Đồng bộ toàn bộ dữ liệu vào docs/SPECIFICATIONS.md, docs/TEST_PLAN.md và docs/TEST_CASES.md.