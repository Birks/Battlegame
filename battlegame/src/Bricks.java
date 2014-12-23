import java.awt.*;
import java.util.Random;

class Bricks extends Blocks {
    private static final int BRICK = 3;
    Random randomnumber = new Random();

    // Constructor
    public Bricks(int[][] world) {
        super(world);
        BLOCK = BRICK;
        boundArr = new Rectangle[280];
        blockColor = Color.YELLOW;
        setWorldBlocks();
    }

    // Fills the world array with bricks
    void setWorldBlocks() {
        for (int k = 0; k < 280; k++) {
            int i = randomnumber.nextInt(13);
            int j = randomnumber.nextInt(15);
            if (world[i][j] == 0 && !(i == 1 && j == 1) && !(i == 2 && j == 1) && !(i == 1 && j == 2)) {
                world[i][j] = BLOCK;
                counter++;
            }
        }
        setBoundaries();
    }

    // Needed to explode the setWorldBlock because array needs to be refreshed
    public void setBoundaries(){
        counter = 0;
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                if (world[i][j] == BLOCK) {
                    boundArr[counter] = new Rectangle(j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                    counter++;
                }
            }
        }

    }


}
