# SokobaGame  

Dự án Sokoban được phát triển bằng **Java**.  

## Tính năng
- CRUD màn chơi
- Giao diện sử dụng Java Swing
- Hỗ trợ giải tự động bằng thuật toán A*

## Cách chạy project
- Khởi động SQL Server Management Studio đổi tên user là sa và password là 123 trên SQL Server Management Studio
- chạy file Sokoban.sql
- khởi động netbeans, chạy project
- chuột phải vào Text Libraries chọn Add JAR/folder
- <img width="337" height="577" alt="image" src="https://github.com/user-attachments/assets/b9d4d002-db29-41a2-948a-1c617b0a816d" />
- chọn giống hình
- <img width="933" height="473" alt="image" src="https://github.com/user-attachments/assets/de086e6e-b144-4e34-90d6-f507309afb1a" />
- cuối cùng vào Gui rồi run file như trên hình
- <img width="1915" height="1016" alt="image" src="https://github.com/user-attachments/assets/91321a0b-f7d0-4ac9-98e2-a2eb92447cd0" />
- kết quả
- <img width="1158" height="708" alt="image" src="https://github.com/user-attachments/assets/a23c141a-efa1-4f51-a7ad-ad50883535f1" />

## lưu ý: nếu không kết nối được với database thì mở SQL Server Configuration Manager
- Nếu không thấy SQL Server Configuration Manager, bạn có thể vào: C:\Windows\SysWOW64\SQLServerManager.msc như hình dưới
- <img width="1001" height="593" alt="image" src="https://github.com/user-attachments/assets/41546337-3942-4328-b204-f0655377445b" />
Trong cửa sổ, chọn:
SQL Server Network Configuration
  → Protocols for MSSQLSERVER
Ở khung bên phải, double-click vào TCP/IP để mở Properties.
Chuyển sang tab IP Addresses.
Kéo xuống dưới cùng phần IPAll.
Bạn sẽ thấy:
TCP Dynamic Ports → để trống (nếu muốn dùng port cố định).
TCP Port → nhập số port bạn muốn (mặc định là 1433).
Bấm OK, rồi Restart dịch vụ SQL Server để áp dụng thay đổi:
Vào SQL Server Services trong Configuration Manager.
Chuột phải vào SQL Server (MSSQLSERVER) → chọn Restart.








