import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Bomb {
    private static final int BOMB = 8;
    private static final int BLOCK_SIZE = 40;
    private int bomb_row;
    private int bomb_column;
    private int x;
    private int y;
    Timer timer;

    // The main array which contains the parts of the world
    private int world[][];

    // Constructor
    public Bomb(int[][] world,int player_row,int player_column) {
        this.world = world;
        bomb_row=player_row;
        bomb_column=player_column;
        setBombXY();
        System.out.println ( "Bomb timer has been started." ) ;
        timer = new Timer () ;
        timer.schedule ( new startTimer () , 5*1000 ) ;

    }

    // Small class
    class startTimer extends TimerTask {
        public void run (  )   {
            System.out.println ( "OK, It's time to do something!" ) ;
            timer.cancel (  ) ; //Terminate the thread
        }
    }



    public void setBombXY(){
        x=bomb_column*BLOCK_SIZE;
        y=bomb_row*BLOCK_SIZE;
    }


    // This part draws the player on the screen
    public void paint(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);

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


}

