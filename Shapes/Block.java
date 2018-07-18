package Shapes;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: daacra
 * Reg: 1004888
 * Date: 28/11/12
 * Time: 15:23
 *
 */
public abstract class Block{
    protected Color col;
    protected int xpos, ypos;
    //Point CenterPos;
    //Rotation rot;
    //abstract java.util.List<Point> pointsOccupied();

    public Block(Color c, int x, int y){
        col = c; xpos = x; ypos = y;
    }

    public void setColor(Color c){
        col = c;
    }
    public void move(int xdist, int ydist){
        xpos += xdist;
        ypos += ydist;
    }
	
	//abstract methods will be further implemented in subclass by use of keyword Override
    public abstract void drawOnBoard(int[][] a, int currentDroppingRow, int currentDroppingColumn);

    public abstract void clearOnBoard(int[][] a, int row, int column);

    public abstract boolean canDrop(int[][] a, int Row, int Column);

    public abstract boolean canRightLeftMove(int Row, int Column);

    public abstract boolean canRotate(int[][] a, int Row, int Column);
    public abstract void rotate(int[][] a, int Row, int Column);
    
    //public abstract int getRow();
    
    public abstract int getRotate();
    public abstract int getDropRow(int currentDroppingRow);

}
