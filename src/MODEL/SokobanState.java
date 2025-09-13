/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
/**
 *
 * @author ASUS
 */
public class SokobanState {
    public char[][][] map; // map[0]: tường và đích, map[1]: hộp và người chơi
    public Point player; // vị trí người chơi
    public List<Point> boxes; // vị trí các hộp
    public SokobanState parent; // trạng thái cha để truy ngược đường đi
    public String moveHistory; // chuỗi ghi lại các bước đi (VD: "U", "D", "L", "R")
    public int cost; // g(n): chi phí từ trạng thái gốc
    private int hash; // Bộ nhớ đệm cho hashCode
    private static List<Point> cachedGoals = null; // Bộ nhớ đệm cho danh sách đích

    public SokobanState(char[][][] map, Point player, List<Point> boxes) {
        this.map = map; // Trì hoãn sao chép map đến khi cần
        this.player = new Point(player);
        this.boxes = new ArrayList<>(boxes.size());
        for (Point b : boxes) {
            this.boxes.add(new Point(b));
        }
        this.parent = null;
        this.moveHistory = "";
        this.cost = 0;
        this.hash = computeHashCode(); // Tính hash ngay khi khởi tạo
    }

    // Sao chép map chỉ khi cần thiết
    public char[][][] getMapCopy() {
        char[][][] copy = new char[2][map[0].length][map[0][0].length];
        for (int i = 0; i < map[0].length; i++) {
            System.arraycopy(map[0][i], 0, copy[0][i], 0, map[0][0].length);
            System.arraycopy(map[1][i], 0, copy[1][i], 0, map[1][0].length);
        }
        return copy;
    }

    // Kiểm tra trạng thái đã là mục tiêu chưa
    public boolean isGoal() {
        for (Point b : boxes) {
            if (map[0][b.x][b.y] != '.') {
                return false;
            }
        }
        return true;
    }

    // Tính heuristic cải tiến
    public int heuristic() {
        if (cachedGoals == null) {
            cachedGoals = new ArrayList<>();
            for (int x = 0; x < map[0].length; x++) {
                for (int y = 0; y < map[0][0].length; y++) {
                    if (map[0][x][y] == '.') {
                        cachedGoals.add(new Point(x, y));
                    }
                }
            }
        }

        int h = 0;
        // Tính khoảng cách Manhattan từ mỗi thùng đến đích gần nhất
        for (Point b : boxes) {
            int minDist = Integer.MAX_VALUE;
            for (Point g : cachedGoals) {
                int dist = Math.abs(b.x - g.x) + Math.abs(b.y - g.y);
                minDist = Math.min(minDist, dist);
            }
            h += minDist;
        }
        // Thêm khoảng cách từ người chơi đến thùng gần nhất
        int minPlayerDist = Integer.MAX_VALUE;
        for (Point b : boxes) {
            int dist = Math.abs(player.x - b.x) + Math.abs(player.y - b.y);
            minPlayerDist = Math.min(minPlayerDist, dist);
        }
        h += minPlayerDist / 2; // Giảm trọng số để heuristic vẫn admissible
        return h;
    }

    // Kiểm tra trạng thái deadlock đơn giản
    public boolean isDeadlock() {
        for (Point b : boxes) {
            // Kiểm tra thùng ở góc (không phải đích)
            if (map[0][b.x][b.y] != '.') {
                boolean isCorner = (map[0][b.x - 1][b.y] == '#' && map[0][b.x][b.y - 1] == '#') ||
                                  (map[0][b.x - 1][b.y] == '#' && map[0][b.x][b.y + 1] == '#') ||
                                  (map[0][b.x + 1][b.y] == '#' && map[0][b.x][b.y - 1] == '#') ||
                                  (map[0][b.x + 1][b.y] == '#' && map[0][b.x][b.y + 1] == '#');
                if (isCorner) {
                    return true;
                }
            }
        }
        return false;
    }

    // Tối ưu equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SokobanState)) return false;
        SokobanState other = (SokobanState) o;
        if (!player.equals(other.player)) return false;
        if (boxes.size() != other.boxes.size()) return false;
        // Sử dụng Set để so sánh nhanh hơn
        return new HashSet<>(boxes).equals(new HashSet<>(other.boxes));
    }

    // Tối ưu hashCode
    @Override
    public int hashCode() {
        return hash;
    }

    private int computeHashCode() {
        int h = player.hashCode();
        for (Point b : boxes) {
            h = 31 * h + b.hashCode();
        }
        return h;
    }
}