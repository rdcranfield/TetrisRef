package Shapes;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: daacra
 * Reg: 1004888
 * Date: 29/11/12
 * Time: 16:53
 */
public class LBlock extends BaseBlock {
    protected int random = 1 + (int)(Math.random() * ((9 - 1) + 1));

    public LBlock(Color c, int x, int y, int s) {
        super(c, x, y, s);
    }

    @Override
    public void drawOnBoard(int[][] a, int row, int column) {
    	if(getRotate() == 0){
    		a[row + 0][column + 0] = a[row + 0][column + 1] = 3;    //top : next below
    		a[row + 0][column + 2] = a[row + 1][column + 2] = 3;    //3rd below : to the right
        }else if(getRotate() == 1){
        	a[row + 1][column + 0] = a[row + 0][column + 1] = 3;    //top : next below
            a[row + 1][column + 1] = a[row -1][column + 1] = 3;    //3rd below : to the right
        	
        }else if(getRotate() == 2){
        	a[row + 0][column + 0] = a[row][column+1] = 3;    //top : next below
            a[row + 0][column + 2] = a[row -1][column] = 3;    //3rd below : to the right
        }else if(getRotate() == 3){
        	a[row + 1][column + 1] = a[row + 0][column + 1] = 3;    //top : next below
            a[row -1][column + 1] =  a[row -1][column + 2] = 3;    //3rd below : to the right
        }
    }

    @Override
    public void clearOnBoard(int[][] a, int row, int column) {
    	if(getRotate() == 0 || getRotate() == 4){
    		a[row + 0][column + 0] = a[row + 0][column + 1] = 0;
    		a[row + 0][column + 2] = a[row + 1][column + 2] = 0;
    	}else if(getRotate() == 1){
    		a[row + 1][column + 0] = a[row + 0][column + 1] = 0;    //top : next below
            a[row + 1][column + 1] = a[row -1][column + 1] = 0;          	
        }else if(getRotate() == 2){
        	a[row + 0][column + 0] = a[row][column+1] = 0;    //top : next below
            a[row + 0][column + 2] = a[row -1][column] = 0;    //3rd below : to the right
        }else if(getRotate() == 3){
        	a[row + 1][column + 1] = a[row + 0][column + 1] = 0;    //top : next below
            a[row -1][column + 1] =  a[row -1][column + 2] = 0;    //3rd below : to the right       	
        }
    }
	
    @Override
    public boolean canDrop(int[][] a, int Row, int Column){
    	if(getRotate() == 0) return Column < 17 && a[Row+0][Column+3] == 0 && a[Row+1][Column+3] == 0;
    	else if(getRotate() == 1) return Column < 18 &&  a[Row-1][Column+2] == 0 && a[Row+0][Column+2] == 0 && a[Row+1][Column+2] == 0;
    	else if(getRotate() == 2) return Column < 17 && a[Row+0][Column+3] == 0 && a[Row-1][Column+1] == 0;
    	else if(getRotate() == 3) return Column < 17 &&  a[Row-1][Column+3] == 0 && a[Row][Column+2] == 0 && a[Row+1][Column+2] == 0;
    	else return false;
    }
    
    @Override
    public boolean canRightLeftMove(int Row, int Column){
    	int maxRows = 8; int minRows = 1;
    	if(getRotate() == 0) minRows = 0; 
    	if(getRotate() == 2) maxRows = 9;
    	return ( Row >= minRows && Row <= maxRows  ); 
    }

    @Override
    public boolean canRotate(int[][] a, int Row, int Column) {
    	if(getRotate() == 0) return ( (Row >= 1 && Row<=6) && Column < 17 && a[Row + 1][Column + 0] == 0 && a[Row + 0][Column + 1] == 0 &&    //top : next below
                a[Row + 1][Column + 1] == 0 && a[Row -1][Column + 1] == 0 && a[Row -1][Column] == 0 
                && a[Row +2][Column] == 0 && a[Row +2][Column+1] == 0 && a[Row + 2][Column + 2] == 0 )
                || ((Row >= 1 && Row<=7) && Column < 17 && a[Row + 1][Column + 0] == 0 && a[Row + 0][Column + 1] == 0 &&    //top : next below
                        a[Row + 1][Column + 1] == 0 && a[Row -1][Column + 1] == 0 && a[Row -1][Column] == 0 );
    	else if(getRotate() == 1) return (Row >= 1) && Column < 18 && a[Row - 1][Column + 0] == 0 && a[Row + 0][Column + 0] == 0 &&    //top : next below
                a[Row - 1][Column + 2] == 0 && a[Row][Column + 2] == 0 && a[Row -1][Column - 1] == 0 && a[Row][Column - 1] == 0;
        else if(getRotate() == 2) return (Row <= 8 && Row>1) && (Column < 17 && a[Row + 1][Column + 1] == 0 && a[Row + 0][Column + 1] == 0 &&    //top : next below
                a[Row -1][Column + 1] == 0 && a[Row -1][Column + 2] == 0
                && a[Row -2][Column] == 0 && a[Row -2][Column + 1] == 0
                && a[Row -2][Column + 2] == 0 && a[Row +1][Column + 1] == 0
                && a[Row +1][Column+2] == 0)
                || (Row<=1 && Row <=8) && Column < 17 && a[Row + 1][Column + 1] == 0 && a[Row + 0][Column + 1] == 0 &&    //top : next below
                        a[Row -1][Column + 1] == 0 && a[Row -1][Column + 2] == 0
                        && a[Row +1][Column + 1] == 0
                        && a[Row +1][Column+2] == 0;
		return false;
    }

    @Override
    public void rotate(int[][] a, int Row, int Column) {
    	if(canRotate(a, Row, Column)){ rotate++; }
    	if(getRotate() >= 4)rotate = 0;
    	drawOnBoard(a, Row, Column);
    }

	@Override
	public int getDropRow(int currentDroppingRow) {
		int result = 0;
		int minRows = 1;
		int maxRows = 8;
		if ( currentDroppingRow < minRows ){
			result = +1;   		
    	}else if(currentDroppingRow>=maxRows){
    		result = -1;
    	}
		return result;
	}
}

