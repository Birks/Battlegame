import java.awt.*;
import java.awt.event.KeyEvent;

class Man {

    private static final int PLAYER = 2;
    private static final int START_X = 45;
    private static final int START_Y = 45;
    private static final int PLAYER_SIZE = 30;

    private GamePanel game;
    private int x;
    private int y;
    private int xa = 0;
    private int ya = 0;


    // The main array which contains the parts of the world
    private int world[][];

    // Constructor
    public Man(int[][] world, GamePanel game) {
        this.world = world;
        this.game = game;
        x = START_X;
        y = START_Y;
    }

    // Moving the player in x and y directions
    public void move() {

        if (checkCollision()) {
            xa = 0;
            ya = 0;
            y += 2;
            if (checkCollision()) {
                y -= 3;
            }
            x += 2;
            if (checkCollision()) {
                x -= 3;
            }
        }
        x = x + xa;
        y = y + ya;
    }

    // Checks does the player collide with the walls
    public boolean checkCollision() {
        boolean isCollide = false;
        Rectangle[] boundArr = game.walls.getRectangleArr();
        for (Rectangle item : boundArr) {
            isCollide = getBounds().intersects(item);
            if (isCollide)
                break;
        }
        return isCollide;
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
        g.fillRect(x, y, PLAYER_SIZE, PLAYER_SIZE);

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
        int j = Math.round((x + PLAYER_SIZE / 2) / Walls.getBlockSize()); // Static
        int i = Math.round((y + PLAYER_SIZE / 2) / Walls.getBlockSize());
        world[i][j] = PLAYER;

    }

    // Returns the player object boundary
    public Rectangle getBounds() {
        return new Rectangle(x, y, PLAYER_SIZE, PLAYER_SIZE);
    }



}
