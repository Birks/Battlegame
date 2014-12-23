import java.awt.*;

class Blocks {
    protected static final int BLOCK_SIZE = 40;
    protected int BLOCK;
    Color blockColor;
    int counter = 0;

    Rectangle[] boundArr;

    // The main array which contains the parts of the world
    protected int world[][];

    // Constructor
    public Blocks(int[][] world) {
        this.world = world;
    }


    // This part draws the blocks on the screen
    public void paint(Graphics2D g) {
        g.setColor(blockColor);
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                if (world[i][j] == BLOCK) {
                    g.fillRect(j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
    }


    // Returns the array which contains the boundaries data
    public Rectangle[] getRectangleArr() {
        return boundArr;
    }

    // Returns the size of the block size constant
    public static int getBlockSize() {
        return BLOCK_SIZE;
    }

}
