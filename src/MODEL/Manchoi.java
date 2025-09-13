/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Manchoi
{

    private char[][][] map = new char[2][8][9];
    private Point p = new Point();
    private int level;

    public char[][][] getMap() {
        return map;
    }

    public Point getPlayerPosition() {
        return p;
    }

    public int getWidth() {
        return map[0].length;
    }

    public int getHeight() {
        return map[0][0].length;
    }

//    public char[][] getMapLayer0() {
//        return map[0];
//    }
//
//    public char[][] getMapLayer1() {
//        return map[1];
//    }
    public Manchoi(LevelSokoban lv) {
        this.level = Integer.parseInt(lv.getTenLV().split(" ")[1]); // lấy số trong "Level 1"
        map = new char[2][lv.getCot()][lv.getDong()];
        try {
            initFromFile(lv.getTenFile(), lv.getCot(), lv.getDong());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initFromFile(String filename, int cot, int dong) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        int y = 0;
        while ((line = br.readLine()) != null && y < dong) {
            for (int x = 0; x < line.length() && x < cot; x++) {
                char c = line.charAt(x);
                if (c == '#' || c == '.') {
                    map[0][x][y] = c;
                    map[1][x][y] = ' ';
                } else if (c == 'O' || c == 'H') {
                    map[0][x][y] = ' ';
                    map[1][x][y] = c;
                    if (c == 'H') {
                        p.x = x;
                        p.y = y;
                    }
                } else {
                    map[0][x][y] = ' ';
                    map[1][x][y] = ' ';
                }
            }
            y++;
        }
        br.close();
    }

    public void move(int d) {
        int dx = d == 37 ? -1 : d == 39 ? 1 : 0;
        int dy = d == 38 ? -1 : d == 40 ? 1 : 0;

        int newX = p.x + dx;
        int newY = p.y + dy;

        if (map[1][newX][newY] == 'O'
                && map[0][newX + dx][newY + dy] != '#'
                && map[1][newX + dx][newY + dy] == ' ') {
            map[1][newX][newY] = ' ';
            map[1][newX + dx][newY + dy] = 'O';
        }

        if (map[1][newX][newY] != 'O' && map[0][newX][newY] != '#') {
            map[1][p.x][p.y] = ' ';
            p.x = newX;
            p.y = newY;
        }
    }

    public boolean isCompleted() {
        for (int y = 0; y < map[0][0].length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[1][x][y] == 'O' && map[0][x][y] != '.') {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String out = "";
        for (int y = 0; y < map[0][0].length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                out += p.x == x && p.y == y ? 'H'
                        : map[1][x][y] != ' ' ? map[1][x][y] : map[0][x][y];
            }
            out += "\n";
        }
        return out;
    }

//    // Main test (ch?y console)
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Ch?n level (1, 2, ...): ");
//        int level = sc.nextInt();
//
//    }

}
