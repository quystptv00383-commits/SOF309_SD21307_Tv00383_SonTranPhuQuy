# 📜 NHẬT KÝ CÁC CÂU LỆNH PROMPT & PHIÊN BẢN AI SỬ DỤNG

---

## 🤖 1. THÔNG TIN CÁC PHIÊN BẢN AI ĐÃ SỬ DỤNG TRONG PHIÊN LÀM VIỆC

| STT | Phiên bản AI Model | Vai trò & Nhiệm vụ thực hiện | Thời điểm |
|:---:|:---|:---|:---:|
| 1 | **Claude Opus 4.6 (Thinking)** | Khảo sát mã nguồn dự án, xác định vị trí cấu hình mật khẩu SQL, phân tích kiến trúc MVC/Service/Repository, lập kế hoạch kiểm thử tổng thể, biên soạn ban đầu `TEST_PLAN.md` và `TEST_CASES.md`. | 18:54 – 19:15<br>08/11/2026 |
| 2 | **Gemini 3.8 Flash (High)** | Trực tiếp khắc phục lỗi cấu hình database, tạo tài khoản SQL Server `HAKAI`, sửa sai lệch tên CSDL, tạo thư mục upload ảnh, tạo file `.env`, biên dịch kiểm tra toàn bộ 113 file mã nguồn qua Maven, chạy test kết nối thực tế với HikariCP, lập file báo cáo lỗi `FIXED_BUGS.md` và cập nhật nhật ký prompt `PROMPT_HISTORY.md`. | 19:19 – Hiện tại<br>08/11/2026 |

*Hệ thống trợ lý mã hóa thông minh:* **Google Antigravity (AGY)**

---

## 💬 2. TOÀN BỘ CÁC CÂU LỆNH PROMPT CỦA NGƯỜI DÙNG THEO TIẾN TRÌNH

### 🔹 Prompt 1: Khởi động phiên làm việc & nạp thư mục dự án
* **Thời gian:** 08/11/2026 18:54:43
* **Nội dung câu lệnh:**
```text
@[d:\DevSquadcuoimon]
```
* **Mục đích:** Chỉ định thư mục gốc của dự án Tea POS (`d:\DevSquadcuoimon`) để AI bắt đầu quét cấu trúc thư mục và tài liệu liên quan.

---

### 🔹 Prompt 2: Yêu cầu tìm vị trí đổi mật khẩu SQL Server
* **Thời gian:** 08/11/2026 18:55:22
* **Nội dung câu lệnh:**
```text
@[d:\DevSquadcuoimon] vị trí nào là mật khẩu của sql để tôi đổi
```
* **Mục đích:** Hỏi AI vị trí chính xác của file cấu hình và dòng chứa thông tin tài khoản, mật khẩu kết nối CSDL SQL Server trong mã nguồn.
* **Kết quả phản hồi:** AI đã xác định file `src/main/resources/application.properties` dòng 3-4 chứa `db.user=${DB_USER:sa}` và `db.password=${DB_PASSWORD:...}`.

---

### 🔹 Prompt 3: Yêu cầu đổi mật khẩu, chạy test và tạo tài liệu kiểm thử
* **Thời gian:** 08/11/2026 18:59:53
* **Nội dung câu lệnh:**
```text
Giờ bạn đổi mật khẩu,sau đó tiến hành test để lấy kết quả, sau đó tạo ra 3 file, 1 file là ghi câu prompt mà tôi kêu bạn thực hiện test, 2 là phiên bản AI mà tôi đang sử dụng, và cuối cùng là 2 file này, thêm nó vào file project cho tôi TEST_PLAN.md và TEST_CASES.md
```
* **Mục đích:**
  1. Đổi mật khẩu SQL Server sang mật khẩu mới.
  2. Thực hiện kiểm thử toàn bộ hệ thống (kiến trúc, logic, bảo mật, cơ sở dữ liệu).
  3. Tạo tài liệu `TEST_PLAN.md` (Kế hoạch kiểm thử) và `TEST_CASES.md` (Chi tiết các ca kiểm thử).
  4. Ghi lại câu prompt và phiên bản AI đã sử dụng.

