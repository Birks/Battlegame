import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

class Walls extends Blocks {
    public static final int WALL = 1;

    // Constructor
    public Walls(int[][] world) {
        super(world);
        BLOCK = WALL;
        boundArr = new Rectangle[82];
        blockColor = Color.GRAY;
        setWorldBlocks();

        URL imageURL = getClass().getResource("/img-wall.png");

        try {
            img = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Fills the world array with walls
    void setWorldBlocks() {
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                if (i == 0 || j == 0 || i == world.length - 1 || j == world[i].length - 1 || (i % 2 == 0 && j % 2 == 0)) {
                    world[i][j] = BLOCK;
                    boundArr[counter] = new Rectangle(j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                    counter++;
                }
            }
        }
        counter=0;
    }




}
