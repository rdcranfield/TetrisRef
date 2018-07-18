package Shapes;

import java.awt.Color;
import java.awt.Graphics;

public class BaseBlock extends Block {
	private int slen;
	protected int rotationPos;
	protected int rotate = 0;
	public BaseBlock(Color c, int x, int y, int s) {
		super(c, x, y);
		slen = s;
	}

	public void draw(Graphics g) {
        g.setColor(col);
        g.drawRect(xpos - slen / 2, ypos - slen / 2, slen, slen);
    }
	
	@Override
	public void drawOnBoard(int[][] a, int currentDroppingRow, int currentDroppingColumn) {	
	}

	@Override
	public void clearOnBoard(int[][] a, int row, int column) {	
	}

	@Override
	public boolean canDrop(int[][] a, int Row, int Column) {
		return false;
	}

	@Override
	public boolean canRightLeftMove(int Row, int Column) {
		return false;
	}
	
	@Override
	public boolean canRotate(int[][] a, int Row, int Column) {
		return false;
	}

	@Override
    public void rotate(int[][] a, int Row, int Column) {
    	if(canRotate(a, Row, Column)){
    		rotate++;
    	}
        if(getRotate() == 1 || getRotate() == 2 || getRotate() == 3){
        	drawOnBoard(a, Row, Column);        
        }else{ 
        	rotate = 0;          
        }
    }

	@Override
	public int getRotate() {
		return rotate;
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
