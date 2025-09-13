/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.LevelSokoban;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class LevelSokobanDAO {

    public List<LevelSokoban> readAllLevels() {
        List<LevelSokoban> list = new ArrayList<>();
        try {
            Connection con = KetnoiCSDL.openConnection();

            // Dùng câu lệnh SELECT lấy toàn bộ level
            String sql = "SELECT idLV, tenLV, tenfile, cot, dong FROM LevelSokoban";
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                LevelSokoban level = new LevelSokoban(
                        rs.getString("idLV"),
                        rs.getString("tenLV"),
                        rs.getString("tenfile"),
                        rs.getInt("cot"),
                        rs.getInt("dong")
                );
                list.add(level);
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public LevelSokoban getLevelByTen(String tenLV) {
        LevelSokoban level = null;
        try {
            Connection con = KetnoiCSDL.openConnection();

            // Câu lệnh SELECT có điều kiện WHERE theo tên level
            String sql = "SELECT idLV, tenLV, tenfile, cot, dong FROM LevelSokoban WHERE tenLV = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, tenLV);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                level = new LevelSokoban(
                        rs.getString("idLV"),
                        rs.getString("tenLV"),
                        rs.getString("tenfile"),
                        rs.getInt("cot"),
                        rs.getInt("dong")
                );
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return level;
    }

    //them du lieu vao bang
    public boolean insertLevel(LevelSokoban level) {
        try (Connection con = KetnoiCSDL.openConnection()) {
            String sql = "INSERT INTO LevelSokoban (idLV, tenLV, tenfile, cot, dong) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, level.getIdLV());
            stmt.setString(2, level.getTenLV());
            stmt.setString(3, level.getTenFile());
            stmt.setInt(4, level.getCot());
            stmt.setInt(5, level.getDong());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    //update du lieu
    public boolean updateLevel(LevelSokoban level) {
        try (Connection con = KetnoiCSDL.openConnection()) {
            String sql = "UPDATE LevelSokoban SET tenfile=?, cot=?, dong=? WHERE tenLV=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, level.getTenFile());
            stmt.setInt(2, level.getCot());
            stmt.setInt(3, level.getDong());
            stmt.setString(4, level.getTenLV()); // `tenLV` là khoá chính

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteLevel(String tenLV) {
        try (Connection con = KetnoiCSDL.openConnection()) {
            String sql = "DELETE FROM LevelSokoban WHERE tenLV = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, tenLV);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
