import javax.swing.*;

public class MainGame {
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();
        GamePanel gpanel = new GamePanel();

        frame.add(gpanel);
        frame.setTitle("Battlegame");
        frame.setResizable(false);
        frame.setSize(600 + 5, 650);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Main loop
        int counter = 0;
        while (true) {
            gpanel.repaint();
            gpanel.update();

            // To refresh the console less
            if (counter == 100) {
                //gpanel.debugWorld();
                counter = 0;
            }
            counter++;
            Thread.sleep(20);
        }


    }
}
