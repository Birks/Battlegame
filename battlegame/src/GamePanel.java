
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;


class GamePanel extends JPanel {
    private static final int ARRAY_WIDTH = 15;
    private static final int ARRAY_HEIGHT = 13;
    private int world[][];

    Walls walls;
    Bricks bricks;
    public Man man;
    public Com com1;
    public Com com2;
    public Com com3;
    public Com com4;
    Com[] coms;
    public int comCounter=14;
    Image bg;

    // Constructor
    public GamePanel() {
        world = new int[ARRAY_HEIGHT][ARRAY_WIDTH];
        bricks = new Bricks(world);
        walls = new Walls(world);
        man = new Man(world, this);

        URL imageURL = getClass().getResource("/bg.png");

        try {
            bg = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        coms=new Com[6];
        coms[0] = new Com(world, this, 7, 125, 125,0);
        coms[1] = new Com(world, this, 7, 365, 125,1);
        coms[2] = new Com(world, this, 7, 285, 285,2);
        coms[3] = new Com(world, this, 7, 405, 205,3);
        coms[4] = new Com(world, this, 7, 125, 365,4);
        coms[5] = new Com(world, this, 7, 445, 365,4);

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

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(bg,0,0,null);

        bricks.paint(g2d);
        walls.paint(g2d);
        man.paint(g2d);

        for (Com c : coms) {
            if (c != null)
                c.paint(g2d);
        }

    }


    // Updates the game
    public void update() {
        man.move();

        if (comCounter==0) {
            JOptionPane.showMessageDialog(null,"YOU WIN!");
            System.exit(0);
        }

        for (Com c : coms) {
            if (c != null) {
                c.move();
                c.putPlayerInMatrix();
            }
        }


        bricks.setBoundaries();
        walls.setWorldBlocks();

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
