import java.awt.*;

class Walls {
    private static final int BLOCK_SIZE = 40;
    private static final int WALL = 1;

    // The main array which contains the parts of the world
    private int world[][];

    // Constructor
    public Walls(int[][] world) {
        this.world = world;
        setWorldWalls();
    }


    // Fills the world array with walls
    private void setWorldWalls() {
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                if (i == 0 || j == 0 || i == world.length - 1 || j == world[i].length - 1 || (i % 2 == 0 && j % 2 == 0))
                    world[i][j] = 1;
            }
        }
    }

    // This part draws the walls on the screen
    public void paint(Graphics2D g) {
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                if (world[i][j] == 1)
                    g.fillRect(j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
            }
        }

    }


}
