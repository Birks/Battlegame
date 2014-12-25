import org.apache.commons.lang.ArrayUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

class Man {

    public static final int PLAYER = 2;
    private static final int START_X = 45;
    private static final int START_Y = 45;
    private static final int PLAYER_SIZE = 30;

    private GamePanel game;
    private int x;
    private int y;
    private int speed=2;
    private int xa = 0;
    private int ya = 0;
    private boolean booleanBombPlaced = false;
    private boolean thirdBoolBomb = false;
    private int exp_length = 1;
    Bomb bomb;
    Powerup[] powerupArr;
    Random chance = new Random();


    // The main array which contains the parts of the world
    private int world[][];

    // Constructor
    public Man(int[][] world, GamePanel game) {
        this.world = world;
        this.game = game;
        x = START_X;
        y = START_Y;

        powerupArr = new Powerup[15];

    }

    // Moving the player in x and y directions
    public void move() {

        checkPowerUpCollision();

        if (checkExplosionCollision()) {
            JOptionPane.showMessageDialog(null, "You are dead...");
        }

        if (checkCollision()) {
            xa = 0;
            ya = 0;
            y += speed;
            if (checkCollision()) {
                y -= speed+1;
            }
            x += 2;
            if (checkCollision()) {
                x -= speed+1;
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
            thirdBoolBomb = true;
        }

        for (Rectangle item : boundArr) {
            // Check does the item is zero (last items in array could be 0)
            if (item != null) {

                // Complex statements for bomb placement check
                if (thirdBoolBomb) {
                    isCollide = getBounds().intersects(bomb.getBounds());
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

    // Does the player interracts with explosion
    public boolean checkExplosionCollision() {
        boolean isExpCollide = false;
        if (bomb != null && bomb.isExplosion) {
            Rectangle[] expBoundArr = bomb.getRectangleArr();
            for (Rectangle item : expBoundArr) {
                if (item != null) {
                    //System.out.println(item);
                    isExpCollide = getBounds().intersects(item);
                    if (isExpCollide)
                        break;
                }
            }
            //System.out.println();

        }
        return isExpCollide;
    }

    // Check does the player interract with powerup
    public void checkPowerUpCollision() {
        for (int i = 0; i < 15; i++) {
            if (powerupArr[i] != null) {
                if (getBounds().intersects(powerupArr[i].getBounds())) {
                    switch (powerupArr[i].type) {
                        case Powerup.ROLLER:
                            //System.out.println("increase speed");
                            speed++;
                            powerupArr[i] = null;
                            break;
                        case Powerup.FIRE:
                            //System.out.println("exp length");
                            exp_length++;
                            powerupArr[i] = null;
                            break;
                    }
                }
            }
        }

    }

    public void keyReleased(KeyEvent e) {
        xa = 0;
        ya = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            xa = -speed;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            xa = speed;
        if (e.getKeyCode() == KeyEvent.VK_UP)
            ya = -speed;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            ya = speed;
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!booleanBombPlaced) {
                bomb = new Bomb(world, this, getPlayerArrayRow(), getPlayerArrayColumn(), exp_length);
            }
        }

    }

    // This part draws the player on the screen
    public void paint(Graphics2D g) {
        if (bomb != null)
            bomb.paint(g);

        for (Powerup item : powerupArr) {
            if (item != null)
                item.paint(g);
        }
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
        if (bomb != null) {
            if (!getBounds().intersects(bomb.getBounds())) {
                booleanBombPlaced = true;
            } else
                booleanBombPlaced = false;
        } else
            booleanBombPlaced = false;
    }

    // This destroys the explosion and the bomb object
    public void destroyBomb() {
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                if (world[i][j] == Bomb.EXPLOSION) {
                    // Add powerup sometimes
                    if (chance.nextInt(100)<(10-speed)) {
                        for (int k=0; k<powerupArr.length; k++) {
                            if (powerupArr[k]==null) {
                                if (chance.nextInt(100)<20)
                                    powerupArr[k]= new Powerup(world, 5, i, j);
                                else
                                    powerupArr[k]= new Powerup(world, 6, i, j);
                                break;
                            } else
                            world[i][j] = 0;
                        }
                    }
                    else {
                        world[i][j] = 0;
                    }
                }
            }
        }
        bomb = null;
        booleanBombPlaced = false;
        thirdBoolBomb = false;
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
