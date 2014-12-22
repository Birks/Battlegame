import java.awt.*;
import java.util.Random;

class Bricks {
    private static final int BLOCK_SIZE = 40;
    private static int BRICK = 3;
    Rectangle[] boundArr = new Rectangle[82];
    int counter = 0;
    Random randomnumber = new Random();

    // The main array which contains the parts of the world
    private int world[][];

    // Constructor
    public Bricks(int[][] world) {
        this.world = world;
        setWorldBlocks();
    }

    // Fills the world array with bricks
    private void setWorldBlocks() {
        for (int k = 0; k < 250; k++) {
            int i=randomnumber.nextInt(13);
            int j=randomnumber.nextInt(15);
            if (world[i][j]==0) {
                world[i][j]=BRICK;
            }
        }

    }

    // This part draws the walls on the screen
    public void paint(Graphics2D g) {
        g.setColor(Color.YELLOW);
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                if (world[i][j] == BRICK) {
                    g.fillRect(j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
    }

    // Returns the array which contains the boundaries data
    public Rectangle[] getRectangleArr() {
        return boundArr;
    }

    // Returns the size of the wall constant
    public static int getBlockSize() {
        return BLOCK_SIZE;
    }
}
