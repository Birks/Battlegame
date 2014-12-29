import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

class Bricks extends Blocks {
    public static final int BRICK = 3;
    Random randomnumber = new Random();

    // Constructor
    public Bricks(int[][] world) {
        super(world);
        BLOCK = BRICK;
        blockColor = Color.YELLOW;
        setWorldBlocks();

        URL imageURL = getClass().getResource("/img-bricks.png");

        try {
            img = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Fills the world array with bricks
    void setWorldBlocks() {
        for (int k = 0; k < 400; k++) {
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
        boundArr = new Rectangle[280];
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

    // Returns the array which contains the boundaries data
    public Rectangle[] getRectangleArr() {
        return boundArr;
    }

}
