import org.apache.commons.lang.ArrayUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

class Com {

    int PLAYER;
    private static final int PLAYER_SIZE = 30;

    private GamePanel game;
    private int x;
    private int y;
    private int speed=1;
    private int xa = 0;
    private int ya = 0;
    Random rndNumGen = new Random();




    // The main array which contains the parts of the world
    private int world[][];

    // Constructor
    public Com(int[][] world, GamePanel game,int num,int START_X, int START_Y) {

        this.world = world;
        this.game = game;
        PLAYER=num;
        x = START_X;
        y = START_Y;
//        x = (rndNumGen.nextInt(((480-80)+1)+80)%40)+85;
//        y =(rndNumGen.nextInt(((400-80)+1)+80)%40)+85;

        putPlayerInMatrix();

        switch(rndNumGen.nextInt(4)) {
            // Create empty block up
            case 0:
                world[getPlayerArrayRow()-1][getPlayerArrayColumn()]=0;
                world[getPlayerArrayRow()+1][getPlayerArrayColumn()]=0;
                ya=-speed;
                break;
            // Create empty block down
            case 1:
                world[getPlayerArrayRow()+1][getPlayerArrayColumn()]=0;
                world[getPlayerArrayRow()-1][getPlayerArrayColumn()]=0;
                ya=speed;
                break;
            // Create empty block left
            case 2:
                world[getPlayerArrayRow()][getPlayerArrayColumn()-1]=0;
                world[getPlayerArrayRow()][getPlayerArrayColumn()+1]=0;
                xa=-speed;
                break;
            // Create empty block right
            case 3:
                world[getPlayerArrayRow()][getPlayerArrayColumn()+1]=0;
                world[getPlayerArrayRow()][getPlayerArrayColumn()-1]=0;
                xa=speed;
                break;
        }
    }

    // Moving the player in x and y directions
    public void move() {

        if (checkExplosionCollision()) {
            JOptionPane.showMessageDialog(null, "Com dead");
        }

        if (checkCollision()) {
            changeComMovingWay();
        }

        x = x + xa;
        y = y + ya;
    }

    public void changeComMovingWay(){
        if (xa!=0) {
            xa=xa*(-1);
        }
        if (ya!=0) {
            ya=ya*(-1);
        }
    }


    // Checks does the player collide with the walls
    public boolean checkCollision() {
        boolean isCollide = false;
        Rectangle[] boundArr = (Rectangle[]) ArrayUtils.addAll(game.walls.getRectangleArr(), game.bricks.getRectangleArr());



        for (Rectangle item : boundArr) {
            // Check does the item is zero (last items in array could be 0)
            if (item != null) {

                // Complex statements for bomb placement check
                if (game.man.thirdBoolBomb) {
                    isCollide = getBounds().intersects(game.man.bomb.getBounds());
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
        if (game.man.bomb != null && game.man.bomb.isExplosion) {
            Rectangle[] expBoundArr = game.man.bomb.getRectangleArr();
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






    // This part draws the player on the screen
    public void paint(Graphics2D g) {

        g.setColor(Color.RED);
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
