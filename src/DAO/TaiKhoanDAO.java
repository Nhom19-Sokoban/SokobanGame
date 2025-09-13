/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.TaiKhoan;
import java.sql.Connection;
import java.sql.*;
/**
 *
 * @author ASUS
 */
public class TaiKhoanDAO {
    public TaiKhoan checkLogin(String tenDangNhap, String matKhau) throws Exception {
        try {
            String sql = "SELECT username, password, role FROM TaiKhoan WHERE username = ? AND password = ?";
            Connection con = KetnoiCSDL.openConnection(); // lớp kết nối riêng của bạn
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, tenDangNhap);
            pstm.setString(2, matKhau);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setUsername(tenDangNhap); // hoặc rs.getString("username")
                tk.setPassword(matKhau);     // hoặc rs.getString("password")
                tk.setRole(rs.getString("role"));
                return tk;
            }

            rs.close();
            pstm.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace(); // In lỗi để debug
        }
        return null;
    }
}
