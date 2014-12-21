
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;


class GamePanel extends JPanel {
    private static final int ARRAY_WIDTH = 15;
    private static final int ARRAY_HEIGHT = 13;
    private static final int BLOCK_SIZE = 40;
    private static final int WALL = 1;
    private int world[][];

    private Walls walls;
    private Man man;

    // Constructor
    public GamePanel() {
        world = new int[ARRAY_HEIGHT][ARRAY_WIDTH];
        walls = new Walls(world);
        man = new Man(world);

        // Key recognizing part
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                man.keyReleased(e);

            }

            @Override
            public void keyPressed(KeyEvent e) {
                man.keyPressed(e);

            }
        });
        setFocusable(true);


    }


    // Tha Java2D main paint part
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.GRAY);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        walls.paint(g2d);
        man.paint(g2d);
    }


    // Updates the game
    public void update() {
        man.move();
        man.putPlayerInMatrix();
    }

    // Debugs the world matrix
    public void debugWorld() {
        for (int[] i : world) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

}
