import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Bomb {
    private static final int BOMB = 8;
    public static final int EXPLOSION = 4;
    private static final int BLOCK_SIZE = 40;
    private int bomb_row;
    private int bomb_column;
    private int x;
    private int y;
    private int exp_length;
    public boolean isExplosion = false;
    private Man player;
    Timer timerStart;
    Timer timerEnd;
    int counter = 0;

    Rectangle[] boundArr;


    // The main array which contains the parts of the world
    private int world[][];

    // Constructor
    public Bomb(int[][] world, Man player, int player_row, int player_column, int exp_length) {
        this.player = player;
        this.world = world;
        bomb_row = player_row;
        bomb_column = player_column;
        this.exp_length = exp_length;
        setBombXY();

        timerStart = new Timer();
        timerStart.schedule(new startTimer(), 5 * 1000);

    }

    // When bomb placed it starts to count for 5 sec
    class startTimer extends TimerTask {
        public void run() {
            makeExplosion();
            timerStart.cancel(); //Terminate the thread
        }
    }

    // When explosion made, 2 second interval starts
    class endExplosion extends TimerTask {
        public void run() {
            player.destroyBomb();
            timerEnd.cancel(); //Terminate the thread
        }
    }

    // To fill the array with explosion (4)
    private void makeExplosion() {
        isExplosion = true;
        boolean stopTop = false;
        boolean stopBottom = false;
        boolean stopLeft = false;
        boolean stopRight = false;

        // To increase the length of the explosion
        for (int i = 1; i <= exp_length; i++) {

            // To explode only one brick top
            if (!stopTop) {
                // Check walls for top
                if (bomb_row - i >= 1) {
                    if (world[bomb_row - i][bomb_column] != Walls.WALL && world[bomb_row - 1][bomb_column] != Walls.WALL) {
                        if (world[bomb_row - i][bomb_column] == Bricks.BRICK) {
                            stopTop = true;
                        }
                        world[bomb_row - i][bomb_column] = EXPLOSION;
                    }
                }
            }

            // To explode only one brick bottom
            if (!stopBottom) {
                // Check walls for bottom
                if (bomb_row + i <= 11) {
                    if (world[bomb_row + i][bomb_column] != Walls.WALL && world[bomb_row + 1][bomb_column] != Walls.WALL) {
                        if (world[bomb_row + i][bomb_column] == Bricks.BRICK) {
                            stopBottom = true;
                        }
                        world[bomb_row + i][bomb_column] = EXPLOSION;
                    }
                }
            }


            // To explode only one brick left
            if (!stopLeft) {
                // Check walls for left
                if (bomb_column - i >= 1) {
                    if (world[bomb_row][bomb_column - i] != Walls.WALL && world[bomb_row][bomb_column - 1] != Walls.WALL) {
                        if (world[bomb_row][bomb_column - i] == Bricks.BRICK) {
                            stopLeft = true;
                        }
                        world[bomb_row][bomb_column - i] = EXPLOSION;
                    }
                }
            }

            //To explode only one brick right
            if (!stopRight) {
                // Check walls for right
                if (bomb_column + i <= 13) {
                    if (world[bomb_row][bomb_column + i] != Walls.WALL && world[bomb_row][bomb_column + 1] != Walls.WALL) {
                        if (world[bomb_row][bomb_column + i] == Bricks.BRICK) {
                            stopRight = true;
                        }
                        world[bomb_row][bomb_column + i] = EXPLOSION;
                    }
                }
            }

        }


        world[bomb_row][bomb_column] = EXPLOSION;
        setBoundaries();
        timerEnd = new Timer();
        timerEnd.schedule(new endExplosion(), 2 * 1000);
    }

    // Sets the location of the bomb for a later paint
    public void setBombXY() {
        x = bomb_column * BLOCK_SIZE;
        y = bomb_row * BLOCK_SIZE;
    }


    // This part draws the player on the screen
    public void paint(Graphics2D g) {
        if (isExplosion) {
            g.setColor(Color.ORANGE);
            for (int i = 0; i < world.length; i++) {
                for (int j = 0; j < world[i].length; j++) {
                    if (world[i][j] == EXPLOSION)
                        g.fillRect(j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
        }


    }

    public void setBoundaries(){
        boundArr = new Rectangle[30];
        counter = 0;
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                if (world[i][j] == EXPLOSION) {

                    boundArr[counter] = new Rectangle(j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                    counter++;
                } else if (world[i][j]==Man.PLAYER) {
                    // When player is in the explosion
                    JOptionPane.showMessageDialog(null, "You are dead...");
                }
            }
        }

    }

    // Returns the array which contains the boundaries data
    public Rectangle[] getRectangleArr() {
        return boundArr;
    }

    // Returns the bomb boundary
    public Rectangle getBounds() {
        return new Rectangle(x, y, BLOCK_SIZE, BLOCK_SIZE);
    }


}

