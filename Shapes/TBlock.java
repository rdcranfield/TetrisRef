package Shapes;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: daacra
 * Reg: 1004888
 * Date: 29/11/12
 * Time: 16:29
 */
public class TBlock extends BaseBlock {
	
    public TBlock(Color c, int x, int y, int s) {
        super(c, x, y, s);
    }
	
    @Override
    public void drawOnBoard(int[][] a, int row, int column) {
    	if(rotate == 0){
    		a[row + 0][column + 1] = a[row + 1][column + 1] = 9;
    		a[row + 2][column + 1] = a[row + 1][column + 2] = 9;
    	}else if(rotate == 1){
    		a[row + 1][column] = a[row + 1][column + 1] = 9;
            a[row + 1][column + 2] = a[row + 2][column + 1] = 9;
    	}else if(rotate == 2){
    		a[row + 1][column] = a[row][column + 1] = 9;
            a[row + 1][column + 1] = a[row + 2][column + 1] = 9;
    	}else if(rotate == 3){
    		a[row + 1][column] = a[row + 1][column + 1] = 9;
            a[row + 1][column + 2] = a[row][column+1] = 9;
    	}
    }

    @Override
    public void clearOnBoard(int[][] a, int row, int column) {
    	if(rotate == 0){
    		a[row + 0][column + 1] = a[row + 1][column + 1] = 0;
    		a[row + 2][column + 1] = a[row + 1][column + 2] = 0;
    	}else if(rotate == 1){
    		a[row + 1][column] = a[row + 1][column + 1] = 0;
            a[row + 1][column + 2] = a[row + 2][column + 1] = 0;
    	}else if(rotate == 2){
    		a[row + 1][column] = a[row][column + 1] = 0;
            a[row + 1][column + 1] = a[row + 2][column + 1] = 0;
    	}else if(rotate == 3){
    		a[row + 1][column] = a[row + 1][column + 1] = 0;
            a[row + 1][column + 2] = a[row][column+1] = 0;
    	}   	
    }

    @Override
    public boolean canDrop(int[][] a, int Row, int Column){
    	if(getRotate() == 0 || getRotate() == 4) return Column < 17 && a[Row+1][Column+3] == 0 && a[Row+2][Column+2] == 0 && a[Row+0][Column+2] == 0;
    	else if(getRotate() == 1) return Column < 17 && a[Row+1][Column+3] == 0 && a[Row+2][Column+2] == 0;
    	else if(getRotate() == 2) return Column < 18 && a[Row+1][Column+2] == 0 && a[Row+2][Column+2] == 0 && a[Row+0][Column+2] == 0;
    	else if(getRotate() == 3) return Column < 17 && a[Row+1][Column+3] == 0 && a[Row+0][Column+2] == 0;
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
    	if(getRotate() == 0) return Column < 17 && a[Row + 1][Column + 0] == 0 && a[Row + 1][Column + 1] == 0 &&    //top : next below
                a[Row + 1][Column + 2] == 0 && a[Row +2][Column + 1] == 0 && a[Row][Column +2] == 0 
                && a[Row +2][Column+2] == 0 && a[Row +2][Column] == 0;
    	else if(getRotate() == 1) return (Row >= 0) && Column < 17 && a[Row + 1][Column + 0] == 0 && a[Row + 1][Column + 1] == 0 &&    //top : next below
                a[Row][Column + 1] == 0 && a[Row+2][Column + 1] == 0 && a[Row + 2][Column + 2] == 0 
                && a[Row+2][Column] == 0 && a[Row][Column] == 0;
        else if(getRotate() == 2) return Column < 18 && a[Row + 1][Column] == 0 && a[Row + 1][Column + 1] == 0 &&    //top : next below
                a[Row +1][Column + 2] == 0 && a[Row][Column + 1] == 0
                && a[Row][Column] == 0 && a[Row+2][Column] == 0
                && a[Row][Column + 2] == 0;
        else if(getRotate() == 3) return (Row <=7) && Column < 17 && a[Row ][Column + 1] == 0 && a[Row + 1][Column + 1] == 0 &&    //top : next below
                a[Row + 2][Column + 1] == 0 && a[Row+1][Column + 2] == 0 && a[Row][Column + 2] == 0 
                && a[Row+2][Column+2] == 0 && a[Row][Column] == 0;
		return false;	
    }
}

