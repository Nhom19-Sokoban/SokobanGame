/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;
import java.awt.Point;
import java.util.*;
/**
 *
 * @author ASUS
 */
public class AStarSolver {
    private static final int[] dx = {0, 0, -1, 1}; // Up, Down, Left, Right
    private static final int[] dy = {-1, 1, 0, 0};
    private static final String[] directions = {"U", "D", "L", "R"};

    public List<String> solve(Manchoi manChoi) {
        SokobanState start = manchoiToState(manChoi);
        PriorityQueue<SokobanState> openSet = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost + s.heuristic()));
        Map<SokobanState, Integer> costMap = new HashMap<>(); // Lưu chi phí thấp nhất cho mỗi trạng thái
        Set<SokobanState> closedSet = new HashSet<>();

        openSet.add(start);
        costMap.put(start, 0);

        while (!openSet.isEmpty()) {
            SokobanState current = openSet.poll();

            // Kiểm tra mục tiêu
            if (current.isGoal()) {
                return reconstructPath(current);
            }

            // Kiểm tra deadlock
            if (current.isDeadlock()) {
                continue;
            }

            closedSet.add(current);

            // Thử các hướng di chuyển
            for (int i = 0; i < 4; i++) {
                SokobanState neighbor = move(current, dx[i], dy[i], directions[i]);
                if (neighbor == null || closedSet.contains(neighbor)) continue;

                int newCost = current.cost + 1;
                Integer existingCost = costMap.get(neighbor);

                if (existingCost == null || newCost < existingCost) {
                    neighbor.cost = newCost;
                    costMap.put(neighbor, newCost);
                    openSet.add(neighbor);
                }
            }
        }

        return Collections.emptyList(); // Không tìm được lời giải
    }

    private SokobanState manchoiToState(Manchoi manChoi) {
        char[][][] map = manChoi.getMap();
        Point player = manChoi.getPlayerPosition();
        List<Point> boxes = new ArrayList<>();
        for (int x = 0; x < map[1].length; x++) {
            for (int y = 0; y < map[1][0].length; y++) {
                if (map[1][x][y] == 'O') {
                    boxes.add(new Point(x, y));
                }
            }
        }
        return new SokobanState(map, player, boxes);
    }

    private SokobanState move(SokobanState state, int dx, int dy, String direction) {
        int newPlayerX = state.player.x + dx;
        int newPlayerY = state.player.y + dy;

        // Kiểm tra biên
        if (!inBounds(state, newPlayerX, newPlayerY)) return null;

        // Nếu là tường thì không đi
        if (state.map[0][newPlayerX][newPlayerY] == '#') return null;

        // Sao chép map khi cần thiết
        char[][][] newMap = state.getMapCopy();
        Point newPlayer = new Point(newPlayerX, newPlayerY);
        List<Point> newBoxes = new ArrayList<>(state.boxes.size());
        for (Point b : state.boxes) newBoxes.add(new Point(b));

        // Kiểm tra có hộp ở vị trí mới của người chơi không
        Point boxAt = null;
        for (Point b : newBoxes) {
            if (b.x == newPlayerX && b.y == newPlayerY) {
                boxAt = b;
                break;
            }
        }

        if (boxAt == null) {
            // Di chuyển người chơi
        } else {
            // Đẩy hộp
            int boxNewX = boxAt.x + dx;
            int boxNewY = boxAt.y + dy;
            if (!inBounds(state, boxNewX, boxNewY) || 
                state.map[0][boxNewX][boxNewY] == '#' || 
                containsBox(newBoxes, boxNewX, boxNewY)) {
                return null;
            }
            boxAt.x = boxNewX;
            boxAt.y = boxNewY;
        }

        SokobanState neighbor = new SokobanState(newMap, newPlayer, newBoxes);
        neighbor.parent = state;
        neighbor.moveHistory = state.moveHistory + direction;
        neighbor.cost = state.cost + 1;
        return neighbor;
    }

    private boolean inBounds(SokobanState state, int x, int y) {
        return x >= 0 && y >= 0 && x < state.map[0].length && y < state.map[0][0].length;
    }

    private boolean containsBox(List<Point> boxes, int x, int y) {
        for (Point b : boxes) {
            if (b.x == x && b.y == y) return true;
        }
        return false;
    }

    private List<String> reconstructPath(SokobanState state) {
        List<String> path = new ArrayList<>();
        while (state.parent != null) {
            String lastMove = state.moveHistory.substring(state.moveHistory.length() - 1);
            path.add(lastMove);
            state = state.parent;
        }
        Collections.reverse(path);
        return path;
    }
}