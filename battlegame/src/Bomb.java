import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Bomb {
    private static final int BOMB = 8;
    private static final int EXPLOSION =4;
    private static final int BLOCK_SIZE = 40;
    private int bomb_row;
    private int bomb_column;
    private int x;
    private int y;
    private boolean isExplosion = false;
    private Man player;
    Timer timerStart;
    Timer timerEnd;


    // The main array which contains the parts of the world
    private int world[][];

    // Constructor
    public Bomb(int[][] world, Man player, int player_row, int player_column) {
        this.player=player;
        this.world = world;
        bomb_row = player_row;
        bomb_column = player_column;
        setBombXY();

        System.out.println("Bomb timer has been started.");
        timerStart = new Timer();
        timerStart.schedule(new startTimer(), 5 * 1000);

    }

    // When bomb placed it starts to count for 5 sec
    class startTimer extends TimerTask {
        public void run() {
            System.out.println("Bomb timer finished");
            makeExplosion();
            timerStart.cancel(); //Terminate the thread
        }
    }

    // When explosion made, 2 second interval starts
    class endExplosion extends TimerTask {
        public void run() {
            System.out.println("Eplosion ended");
            player.destroyBomb();
            timerEnd.cancel(); //Terminate the thread
        }
    }

    // To fill the array with explosion (4)
    private void makeExplosion() {
        isExplosion = true;
        if (world[bomb_row - 1][bomb_column] != 1) {
            world[bomb_row - 1][bomb_column] = EXPLOSION;;
        }
        if (world[bomb_row + 1][bomb_column] != 1) {
            world[bomb_row + 1][bomb_column] =EXPLOSION;;
        }
        if (world[bomb_row][bomb_column - 1] != 1) {
            world[bomb_row][bomb_column - 1] = EXPLOSION;;
        }
        if (world[bomb_row][bomb_column + 1] != 1) {
            world[bomb_row][bomb_column + 1] = EXPLOSION;;
        }
        world[bomb_row][bomb_column]=EXPLOSION;
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


    public int getBomb_column() {
        return bomb_column;
    }

    public int getBomb_row() {
        return bomb_row;
    }


    // Returns the bomb boundary
    public Rectangle getBounds() {
        return new Rectangle(x, y, BLOCK_SIZE, BLOCK_SIZE);
    }

    // Returns the constant value of EXPLOSION (4)
    public static int getExplosion() {
        return EXPLOSION;
    }


}

