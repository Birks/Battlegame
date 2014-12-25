import org.apache.commons.lang.ArrayUtils;

import java.awt.*;
import java.awt.event.KeyEvent;

class Man {

    public static final int PLAYER = 2;
    private static final int START_X = 45;
    private static final int START_Y = 45;
    private static final int PLAYER_SIZE = 30;

    private GamePanel game;
    private int x;
    private int y;
    private int xa = 0;
    private int ya = 0;
    private boolean booleanBombPlaced=false;
    private boolean thirdBoolBomb = false;
    Bomb bomb;


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
        Rectangle[] boundArr = (Rectangle[]) ArrayUtils.addAll(game.walls.getRectangleArr(), game.bricks.getRectangleArr());

        // Required for bomb check
        if (!booleanBombPlaced) {

            checkBombPlaced();
        } else {
            thirdBoolBomb =true;
        }

        for (Rectangle item : boundArr) {
            // Check does the item is zero (last items in array could be 0)
            if (item != null) {

                // Complex statements for bomb placement check
                if (thirdBoolBomb) {
                  isCollide= getBounds().intersects(bomb.getBounds());
                  if (!isCollide)
                      isCollide = getBounds().intersects(item);
                } else
                isCollide = getBounds().intersects(item);
                if (isCollide)
                    break;
            }
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
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {

            bomb = new Bomb(world, this, getPlayerArrayRow(), getPlayerArrayColumn(),3);
        }

    }

    // This part draws the player on the screen
    public void paint(Graphics2D g) {
        if (bomb != null)
            bomb.paint(g);
        g.setColor(Color.BLUE);
        g.fillRect(x, y, PLAYER_SIZE, PLAYER_SIZE);


    }

    // Returns the row where is the player this moment
    public int getPlayerArrayRow() {
        int ret = 0;
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                if (world[i][j] == PLAYER)
                    ret = i;
            }
        }
        return ret;
    }

    // Returns the column where is the player this moment
    public int getPlayerArrayColumn() {
        int ret = 0;
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                if (world[i][j] == PLAYER)
                    ret = j;
            }
        }
        return ret;
    }

    // Does the player standing on bomb
    public void checkBombPlaced() {
        if(bomb!=null) {
              if (!getBounds().intersects(bomb.getBounds())){
                  booleanBombPlaced = true;
              } else
                  booleanBombPlaced= false;
        } else
        booleanBombPlaced= false;
    }

    // This destroys the explosion and the bomb object
    public void destroyBomb() {
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                if (world[i][j] == Bomb.EXPLOSION)
                    world[i][j] = 0;
            }
        }
        bomb=null;
        booleanBombPlaced=false;
        thirdBoolBomb =false;
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
