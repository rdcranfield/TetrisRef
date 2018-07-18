package Shapes;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: daacra
 * Reg: 1004888
 * Date: 29/11/12
 * Time: 16:38
 */
public class SBlock extends BaseBlock {

    public SBlock(Color c, int x, int y, int s) {
        super(c, x, y, s);
    }

    @Override
    public void drawOnBoard(int[][] a, int row, int column) {
    	if(rotate == 0|| rotate == 2){
    		a[row + 1][column + 0] = a[row + 2][column + 0] = 2;    //top middle : right top
    		a[row + 1][column + 1] = a[row + 0][column + 1] = 2;    //bottom middle : bottom left
    	}else if(rotate == 1){
    		a[row + 1][column] = a[row + 1][column + 1] = 2;   
    		a[row + 2][column + 1] = a[row + 2][column + 2] = 2;     		
    	}
    }

    @Override
    public void clearOnBoard(int[][] a, int row, int column) {
    	if(rotate == 0 || rotate == 2){
        	a[row + 1][column + 0] = a[row + 2][column + 0] = 0;    //top middle : right top
        	a[row + 1][column + 1] = a[row + 0][column + 1] = 0;    //bottom middle : bottom left
    	}else if(rotate == 1){
    		a[row + 1][column] = a[row + 1][column + 1] = 0;   
    		a[row + 2][column + 1] = a[row + 2][column + 2] = 0;     		    		
    	}
    }
	
    @Override
    public boolean canDrop(int[][] a, int Row, int Column){
    	if(getRotate() == 0 || getRotate() == 2) return Column < 18 && a[Row+1][Column+2] == 0 && a[Row+0][Column+2] == 0 && a[Row+2][Column+1] == 0;
    	else if(getRotate() == 1 || getRotate() == 3) return Column < 17 && a[Row +1][Column + 2] == 0 && a[Row+2][Column + 3] == 0;
    	else return false;
	}
	
    @Override
    public boolean canRightLeftMove(int Row, int Column){
    	int maxRows = 7; int minRows = 0;
    	if(getRotate() == 1) minRows = -1;
    	return ( Row >= minRows && Row <= maxRows  );
    }
    
    @Override
    public boolean canRotate(int[][] a, int Row, int Column) {
    	if(getRotate() == 0 || getRotate() == 2) return Column < 18 && a[Row + 1][Column + 1] == 0 && a[Row + 1][Column] == 0 && a[Row + 2][Column + 1] == 0 && a[Row + 2][Column+2] == 0 && a[Row][Column] == 0
				&& a[Row][Column + 2] == 0 && a[Row + 1][Column + 2] == 0 && a[Row + 2][Column -1] == 0;
    	else if(getRotate() == 1) return (Row >= 0 && Row <= 7) && Column < 17 && a[Row][Column + 1] == 0 && a[Row+1][Column + 1] == 0 && a[Row+1][Column] == 0 && a[Row+2][Column] == 0;
		return false;		
    }

    @Override
    public void rotate(int[][] a, int Row, int Column) {
    	if(canRotate(a, Row, Column)){ rotate++; }
        if(getRotate() == 1){ drawOnBoard(a, Row, Column); }
        else if(getRotate() == 2){ rotate = 0; }
    }

	@Override
	public int getDropRow(int currentDroppingRow) {
		int result = 0;
		int minRows = 1;
		int maxRows = 7;
		if ( currentDroppingRow < minRows ){
			result = +1;   		
    	}else if(currentDroppingRow>=maxRows){
    		result = -1;	
    	}
		return result;
	}
}

