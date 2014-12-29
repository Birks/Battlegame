import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

class Powerup extends Blocks {
    public static final int ROLLER = 5;
    public static final int FIRE = 6;
    public int type;
    private int powerup_row, powerup_column;
    private int x;
    private int y;


    public Powerup(int[][] world, int type, int powerup_row, int powerup_column) {
        super(world);
        this.powerup_row = powerup_row;
        this.powerup_column = powerup_column;
        this.type = type;

        switch (type) {
            case 5:
                BLOCK = ROLLER;
                blockColor = Color.CYAN;
                break;
            case 6:
                BLOCK = FIRE;
                blockColor = Color.MAGENTA;
                break;
        }

        URL imageURL1 = getClass().getResource("/img-roller.png");
        URL imageURL2 = getClass().getResource("/img-fire.png");

        try {
            if (BLOCK == ROLLER)
                img = ImageIO.read(imageURL1);
            else
                img = ImageIO.read(imageURL2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        world[powerup_row][powerup_column] = BLOCK;
        setPowerupXY();

    }

    public void setPowerupXY() {
        x = powerup_column * BLOCK_SIZE;
        y = powerup_row * BLOCK_SIZE;
    }

    // Returns the powerup boundary
    public Rectangle getBounds() {
        return new Rectangle(x, y, BLOCK_SIZE, BLOCK_SIZE);
    }
}
