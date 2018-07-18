package Shapes;

/**
 * Created with IntelliJ IDEA.
 * User: daacra
 * Reg: 1004888
 * Date: 07/12/12
 * Time: 16:02
 */
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: daacra
 * Date: 29/11/12
 * Time: 16:53
 * To change this template use File | Settings | File Templates.
 */
public class JBlock extends BaseBlock {
    
    public JBlock(Color c, int x, int y, int s) {
        super(c, x, y, s);
    }

    @Override
    public void drawOnBoard(int[][] a, int row, int column) {
		if(getRotate() == 0){
			a[row + 1][column + 1] = a[row + 1][column + 2] = 10;
			a[row + 1][column + 3] = a[row + 0][column + 3] = 10;
		}else if(getRotate() == 1){
			a[row][column + 2] = a[row + 1][column + 2] = 10;
			a[row + 2][column + 2] = a[row + 2][column + 3] = 10;   		
		}else if(getRotate() == 2){
			a[row + 1][column + 1] = a[row + 1][column + 2] = 10;
			a[row + 1][column + 3] = a[row + 2][column + 1] = 10; 		
		}else if(getRotate() == 3){
			a[row][column + 1] = a[row][column + 2] = 10;
			a[row + 1][column + 2] = a[row + 2][column + 2] = 10;
		}
    }

    @Override
    public void clearOnBoard(int[][] a, int row, int column) {
		if(getRotate() == 0){
			a[row + 1][column + 1] = a[row + 1][column + 2] = 0;
			a[row + 1][column + 3] = a[row + 0][column + 3] = 0;
    	}else if(getRotate() == 1){
    		a[row][column + 2] = a[row + 1][column + 2] = 0;
    		a[row + 2][column + 2] = a[row + 2][column + 3] = 0;   		
    	}else if(getRotate() == 2){
    		a[row + 1][column + 1] = a[row + 1][column + 2] = 0;
    		a[row + 1][column + 3] = a[row + 2][column + 1] = 0;       		
    	}else if(getRotate() == 3){
    		a[row][column + 1] = a[row][column + 2] = 0;
    		a[row + 1][column + 2] = a[row + 2][column + 2] = 0;		
    	}
    }
	
    @Override
    public boolean canDrop(int[][] a, int Row, int Column) {
    	if(getRotate() == 0 || getRotate() == 4) return Column < 16 && a[Row+1][Column+4] == 0 && a[Row+0][Column+4] == 0;
    	else if(getRotate() == 1) return Column < 16 && a[Row][Column+3] == 0 && a[Row+1][Column+3] == 0 && a[Row+2][Column+4] == 0;
    	else if(getRotate() == 2) return Column < 16 && a[Row+1][Column+4] == 0 && a[Row+2][Column+2] == 0;
    	else if(getRotate() == 3) return Column < 17 && a[Row][Column+3] == 0 && a[Row+1][Column+3] == 0 && a[Row+2][Column+3] == 0;
    	else return false;
    }

    @Override
    public boolean canRightLeftMove(int Row, int Column){
    	int maxRows = 7; int minRows = 0;
    	if(getRotate() == 0 || getRotate() == 4) maxRows = 8;
    	if(getRotate() == 2) minRows = -1;
    	return ( Row >= minRows && Row <= maxRows  );
    }

    @Override
    public boolean canRotate(int[][] a, int Row, int Column) {
    	if(getRotate() == 0) return (Row >= 0 && Row<=7) && Column < 16 && a[Row][Column + 2] == 0 && a[Row + 1][Column + 2] == 0 &&
                a[Row + 2][Column + 2] == 0 && a[Row + 2][Column + 3] == 0 && a[Row][Column + 1] == 0;
    	else if(getRotate() == 1) return (Row >= 0) && Column < 16 && a[Row + 1][Column + 1] == 0 && a[Row + 1][Column + 2] == 0 &&
               a[Row + 1][Column + 3] == 0 && a[Row + 2][Column + 1] == 0 && a[Row][Column + 3] == 0;
        else if(getRotate() == 2) return (Row <= 8 && Row>-1) && (Column < 16 && a[Row][Column + 1] == 0 && a[Row + 0][Column + 2] == 0 &&    //top : next below
                a[Row + 1][Column + 2] == 0 && a[Row + 2][Column + 2] == 0) && a[Row + 2][Column + 3] == 0;
		return false;
    }

	@Override
	public int getDropRow(int currentDroppingRow) {
		int result = 0;
		int minRows = 0;
		int maxRows = 8;
		if (getRotate() == 1 || getRotate() == 2 || getRotate() == 3) maxRows = 7;
		if (getRotate() == 2) minRows = -1; 
		
		if ( currentDroppingRow < minRows ){
			result = +1;   		
    	}else if(currentDroppingRow>=maxRows){
    		result = -1;		
    	}
		return result;
	}
}