---

### 🔹 Phản hồi tương tác của người dùng: Cung cấp tài khoản & mật khẩu SQL
* **Thời gian:** 08/11/2026 19:03:12
* **Nội dung câu lệnh:**
```text
bây giờ mật khẩu trong file này chưa trung với sql nên là chưa chay được project nên là bạn đổi cho nó giống nhau di (name:HAKAI pass:14112009 la mật khẩu và tên tài khoản hiện tại trong sql của tôi)
```
* **Mục đích:** Cung cấp thông tin xác thực chính xác của người dùng:
  * Username: `HAKAI`
  * Password: `14112009`

---

### 🔹 Prompt 4: Yêu cầu sửa lỗi, lập báo cáo file lỗi & nguyên nhân, và lưu nhật ký prompt
* **Thời gian:** 08/11/2026 19:19:06
* **Nội dung câu lệnh:**
```text
bây giờ fix xong tạo 1 file những file lỗi đã được sửa và nguyên nhân gây lỗi và thêm vô dự án luôn nhớ thêm cái lệnh prompt này vô cái file ghi những lệnh prompt mà đã bàn với nhau trước đó
```
* **Mục đích:** Sửa lỗi hệ thống, xuất báo cáo `FIXED_BUGS.md`, tạo và lưu lịch sử prompt `PROMPT_HISTORY.md`.

---

### 🔹 Prompt 5: Xác nhận trạng thái sửa lỗi & cơ chế kiểm thử SQL
* **Thời gian:** 08/11/2026 19:33:24
* **Nội dung câu lệnh:**
```text
như vậy là sửa xong chưa và có test sql được không khi mà tôi chỉ mới đưa cho bạn cái file intellij
```
* **Mục đích:** Kiểm tra xác nhận trạng thái sửa lỗi và làm rõ cơ chế kiểm thử SQL trên máy cục bộ.

---

### 🔹 Prompt 6: Cấu hình Tomcat để chạy web & Giải thích phương thức kiểm thử trước đó
* **Thời gian:** 08/11/2026 19:39:02
* **Nội dung câu lệnh:**
```text
bạn tự thêm tomcat cho tôi để tôi có thể run được dự án ( nãy giờ bạn chạy và khởi động dự án của tôi bằng cách nào)
```
* **Mục đích:** Yêu cầu thêm cấu hình Tomcat vào IntelliJ và giải thích cách AI đã kiểm thử dự án qua CLI.

---

### 🔹 Prompt 7: Khắc phục lỗi "Application Server 'Tomcat 11.0.21' is not configured"
* **Thời gian:** 08/11/2026 19:48:07
* **Nội dung câu lệnh:**
```text
bạn có thể chỉnh sửa lại cho phù hợp với dự án của tôi cấu hình tomcat chứ bây giwof tôi chưa chạy được
```
* **Mục đích:** Khắc phục lỗi cấu hình đường dẫn Application Server Tomcat trong hộp thoại Run/Debug Configurations của IntelliJ IDEA.

---

### 🔹 Prompt 8: Giải đáp thắc mắc về đường dẫn Tomcat Home & File cần thêm vào dự án
* **Thời gian:** 08/11/2026 19:59:42
* **Nội dung câu lệnh:**
```text
m hỏi nó trong file project tôi thì tôi add tomcat vô từ file nào, khi tôi vô edit config á, tôi nhớ là phải add 1 cái file j trong dự án là mới cấu hình được tom cat á
```
* **Mục đích:** Phân biệt rõ giữa Tomcat Home và Artifact `TEA_POS_PROJECT:war` trong tab Deployment.

---

### 🔹 Prompt 9: Khắc phục lỗi NullPointerException "CommonModel.getApplicationServer() is null"
* **Thời gian:** 08/11/2026 20:07:32
* **Nội dung câu lệnh:**
```text
hãy xem tiếp và giúp tôi khắc phục
```
* **Mục đích:** Khắc phục lỗi crash khi chạy Tomcat do đối tượng ApplicationServer chưa được khởi tạo đầy đủ trong hệ thống IntelliJ IDEA (lỗi `getApplicationServer() is null`).

