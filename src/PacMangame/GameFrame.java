package PacMangame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class GameFrame extends JFrame implements Runnable, KeyListener {

    GamePanel gamePanel;
    Thread th;
    int flag = 1;

    public GameFrame(Map map) {
        setSize(507, 508);
        setResizable(false);
        gamePanel = new GamePanel(map);
        addKeyListener(this);
        add(gamePanel);
        th = new Thread(this);
        th.start();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                e.getWindow().dispose();
            }
        });

    }

    //start game
    @Override
    public void run() {

        while (!false) try {
            if (gamePanel.status == gamePanel.ALIVE || gamePanel.status == gamePanel.CHAOS) {
                gamePanel.update();
                gamePanel.repaint(); // vẽ lại frame
//                   System.out.println(gamePanel.status==8?"chaos":"not chaos");
                Thread.sleep(7);// 7 là thời gian delay trước khi vẽ lại
            }
            if (gamePanel.status == gamePanel.CHAOS) {
                gamePanel.status = gamePanel.ALIVE;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            flag += 1;
                            int sign = flag;

                            gamePanel.Chaos = true;
                            for (int i = 0; i < gamePanel.arrayEnemy.allEnemy.size(); i++) {
                                gamePanel.arrayEnemy.allEnemy.get(i).setKind(2);
                            }
                            Thread.sleep(5000);

                            if (flag == sign) {
                                for (int i = 0; i < gamePanel.arrayEnemy.allEnemy.size(); i++) {
                                    gamePanel.arrayEnemy.allEnemy.get(i).setKind(1);
                                }

                                gamePanel.Chaos = false;
                            }
                        } catch (InterruptedException ex) {
                            Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                }).start();
            }

        } catch (Exception ex) {
            Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // bộ nhận diện action với phím
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (gamePanel.status == gamePanel.DEAD) {
                try {
                    this.setVisible(false);
                    new GameFrame(new Map()).setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (gamePanel.status == gamePanel.WIN) {
                try {
                    this.setVisible(false);
                    new GameFrame(new Map()).setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try {
            gamePanel.getAKey(e.getKeyCode());
        } catch (Exception ex) {
            Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}

// màn hình chính của game
class GamePanel extends JPanel {

    // các hằng số định nghĩa các trạng thái, phương hướng cho dễ nhìn, dễ đoc
    int count;
    final int UP = 1;
    final int DOWN = 2;
    final int LEFT = 3;
    final int RIGHT = 4;
    final int ALIVE = 5;
    final int DEAD = 6;
    final int WIN = 7;
    final int CHAOS = 8;

    Map map;
    int status;
    ArrayFood arrayFood;
    ArrayEnemy arrayEnemy;
    Image pacMan;
    int x = 250;
    int y = 230;
    int direct = DOWN;
    int score = 0;
    boolean Chaos;
    boolean vis;
    boolean visPacman = true;
    int countPacMan = 0;

    //khởi tạo
    public GamePanel(Map map) {

        status = ALIVE;
        this.map = map;
        this.setSize(500, 500);
        arrayFood =  new ArrayFood();
        arrayEnemy = new ArrayEnemy(this.map);
        pacMan = new ImageIcon(this.getClass().getResource("up.png")).getImage();
//        System.out.println("new panel");

    }


    // hàm vẽ lên frame, FPS = 100
    @Override
    public void paint(Graphics g) {

        if (status == WIN) {
            pacMan = new ImageIcon(this.getClass().getResource("win.jpg")).getImage();
            g.drawImage(pacMan, 0, 0, 500, 500, this);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.setColor(Color.red);
            g.drawString("Score " + score, 170, 350);

            return;
        } else if (status == DEAD) {
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 70));
            g.drawString("YOU LOSE", 75, 100);
            g.setColor(Color.cyan);
            g.setColor(Color.red);
            g.drawString("Score " + score, 120, 330);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Space to restart", 180, 200);
        } else { // normal
            g.setColor(Color.black); // color of background
            g.fillRect(0, 0, this.getSize().width, this.getSize().height);

            // draw map
            g.setColor(Color.lightGray);
            for (int i = 0; i < 50; i++) {
                for (int j = 0; j < 50; j++) {
                    if (map.block[i][j]) {
                        g.fill3DRect(i * 10, j * 10, 10, 10, true);
                    }
                }
            }

            //draw foods
            g.setColor(Color.YELLOW);
            for (Food food : arrayFood.allFood) {
                g.setColor(Color.YELLOW);
                g.fillOval(food.x, food.y, 10 * food.score, 10 * food.score);

            }

            //draw enermy
            g.setColor(Color.orange);
            for (int i = 0; i < arrayEnemy.allEnemy.size(); i++) {
                if (arrayEnemy.allEnemy.get(i).kind == 1) {
                    g.drawImage(arrayEnemy.allEnemy.get(i).ic, arrayEnemy.allEnemy.get(i).x, arrayEnemy.allEnemy.get(i).y, 30, 30, this);
                } else {
                    g.drawImage(arrayEnemy.allEnemy.get(i).ic2, arrayEnemy.allEnemy.get(i).x, arrayEnemy.allEnemy.get(i).y, 30, 30, this);

                }
            }

            drawPacMan(g, x, y, Chaos);
            g.setColor(Color.yellow);
        }


    }

    // di chuyen, cap nhap vi tri của pacman
    public void update() throws Exception {
        if (arrayFood.allFood.size() == 0) {
            status = WIN;

            saveScore(map);

        } else if (arrayEnemy.check(x, y)) {
            status = DEAD;

            saveScore(map);

        } else {
            arrayEnemy.update();
            int s = arrayFood.check(x, y); // kiểm tra xem vị trí của pacman có trùng với food nào không
            if (s == -1) {
                status = WIN;
            } else if (s == 1) {
                score += s;
            } else if (s == 2) {
                score += s;
                status = CHAOS;
            }
            switch (direct) {
                case DOWN:
                    down();
                    break;
                case LEFT:
                    left();
                    break;
                case UP:
                    up();
                    break;
                case RIGHT:
                    right();
                    break;
                default:
                    System.out.println("error");
            }
        }
    }

    void left() {
        if (map.check((x + 500 - 1) % 500, y)) {
            x = (x + 500 - 1) % 500;
        }
    }

    void right() {
        if (map.check((x + 500 + 1) % 500, y)) {
            x = (x + 500 + 1) % 500;
        }
    }

    void down() {
        if (map.check(x, (y + 500 + 1) % 500)) {
            y = (y + 500 + 1) % 500;
        }
    }

    void up() {
        if (map.check(x, (y + 500 - 1) % 500)) {
            y = (y + 500 - 1) % 500;
        }
    }

    // lọc những trường hợp không di chuyển được
    void getAKey(int keyCode) throws Exception {

        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (map.check(x, (y + 500 - 1) % 500)) { // check(x,y) check xem vị trí có thỏa mãn để di chuyển tới không
                    direct = UP;
                }
                break;
            case KeyEvent.VK_W:
                if (map.check(x, (y + 500 - 1) % 500)) { // check(x,y) check xem vị trí có thỏa mãn để di chuyển tới không
                    direct = UP;
                }
                break;

            case KeyEvent.VK_DOWN:
                if (map.check(x, (y + 500 + 1) % 500)) {
                    direct = DOWN;
                }
                break;
            case KeyEvent.VK_S:
                if (map.check(x, (y + 500 + 1) % 500)) {
                    direct = DOWN;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (map.check((x + 500 - 1) % 500, y)) {
                    direct = LEFT;
                }
                break;
            case KeyEvent.VK_A:
                if (map.check((x + 500 - 1) % 500, y)) {
                    direct = LEFT;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (map.check((x + 500 + 1) % 500, y)) {
                    direct = RIGHT;
                }
                break;
            case KeyEvent.VK_D:
                if (map.check((x + 500 + 1) % 500, y)) {
                    direct = RIGHT;
                }
                break;

        }

    }

    public void saveScore(Map map) throws Exception {
        String filename = "C:\\Users\\vducd\\IntelliJ IDEA Community Edition 2018.2.3\\GameJava\\src\\PacMangame\\\\score.txt";
        int[] data = new int[6];
        Scanner sc = new Scanner(new File(filename));
        for (int i = 0; i < 5; i++) {
            data[i] = sc.nextInt();
        }
        sc.close();
        data[5] = this.score;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (data[i] > data[j]) {
                    int k = data[i];
                    data[i] = data[j];
                    data[j] = k;
                }
            }
        }
        FileWriter fw = new FileWriter(new File(String.valueOf(filename)));
        fw.flush();
        for (int i = 0; i < 5; i++) {
            fw.write(data[i] + "\n");
        }
        fw.close();

    }

    private void drawPacMan(Graphics g, int x, int y, boolean Chaos) {
        if (Chaos) {
            switch (direct) {
                case UP:
                    pacMan = new ImageIcon(this.getClass().getResource("Upchaos.png")).getImage();
                    break;
                case DOWN:
                    pacMan = new ImageIcon(this.getClass().getResource("Downchaos.png")).getImage();
                    break;
                case LEFT:
                    pacMan = new ImageIcon(this.getClass().getResource("Leftchaos.png")).getImage();
                    break;
                case RIGHT:
                    pacMan = new ImageIcon(this.getClass().getResource("Rightchaos.png")).getImage();
                    break;

                default:
                    pacMan = new ImageIcon(this.getClass().getResource("Rightchaos.png")).getImage();
                    break;

            }
        }
         else switch (direct) {
            case UP:
                pacMan = new ImageIcon(this.getClass().getResource("up.png")).getImage();
                break;
            case DOWN:
                pacMan = new ImageIcon(this.getClass().getResource("down.png")).getImage();
                break;
            case LEFT:
                pacMan = new ImageIcon(this.getClass().getResource("left.png")).getImage();
                break;
            case RIGHT:
                pacMan = new ImageIcon(this.getClass().getResource("right.png")).getImage();
                break;
            default:
                pacMan = new ImageIcon(this.getClass().getResource("right.png")).getImage();
                break;
        }

        g.setColor(Chaos ? Color.red : Color.yellow);
        if (countPacMan % 10 == 0) {
            visPacman = !visPacman;
            countPacMan++;
        } else {
            countPacMan++;
        }
        if (visPacman) {
            g.drawImage(pacMan, x - 15, y - 15, 30, 30, this);
        } else {
            g.fillOval(x - 15, y - 15, 30, 30);

        }

    }
}
