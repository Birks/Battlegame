import java.awt.*;

public class Walls {
    // The main array which contains the parts of the world
    private static final int ARRAY_WIDTH = 15;
    private static final int ARRAY_HEIGHT = 13;
    private static final int WALL = 1;
    private int world[][];

    // Constructor
    public Walls() {
        world = new int[ARRAY_HEIGHT][ARRAY_WIDTH];
        setWorldWalls();
    }


    // Fills the world array with walls
    private void setWorldWalls() {
        for (int i = 0; i < ARRAY_HEIGHT; i++) {
            for (int j = 0; j < ARRAY_WIDTH; j++) {
                if (i == 0 || j == 0 || i == ARRAY_HEIGHT - 1 || j == ARRAY_WIDTH - 1 || (i % 2 == 0 && j % 2 == 0))
                    world[i][j] = 1;
            }
        }
    }

    // This part draws the walls on the screen
    public void paint(Graphics2D g) {
        for (int i = 0; i < ARRAY_HEIGHT; i++) {
            for (int j = 0; j < ARRAY_WIDTH; j++) {
                if (world[i][j] == 1)
                    g.fillRect(j * 50, i * 50, 50, 50);
            }
        }

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
