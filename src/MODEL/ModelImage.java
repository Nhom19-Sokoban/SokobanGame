/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author ASUS
 */
public class ModelImage extends JPanel {

    private Manchoi manChoi;

    // ?nh icon
    private Image imgWall, imgGoal, imgBox, imgPlayer, imgFloor;

    final int TILE_SIZE = 32; // kích th??c m?i ô 32x32 px

    public void setModel(Manchoi manChoi) {
        this.manChoi = manChoi;
        setPreferredSize(new Dimension(manChoi.getWidth() * TILE_SIZE, manChoi.getHeight() * TILE_SIZE));
        revalidate();
        repaint();

    }

    public ModelImage(Manchoi manChoi) {
        this.manChoi = manChoi;
        loadImages();
        setPreferredSize(new Dimension(manChoi.getWidth() * TILE_SIZE, manChoi.getHeight() * TILE_SIZE));
    }

    private void loadImages() {
        try {
            imgWall = ImageIO.read(getClass().getResource("/images/wall.jpg"));
            imgGoal = ImageIO.read(getClass().getResource("/images/goal.png"));
            imgBox = ImageIO.read(getClass().getResource("/images/box.jpg"));
            imgPlayer = ImageIO.read(getClass().getResource("/images/player.png"));
            imgFloor = ImageIO.read(getClass().getResource("/images/floor.png"));
        } catch (IOException e) {
            e.printStackTrace();
            // N?u load ?nh l?i thì có th? t?o ?nh thay th? ho?c báo l?i
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        char[][][] map = manChoi.getMap(); // b?n t?o getter tr? v? map (tham kh?o bên d??i)
        Point p = manChoi.getPlayerPosition(); // getter tr? v? v? trí player

        for (int y = 0; y < manChoi.getHeight(); y++) {
            for (int x = 0; x < manChoi.getWidth(); x++) {
                // V? sàn ho?c t??ng (map[0])
                char c0 = map[0][x][y];
                Image baseImg = imgFloor; // m?c ??nh là sàn
                if (c0 == '#') {
                    baseImg = imgWall;
                } else if (c0 == '.') {
                    baseImg = imgGoal;
                }

                g.drawImage(baseImg, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);

                // V? ??i t??ng trên sàn (map[1])
                char c1 = map[1][x][y];
                if (c1 == 'O') {
                    g.drawImage(imgBox, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                } else if (p.x == x && p.y == y) {
                    g.drawImage(imgPlayer, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                }
                // else là kho?ng tr?ng, không v? gì thêm
            }
        }
    }

}
