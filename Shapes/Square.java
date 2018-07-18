package Shapes;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: daacra
 * Reg: 1004888
 * Date: 27/11/12
 * Time: 20:11
 */
public class Square extends Block{
    private int slen;
    public int rotationPos;
    //protected ArrayList<> blocks

    public Square(Color c, int x, int y, int s){
        super(c, x, y); slen = s;
    }

    public void draw(Graphics g){
        g.setColor(col);
        g.drawRect(xpos-slen/2, ypos-slen/2, slen, slen);
    }

    public void move(int x, int y){
        if(rotationPos == 0){
            //move(down);
            //.get(0).move();
        }

    }
	
	// overrides abstract class method and draws the tetris shape Square
    // onto the grid with the correct coordinates and picks fifth(0-5) colour in 
    // colour array in TetrisView class
    @Override
    public void drawOnBoard(int[][] a, int row, int column) {
        //if(canDrop){
        a[row][column] = a[row][column+1] = 5;
        a[row+1][column] = a[row+1][column+1] = 5;
    }

	// overrides abstract class method and clears the previous location
    // of the tetris shape Square, hence the same coordinates as
    // I will make a call to this method in the TetrisView class as
    // Square is dropping down at fixed intervals or moved left or right.  
    // colour zero(0) picked as the colour is white same colour as grid
    @Override
    public void clearOnBoard(int[][] a, int row, int column) {
        a[row+0][column] = a[row+0][column+1] = 0;
        a[row+1][column] = a[row+1][column+1] = 0;
    }
	
	// boolean returns true if shape drops between 16-18 columns depending on the shape and its size 
	//(amount of rows/colums it takes up on grid), and while the coordinates selected by a[row]column] 
	//(coordinates of specific squares of the tetris blocks which are to be checked;  
	// allows me to stack shapes ontop of eachother as it registers the tetris block and stops them
	// falling into other Tetris block shapes and/or continuing past the grid). 
    @Override
    public boolean canDrop(int[][] a, int Row, int Column) {
        System.out.println("column: "+Column);
        if (Column < 18 && a[Row+0][Column+2] == 0 && a[Row+1][Column+2] == 0){
            return true;

        }else{
            return false; 
    }
    }
    @Override
    public boolean canRightLeftMove(int[][] a, int Row, int Column){
        System.out.println("row: "+Row);
        if(Row >= 0 && Row <= 8){
       // if(Row <0 || Row > 8&& a[Row+2][Column+0] == 1 && a[Row+2][Column+1] == 1){
        	return true;
        }
	return false;

    }

	// canRotate would be the method to check rotation is possible for each shape, while method
	// rotate would be the method which would implement the rotation; rotationPos would stand for
	// which rotation its on, each click would turn shape 90degrees clockwise, some shapes rotating
	// 4 times while others 2 times etc. Didnt implement functioning rotation for tetris blocks.
    public boolean canRotate(int[][] a, int Row, int Column){
        return true;
    }
	
    public void rotate(int[][] a, int Row, int Column){
        rotationPos = 0;
        if (rotationPos == 0){
            a[Row][Column] = a[Row][Column-1] = 5;    //0
            a[Row+1][Column+1] = a[Row+0][Column+1] = 5;    //r1   :   r0 c1
           // a[row][column] = a[row][column+1] = 5;
           // a[row+1][column] = a[row+1][column+1] = 5;
        }
	}

	@Override
	public int getRow() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRotate() {
		// TODO Auto-generated method stub
		return 0;
	}
}

    /*public boolean posBlock(int[][] array, int Row, int Column, int endCurrentDroppingRows, int endCurrentDroppingColumn){
        if(Row>=endCurrentDroppingRows){
          Row = endCurrentDroppingRows;
          drawOnBoard(array, Row, Column, canDrop(array, endCurrentDroppingRows, Column));

        }
        return true;
    }
    /*java.util.List<Point> pointsOccupied() {
        int x = CenterPos.x();
        int y = CenterPos.y();
        java.util.List<Point> points = new LinkedList<Point>();
        switch (rot) {
            case up:
                points.add(new Point(x - 1, y));
                points.add(new Point(x, y));
                points.add(new Point(x + 1, y));
                points.add(new Point(x, y + 1));
                break;
            case down:
                points.add(new Point(x - 1, y));
                points.add(new Point(x, y));
                points.add(new Point(x + 1, y));
                points.add(new Point(x, y - 1));
                break;
            // finish the cases
            case left:
                break;
            case right:
                break;
        }

        return points;   */