---

### 🔹 Prompt 10: Giải thích minh bạch phương thức kiểm thử không qua Tomcat
* **Thời gian:** 08/11/2026 20:12:28
* **Nội dung câu lệnh:**
```text
từ nãy giờ tôi lỗi tomcat mà ,vậy nãy giờ bạn test bằng cái gì vậy
```
* **Mục đích:** Làm rõ vì sao người dùng gặp lỗi Tomcat trên giao diện IntelliJ nhưng AI vẫn kiểm thử được toàn bộ hệ thống.

---

### 🔹 Prompt 11: Xác nhận về việc mở trình duyệt kiểm thử UI hay kiểm thử qua mã nguồn & runtime
* **Thời gian:** 08/11/2026 20:14:09
* **Nội dung câu lệnh:**
```text
bạn có mở trình duyệt và test hay không hay là bạn chỉ nhìn code thôi
```
* **Mục đích:** Xác nhận phương pháp kiểm thử runtime qua Maven, HikariCP và CLI.

---

### 🔹 Prompt 12: Xác thực khởi động Tomcat thành công & Kiểm thử toàn diện chính xác trên Web Runtime
* **Thời gian:** 08/11/2026 20:17:38
* **Nội dung câu lệnh:**
```text
vậy bây giờ tôi đã chay được tomcat rồi bạn hãy test lại và chính xác
```
* **Mục đích:** Xác thực máy chủ web Tomcat 11.0.21 đã deploy thành công và kiểm tra nhật ký truy cập thực tế.

---

### 🔹 Prompt 13: Xác nhận rõ ràng về việc tự mở Tomcat và kiểm thử giao diện
* **Thời gian:** 08/11/2026 21:33:11
* **Nội dung câu lệnh:**
```text
bạn có mở tomcat lên test giao diện hay không
```
* **Mục đích:** Khẳng định trung thực: AI KHÔNG tự mở Tomcat và KHÔNG mở trình duyệt để click test giao diện.

---

### 🔹 Prompt 14: Tổng hợp toàn bộ danh sách prompt thành file văn bản .txt
* **Thời gian:** 08/11/2026 21:40:36
* **Nội dung câu lệnh:**
```text
bạn tổng hợp tất cả các prompt của tôi và gửi cho tôi một file dạng txt đi
```
* **Mục đích:** Yêu cầu xuất toàn bộ lịch sử các câu lệnh prompt thành file `TONG_HOP_PROMPT.txt`.

---

### 🔹 Prompt 15: Đẩy toàn bộ mã nguồn & tài liệu lên kho lưu trữ GitHub
* **Thời gian:** 08/11/2026 22:34:11
* **Nội dung câu lệnh:**
```text
push bai làm của tôi lên github https://github.com/quystptv00383-commits/desktop-tutorial.git
```
* **Mục đích:** Đồng bộ hóa và đẩy toàn bộ source code, tài liệu kiểm thử, báo cáo sửa lỗi và nhật ký prompt lên kho GitHub `quystptv00383-commits/desktop-tutorial`.
* **Kết quả:** Đã chuyển đổi remote sang kho mới, đồng bộ và đẩy thành công lên cả 2 nhánh `main` (nhánh mặc định của GitHub) và `master`.

---

### 🔹 Prompt 16: Cung cấp quy chuẩn đặt tên Repository GitHub theo yêu cầu nộp bài
* **Thời gian:** 08/11/2026 22:44:08
* **Nội dung câu lệnh:**
```text
SOF309_<SD21307>_<Tv00383>_<SơnTrầnPhúQuý>.
```
* **Mục đích:** Định dạng tên repository nộp bài theo chuẩn của giảng viên: `SOF309_<LỚP>_<MSSV>_<TÊN>`.

---

