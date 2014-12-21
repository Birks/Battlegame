import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel {
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.BLUE);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
//        ball.paint(g2d);
//        racquet.paint(g2d);
//        racquetZwei.paint(g2d);
    }
}
