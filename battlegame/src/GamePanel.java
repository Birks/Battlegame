
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


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

    // Constructor
    public GamePanel() {
        world = new int[ARRAY_HEIGHT][ARRAY_WIDTH];
        bricks=new Bricks(world);
        walls = new Walls(world);
        man = new Man(world, this);

        com1= new Com(world,this,7,125,125);
        com2= new Com(world,this,7,365,125);
        com3= new Com(world,this,7,285,285);
        com4= new Com(world,this,7,405,205);

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



        bricks.paint(g2d);
        walls.paint(g2d);
        man.paint(g2d);
        com1.paint(g2d);
        com2.paint(g2d);
        com3.paint(g2d);
        com4.paint(g2d);
    }


    // Updates the game
    public void update() {
        man.move();
        com1.move();
        com2.move();
        com3.move();
        com4.move();


        bricks.setBoundaries();
        walls.setWorldBlocks();

        man.putPlayerInMatrix();

        com1.putPlayerInMatrix();
        com2.putPlayerInMatrix();
        com3.putPlayerInMatrix();
        com4.putPlayerInMatrix();



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