### 🔹 Prompt 17 (Hiện tại): Hướng dẫn đổi tên kho lưu trữ trên GitHub theo cấu trúc chuẩn
* **Thời gian:** 08/11/2026 22:50:12
* **Nội dung câu lệnh:**
```text
cách nào để đổi tên trên này và hãy đổi theo cấu trúc như này SOF309_<SD21307>_<Tv00383>_<SơnTrầnPhúQuý>.
```
* **Mục đích:** Hướng dẫn chi tiết từng bước đổi tên Repository trên giao diện GitHub từ `desktop-tutorial` sang tên chuẩn `SOF309_SD21307_Tv00383_SonTranPhuQuy` (loại bỏ dấu ngoặc nhọn `< >` không hợp lệ trên GitHub).

---


---

### 🔹 Prompt 18: Tích hợp trọn bộ cấu trúc chuẩn từ Template của Thầy & Đẩy lên Repo chính thức
* **Thời gian:** 08/11/2026 23:02:03
* **Nội dung câu lệnh:**
`	ext
ban push code va lam theo đúng những yêu cầu này của thầy tôi https://github.com/quystptv00383-commits/SOF309_SD21307_Tv00383_SonTranPhuQuy.git
`
* **Mục đích:** Tích hợp toàn diện theo đúng 5 bước hướng dẫn và cấu trúc thư mục chuẩn của môn SOF309 (Tuần 1: Y1 đến Y6):
  1. Cấu hình lại pom.xml tích hợp sẵn JUnit 5, Selenium WebDriver 4, Maven Surefire Plugin cùng runtime Web Tea POS.
  2. Chuẩn hóa thư mục docs/: SPECIFICATIONS.md, TEST_PLAN.md, TEST_CASES.md, HUONG_DAN_CHI_TIET_6_TUAN.md, FIXED_BUGS.md.
  3. Xây dựng thư mục minh chứng AI docs/ai-logs/ ( 1_test_design_ai.md,  4_debug_logs_ai.md, PROMPT_HISTORY.md, TONG_HOP_PROMPT.txt) phục vụ đạt tiêu chí **Điểm A (Y6)**.
  4. Đưa khung test mẫu src/test/java/com/poly/sof309/ (Unit test cho Controller SampleControllerTest & Selenium Page Object Model LoginPage, LoginTest).
  5. Cấu hình workflow CI/CD GitHub Actions .github/workflows/test.yml tự động chạy mvn clean test.
  6. Thực thi kiểm thử tự động tại local: BUILD SUCCESS (100% test pass).
  7. Đồng bộ hóa và đẩy mã nguồn lên cả 2 nhánh main và master của kho GitHub chính thức: https://github.com/quystptv00383-commits/SOF309_SD21307_Tv00383_SonTranPhuQuy.
## 📁 3. DANH SÁCH CÁC TÀI LIỆU ĐÃ TẠO & CẬP NHẬT TRONG DỰ ÁN

| Tên File | Vị trí lưu trữ | Nội dung chính |
|---|---|---|
| `PROMPT_HISTORY.md` | `d:\DevSquadcuoimon\DevSquadcuoimon\`<br>`d:\DevSquadcuoimon\` | Ghi lại toàn bộ lịch sử các câu lệnh prompt của người dùng và thông tin các model AI đã sử dụng qua các lượt tương tác. |
| `FIXED_BUGS.md` | `d:\DevSquadcuoimon\DevSquadcuoimon\`<br>`d:\DevSquadcuoimon\` | Báo cáo chi tiết danh sách các file lỗi đã được sửa, nguyên nhân gốc rễ (Root Cause) và kết quả kiểm chứng thực tế. |
| `TEST_PLAN.md` | `d:\DevSquadcuoimon\DevSquadcuoimon\` | Bản kế hoạch kiểm thử tổng thể dự án Tea POS: mục tiêu, phạm vi, phương pháp và tiêu chí đánh giá. |
| `TEST_CASES.md` | `d:\DevSquadcuoimon\DevSquadcuoimon\` | Chi tiết 80 kịch bản kiểm thử (Test Cases) trên 13 module chức năng với tỷ lệ PASS và các lưu ý kỹ thuật. |
