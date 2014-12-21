import javax.swing.*;

public class MainGame {
    public static void main(String []args) throws InterruptedException {
        JFrame frame = new JFrame();
        GamePanel gpanel = new GamePanel();
        frame.add(gpanel);
        frame.setTitle("Java game");
        frame.setSize(300, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        // the main loop
//        while (true) {
//            gpanel.repaint();
//            //gpanel.move();
//            Thread.sleep(5);
//        }
    }
}
