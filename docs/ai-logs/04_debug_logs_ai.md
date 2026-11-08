# Nhật ký AI: Phân Tích & Sửa Lỗi Runtime (SQL Server & Tomcat Application Server)

## 1. Ngữ cảnh & Mục tiêu
- **Mục tiêu:** Xử lý các lỗi phát sinh trong quá trình khởi động ứng dụng Web Tea POS trên môi trường cục bộ (`localhost:8080`) để đảm bảo yêu cầu Y1 (Web chạy mượt mà ở local).
- **Phạm vi lỗi:** Lỗi kết nối CSDL SQL Server (Sai DB name, xác thực Login thất bại) và lỗi cấu hình Application Server Tomcat trong IntelliJ IDEA.

## 2. Câu lệnh đã gửi cho AI (Prompt) & Stack Trace
- **Prompt 1:** `"bây giờ mật khẩu trong file này chưa trung với sql nên là chưa chay được project nên là bạn đổi cho nó giống nhau di (name:HAKAI pass:14112009 la mật khẩu và tên tài khoản hiện tại trong sql của tôi)"`
- **Prompt 2:** `"bạn tự thêm tomcat cho tôi để tôi có thể run được dự án ( nãy giờ bạn chạy và khởi động dự án của tôi bằng cách nào)"`
- **Prompt 3:** `"Error running 'Tomcat 11.0.21': Cannot invoke 'com.intellij.javaee.appServers.appServerIntegrations.ApplicationServer.getPersistentData()' because the return value of 'CommonModel.getApplicationServer()' is null"`

## 3. Kết quả do AI sinh ra (Raw Output)
1. **Phân tích lỗi SQL Server:**
   - Stack trace: `Cannot open database "QuanLyQuanCafehetcuu1" requested by the login.`
   - Nguyên nhân: File `application.properties` gõ thừa số `1` ở cuối tên database.
   - AI đã sửa `databaseName=QuanLyQuanCafehetcuu` và tạo Login `[HAKAI]` trên SQL Server với mật khẩu `14112009`.
2. **Phân tích lỗi Tomcat:**
   - Nguyên nhân: IntelliJ IDEA thiếu đường dẫn `Tomcat Home` trong cài đặt hệ thống Application Servers (`getApplicationServer() is null`).
   - AI đã tự động tạo cấu hình `.run/Tomcat 11.0.211.run.xml`, cập nhật `.idea/workspace.xml`, đồng thời hướng dẫn trỏ đúng thư mục `C:\Program Files\Apache Software Foundation\Tomcat 11.0`.

## 4. Chỉnh sửa của Sinh viên & Lý do sửa
- Sinh viên kiểm tra thực tế trên SQL Server Management Studio để xác nhận tên database `QuanLyQuanCafehetcuu`.
- Sau khi AI hướng dẫn trỏ Tomcat Home và thêm artifact `TEA_POS_PROJECT:war exploded`, sinh viên đã khởi động Tomcat thành công trên cổng 8080.
- Nhật ký truy cập `localhost_access_log.txt` xác nhận: `GET /TEA_POS_PROJECT_war/home HTTP/1.1 200 22463` - Web chạy ổn định, hoàn thành nghiệm thu Y1!