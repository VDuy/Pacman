package PacMangame;

public class Pacman {

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater( new Runnable() {
            public void run() {
                new MainMenu().setVisible(true); 

            }
        });
    }
}