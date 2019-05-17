package PacMangame;


import java.util.ArrayList;

class Food {

    int x;
    int y;
    int score;

    public Food(int x, int y, int score) {
        this.x = 10 * x;
        this.y = 10 * y;
        this.score = score;
    }

}

public class ArrayFood {

    ArrayList<Food> allFood;

    public ArrayFood() {
        allFood = new ArrayList<>();

        addNew(2, 2, 2);
        addNew(2, 5, 1);
        addNew(2, 9, 1);
        addNew(2, 14, 1);
        addNew(2, 18, 1);
        addNew(2, 21, 1);
        addNew(2, 24, 2);
        addNew(2, 29, 1);
        addNew(2, 33, 1);
        addNew(2, 33, 1);
        addNew(2, 36, 1);
        addNew(2, 39, 1);
        addNew(2, 42, 1);
        addNew(2, 44, 2);

        addNew(5, 2, 1);
        addNew(5, 9, 1);
        addNew(5, 14, 1);
        addNew(5, 18, 1);
        addNew(5, 24, 1);
        addNew(5, 29, 1);
        addNew(5, 33, 1);
        addNew(5, 39, 1);
        addNew(5, 42, 1);
        addNew(5, 45, 1);

        addNew(8, 2, 1);
        addNew(8, 9, 1);
        addNew(8, 14, 1);
        addNew(8, 18, 1);
        addNew(8, 24, 1);
        addNew(8, 33, 1);
        addNew(8, 42, 1);
        addNew(8, 45, 1);

        addNew(11, 2, 1);
        addNew(11, 5, 1);
        addNew(11, 9, 1);
        addNew(11, 14, 1);
        addNew(11, 18, 1);
        addNew(11, 24, 1);
        addNew(11, 29, 1);
        addNew(11, 33, 1);
        addNew(11, 33, 1);
        addNew(11, 36, 1);
        addNew(11, 39, 1);
        addNew(11, 42, 1);
        addNew(11, 45, 1);

        addNew(16, 2, 1);
        addNew(16, 9, 1);
        addNew(16, 14, 1);
        addNew(16, 18, 1);
        addNew(16, 21, 1);
        addNew(16, 24, 1);
        addNew(16, 29, 1);
        addNew(16, 33, 1);
        addNew(16, 33, 1);
        addNew(16, 36, 1);
        addNew(16, 39, 1);
        addNew(16, 42, 1);
        addNew(16, 45, 1);

        addNew(21, 2, 1);
        addNew(21, 5, 1);
        addNew(21, 9, 1);
        addNew(21, 14, 1);
        addNew(21, 18, 1);
        addNew(21, 24, 1);
        addNew(21, 29, 1);
        addNew(21, 33, 1);
        addNew(21, 33, 1);
        addNew(21, 36, 1);
        addNew(21, 42, 1);
        addNew(21, 45, 1);
        addNew(24, 5, 2);
        addNew(24, 18, 2);
        addNew(24, 21, 1);
        addNew(24, 45, 2);

        addNew(28, 2, 1);
        addNew(28, 5, 1);
        addNew(28, 9, 1);
        addNew(28, 14, 1);
        addNew(28, 18, 1);
        addNew(28, 24, 1);
        addNew(28, 29, 1);
        addNew(28, 33, 1);
        addNew(28, 33, 1);
        addNew(28, 36, 1);
        addNew(28, 42, 1);
        addNew(28, 45, 1);

        addNew(33, 2, 1);
        addNew(33, 9, 1);
        addNew(33, 14, 1);
        addNew(33, 18, 1);
        addNew(33, 21, 1);
        addNew(33, 24, 1);
        addNew(33, 29, 1);
        addNew(33, 33, 1);
        addNew(33, 33, 1);
        addNew(33, 36, 1);
        addNew(33, 39, 1);
        addNew(33, 42, 1);
        addNew(33, 45, 1);

        addNew(39, 2, 1);
        addNew(39, 5, 1);
        addNew(39, 9, 1);
        addNew(39, 14, 1);
        addNew(39, 18, 1);
        addNew(39, 24, 1);
        addNew(39, 29, 1);
        addNew(39, 33, 1);
        addNew(39, 33, 1);
        addNew(39, 36, 1);
        addNew(39, 39, 1);
        addNew(39, 42, 1);
        addNew(39, 45, 1);

        addNew(43, 2, 1);
        addNew(43, 9, 1);
        addNew(43, 14, 1);
        addNew(43, 18, 1);
        addNew(43, 24, 1);
        addNew(43, 29, 1);
        addNew(43, 33, 1);
        addNew(43, 39, 1);
        addNew(43, 42, 1);
        addNew(43, 45, 1);

        addNew(46, 2, 2);
        addNew(47, 5, 1);
        addNew(47, 9, 1);
        addNew(47, 14, 1);
        addNew(47, 18, 1);
        addNew(47, 21, 1);
        addNew(46, 24, 2);
        addNew(47, 29, 1);
        addNew(47, 33, 1);
        addNew(47, 33, 1);
        addNew(47, 36, 1);
        addNew(47, 39, 1);
        addNew(47, 42, 1);
        addNew(46, 44, 2);


    }

    public final void addNew(int x, int y, int score) {
        allFood.add(new Food(x, y, score));
    }

    int check(int x, int y) {
        if (allFood.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < allFood.size(); i++) {

            int fs = allFood.get(i).score;
            int fx = allFood.get(i).x + fs * 5;
            int fy = allFood.get(i).y + fs * 5;
            if ((x - fx) * (x - fx) + (y - fy) * (y - fy) <= (15 + fs * 5) * (15 + fs * 5)) {
                allFood.remove(i);
//                System.out.println("fx: " + fx);
//                System.out.println("fy: " + fy);
//                System.out.println("fs: " + fs);
//                System.out.println("x: " + x);
//                System.out.println("y: " + y);
                return fs;
            }
        }
        return 0;
    }
}

