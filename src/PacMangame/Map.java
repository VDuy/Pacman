package PacMangame;


class Map {

    public boolean[][]
            block = new boolean[50][50];

    public void drawVertical(int x, int y1, int y2) {
        if (y1 > y2) {
            int temp = y1;
            y1 = y2;
            y2 = temp;
        }
        for (int i = y1; i <= y2; i++) {
            block[x][i] = true;
        }
    }

    public void drawHorizontal(int y, int x1, int x2) {
        if (x1 > x2) {
            int temp = x1;
            x1 = x2;
            x2 = temp;
        }
        for (int i = x1; i <= x2; i++) {
            block[i][y] = true;
        }
    }

    public boolean check(int x, int y) {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (block[i][j]) {
                    int xx = i * 10;
                    int yy = j * 10;
                    if (x < xx + 25
                            && x > xx - 15
                            && y < yy + 25
                            && y > yy - 15) {
                        return false;
                    }

                }
            }
        }
        return true;
    }

    Map() {
        drawHorizontal(0, 1, 49);
        drawHorizontal(1, 24, 25);
        drawHorizontal(2, 24, 25);
        drawHorizontal(3, 24, 25);
        drawHorizontal(5, 5, 8);
        drawHorizontal(5, 13, 18);
        drawHorizontal(5, 31, 36);
        drawHorizontal(5, 41, 44);
        drawHorizontal(6, 5, 8);
        drawHorizontal(6, 13, 18);
        drawHorizontal(6, 31, 36);
        drawHorizontal(6, 41, 44);
        drawHorizontal(7, 5, 8);
        drawHorizontal(7, 13, 18);
        drawHorizontal(7, 31, 36);
        drawHorizontal(7, 41, 44);
        drawHorizontal(9, 24, 25);
        drawHorizontal(10, 24, 25);
        drawHorizontal(11, 24, 25);
        drawHorizontal(12, 5, 8);
        drawHorizontal(12, 19, 30);
        drawHorizontal(12, 41, 44);
        drawHorizontal(16, 0, 3);
        drawHorizontal(16, 13, 18);
        drawHorizontal(16, 31, 36);
        drawHorizontal(16, 46, 49);
        drawHorizontal(21, 5, 13);
        drawHorizontal(21, 18, 21);
        drawHorizontal(21, 28, 31);
        drawHorizontal(21, 36, 44);
        drawHorizontal(26, 5, 8);
        drawHorizontal(26, 18, 31);
        drawHorizontal(26, 41, 44);
        drawHorizontal(31, 0, 4);
        drawHorizontal(31, 13, 18);
        drawHorizontal(31, 31, 36);
        drawHorizontal(31, 45, 49);
        drawHorizontal(36, 5, 8);
        drawHorizontal(36, 41, 44);
        drawHorizontal(39, 19, 30);
        drawHorizontal(47, 1, 48);
        drawVertical(0, 0, 49);
        drawVertical(24, 10, 15);
        drawVertical(25,10,15);
        drawVertical(36, 13, 15);
        drawVertical(13, 22, 25);
        drawVertical(18, 22, 25);
        drawVertical(31, 22, 25);
        drawVertical(36, 22, 26);
        drawVertical(13, 32, 36);
        drawVertical(36, 32, 36);
        drawVertical(8, 37, 40);
        drawVertical(41, 37, 40);
        drawVertical(24, 36, 43);
        drawVertical(25, 36, 43);
        drawVertical(13, 41, 43);
        drawVertical(36, 41, 43);
        drawVertical(49,0,47);
    }
}