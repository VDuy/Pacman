package PacMangame;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
public class ArrayEnemy {

 ArrayList<Enemy> allEnemy;
    Map map;

    ArrayEnemy(Map map) {
        this.map = map;
        allEnemy = new ArrayList<>();
        allEnemy.add(new Enemy(100, 20, 1, "ghost1.png"));
        allEnemy.add(new Enemy(400, 20, 1, "ghost2.png"));
        allEnemy.add(new Enemy(20, 430, 1, "ghost3.png"));
        allEnemy.add(new Enemy(450, 430, 1, "ghost4.png"));


    }

    void update() {
        for (int i = 0; i < allEnemy.size(); i++) {

            boolean moved = false;

            while (!moved) {

                switch (allEnemy.get(i).direct) {
                    case 0:
                        if (allEnemy.get(i).moveDown()) {
                            allEnemy.get(i).y++;
                            moved = true;
                        } else {
                            allEnemy.get(i).direct = (int) (Math.random() * 4);
                        }
                        break;
                    case 1:
                        if (allEnemy.get(i).moveUp()) {
                            allEnemy.get(i).y--;
                            moved = true;
                        } else {
                            allEnemy.get(i).direct = (int) (Math.random() * 4);
                        }
                        break;
                    case 2:
                        if (allEnemy.get(i).moveLeft()) {
                            allEnemy.get(i).x--;
                            moved = true;
                        } else {
                            allEnemy.get(i).direct = (int) (Math.random() * 4);
                        }
                        break;
                    case 3:
                        if (allEnemy.get(i).moveRight()) {
                            allEnemy.get(i).x++;
                            moved = true;
                        } else {
                            allEnemy.get(i).direct = (int) (Math.random() * 4);
                        }
                        break;
                }
            }
        }
    }

    boolean check(int x, int y) {
        if (allEnemy.size() == 0) {
            return false;
        }
        // tinh toan x,y
        for (int i = 0; i < allEnemy.size(); i++) {
            int xx = allEnemy.get(i).x + 15;
            int yy = allEnemy.get(i).y + 15;

            if ((x - xx) * (x - xx) + (y - yy) * (y - yy) <= 900) {
                if (allEnemy.get(i).kind == 2) {
                    allEnemy.remove(i);
                    return false;
                }
                return true;
            }
        }

        //default
        return false;
    }

    class Enemy {

            public final int ENEMY = 1;
            public final int FOOD = 2;
            Image ic;
            Image ic2;

            public Enemy(int x, int y, int kind, String fileImage) {
                this.x = x;
                this.y = y;
                this.kind = kind;
                ic = new ImageIcon(this.getClass().getResource(fileImage)).getImage();
                ic2 =new ImageIcon(this.getClass().getResource("creep.png")).getImage();

            }

        public int x;
        public int y;
        public int kind;
        public int direct = 1;

        boolean moveUp() {
            return map.check(x + 15, y - 1 + 15);

        }

        boolean moveDown() {
            return map.check(x + 15, y + 15 + 1);
        }

        boolean moveLeft() {
            return map.check(x + 15 - 1, y + 15);
        }

        boolean moveRight() {
            return map.check(x + 1 + 15, y + 15);
        }

        public void setKind(int kind) {
            this.kind = kind;
        }
        public int getKind(){return this.kind;}
    }

}

