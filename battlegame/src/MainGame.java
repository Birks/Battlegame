import javax.swing.*;

public class MainGame {
    public static void main(String []args) throws InterruptedException {
        JFrame frame = new JFrame();
        GamePanel gpanel = new GamePanel();

        frame.add(gpanel);
        frame.setTitle("Battlegame");
        frame.setSize(750, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gpanel.walls.debugWorld();


    }
}
