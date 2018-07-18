package Shapes;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: daacra
 * Reg: 1004888
 * Date: 27/11/12
 * Time: 20:11
 */
public class Square extends BaseBlock{

    public Square(Color c, int x, int y, int s){
        super(c, x, y, s);
    }

    @Override
    public void drawOnBoard(int[][] a, int row, int column) {
        a[row][column] = a[row][column+1] = 5;
        a[row+1][column] = a[row+1][column+1] = 5;
    }

    @Override
    public void clearOnBoard(int[][] a, int row, int column) {
        a[row+0][column] = a[row+0][column+1] = 0;
        a[row+1][column] = a[row+1][column+1] = 0;
    }

    @Override
    public boolean canDrop(int[][] a, int Row, int Column) {
    	return Column < 18 && a[Row+0][Column+2] == 0 && a[Row+1][Column+2] == 0;
    }
    @Override
    public boolean canRightLeftMove(int Row, int Column){
    	return Row >= 0 && Row <= 8;
    }

    public boolean canRotate(int[][] a, int Row, int Column){
        return true;
    }

	@Override
	public int getDropRow(int currentDroppingRow) {
		int result = 0;
		int minRows = 1;
		int maxRows = 7;
		if ( currentDroppingRow < minRows ){
			result = +1;   		
    	}else if(currentDroppingRow >= maxRows){
    		result = -1;        		
    	}
		return result;
	}
}