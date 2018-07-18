package Shapes;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: daacra
 * Reg: 1004888
 * Date: 29/11/12
 * Time: 16:51
 */
public class LineBlock extends BaseBlock {
    public LineBlock(Color c, int x, int y, int s) {
        super(c, x, y, s);
    }

    @Override
    public void drawOnBoard(int[][] a, int row, int column) {
    	if(getRotate() == 0 || getRotate() == 2){
    	a[row + 0][column + 0] = a[row + 0][column + 1] = 8;
        a[row + 0][column + 2] = a[row + 0][column + 3] = 8;
    	}else if(getRotate() == 1){
    		a[row -1][column + 1] = a[row][column + 1] = 8;
            a[row + 1][column + 1] = a[row + 2][column + 1] = 8;
    	}
    }
	
    @Override
    public void clearOnBoard(int[][] a, int row, int column) {
    	if(getRotate() == 0 || getRotate() == 2){
	        a[row + 0][column + 0] = a[row + 0][column + 1] = 0;
	        a[row + 0][column + 2] = a[row + 0][column + 3] = 0;
    	}else if(getRotate() == 1){
    		a[row -1][column + 1] = a[row][column + 1] = 0;
            a[row + 1][column + 1] = a[row + 2][column + 1] = 0;
    	}   	
    }
	
    @Override
    public boolean canDrop(int[][] a, int Row, int Column){
    	if(getRotate() == 0 || getRotate() == 2) return Column < 16 && a[Row+0][Column+4] == 0;
    	else if(getRotate() == 1) return Column < 18 && a[Row -1][Column + 2] == 0 && a[Row][Column + 2] == 0 &&
                a[Row + 1][Column + 2] == 0 && a[Row + 2][Column + 2] == 0;
    	else return false;
    }

	@Override
    public boolean canRightLeftMove(int Row, int Column){
		int maxRows = 9; int minRows = 0;
    	if(getRotate() == 1) minRows = 1; maxRows = 7;
    	return ( Row >= minRows && Row <= maxRows  );
    }

    @Override
    public boolean canRotate(int[][] a, int Row, int Column) {
    	if(getRotate() == 0 || getRotate() == 2) return (Row >= 1 && Row <= 7) && Column < 19 && a[Row-1][Column] == 0 && a[Row-1][Column-1] == 0 && a[Row-1][Column+1] == 0 && a[Row+1][Column] == 0 && a[Row+2][Column] == 0
      			 && a[Row+0][Column] == 0 && a[Row+2][Column +1] == 0 && a[Row+1][Column+1] == 0 && a[Row+1][Column+2] == 0;
    	else if(getRotate() == 1) return Column < 19 && a[Row-1][Column] == 0 && a[Row+1][Column+2] == 0 && a[Row+1][Column+3] == 0 && a[Row+2][Column+2] == 0 && a[Row+2][Column+3] == 0
      			 && a[Row+0][Column] == 0 && a[Row][Column +1] == 0 && a[Row][Column+2] == 0 && a[Row][Column+3] == 0;
    	return false;
    }

    @Override
    public void rotate(int[][] a, int Row, int Column) {
    	if(canRotate(a, Row, Column)) { rotate++; }
        if(getRotate() == 1){ drawOnBoard(a, Row, Column); }
        else if(getRotate() == 2){ rotate = 0; }
    }

	@Override
	public int getDropRow(int currentDroppingRow) {
		int result = 0;
		int minRows = 0;
		int maxRows = 9;
		if (getRotate() == 1 || getRotate() == 3) minRows = 1; maxRows = 8;
		
		if ( currentDroppingRow < minRows ){
			result = +1;   		
    	}else if(currentDroppingRow >= maxRows){
    		result = -1;       		
    	}
		return result;
	}
}
