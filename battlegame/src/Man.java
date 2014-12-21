import java.awt.*;
import java.awt.event.KeyEvent;

class Man {
    private static final int PLAYER = 2;
    private static final int START_X = 45;
    private static final int START_Y = 45;
    private static final int BLOCK_SIZE = 30;

    private int x;
    private int y;
    private int xa = 0;
    private int ya = 0;

    // The main array which contains the parts of the world
    private int world[][];

    // Constructor
    public Man(int[][] world) {
        this.world = world;
        x = START_X;
        y = START_Y;
    }

    // Moving the player in x and y directions
    public void move() {
        x = x+xa;
        y = y+ya;
    }


    public void keyReleased(KeyEvent e) {
        xa = 0;
        ya = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            xa = -2;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            xa = 2;
        if (e.getKeyCode() == KeyEvent.VK_UP)
            ya = -2;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            ya = 2;
    }

    // This part draws the player on the screen
    public void paint(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);

    }

    // Puts the PLAYER (2) number in the matrix
    public void putPlayerInMatrix() {
        // Clearing the previous player location
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                if (world[i][j] == PLAYER)
                    world[i][j] = 0;
            }
        }

        // New player location
        int j = Math.round((x+BLOCK_SIZE/2) / 40); // Wall block size
        int i = Math.round((y+BLOCK_SIZE/2) / 40);
        world[i][j] = PLAYER;

    }


}
