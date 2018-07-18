package Shapes;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: daacra
 * Reg: 1004888
 * Date: 29/11/12
 * Time: 16:29
 */
public class TBlock extends Block {
    private int slen;
    protected int rotationPos;
    protected int rotate = 0;
    //protected ArrayList<> blocks

    public TBlock(Color c, int x, int y, int s) {
        super(c, x, y);
        slen = s;
    }

    public void draw(Graphics g) {
        g.setColor(col);
        g.drawRect(xpos - slen / 2, ypos - slen / 2, slen, slen);

    }
	
	//overrides abstract class method and draws the tetris shape TBlock
    //onto the grid with the correct coordinates and picks sixth(0-6) colour in 
    //colour array in TetrisView class
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

	//overrides abstract class method and clears the previous location
    //of the tetris shape TBlock, hence the same coordinates as
    //I will make a call to this method in the TetrisView class as
    //TBlock is dropping down at fixed intervals or moved left or right.  
    //colour zero(0) picked as the colour is white same colour as grid
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

	// boolean returns true if shape drops between 16-18 columns depending on the shape and its size 
	//(amount of rows/colums it takes up on grid), and while the coordinates selected by a[row]column] 
	//(coordinates of specific squares of the tetris blocks which are to be checked;  
	// allows me to stack shapes ontop of eachother as it registers the tetris block and stops them
	// falling into other Tetris block shapes and/or continuing past the grid). 
    @Override
    public boolean canDrop(int[][] a, int Row, int Column){
        System.out.println("column: "+Column);
        if(rotate == 0 || rotate == 4){
        if (Column < 17 && a[Row+1][Column+3] == 0 && a[Row+2][Column+2] == 0 && a[Row+0][Column+2] == 0){     //bottom   :  right   :  left
            return true;

        }else{
            return false;  
        }
        }else if(rotate == 1){
        	if (Column < 17 && a[Row+1][Column+3] == 0 && a[Row+2][Column+2] == 0){     //bottom   :  right   :  left
                return true;

            }else{
                return false;  
            }
        	
        }else if(rotate == 2){
        	if (Column < 18 && a[Row+1][Column+2] == 0 && a[Row+2][Column+2] == 0 && a[Row+0][Column+2] == 0){     //bottom   :  right   :  left
                return true;

            }else{
                return false;  
            }
        	
        }else{
        	if (Column < 17 && a[Row+1][Column+3] == 0 && a[Row+0][Column+2] == 0){     //bottom   :  right   :  left
                return true;

            }else{
                return false;  
            }	
        }
	}
	
    @Override
    public boolean canRightLeftMove(int[][] a, int Row, int Column){
        System.out.println("row: "+Row);
        if(rotate == 0 || rotate == 2 || rotate == 4){
        if(Row >= 0 && Row <= 7){
        	 return true;
        }
       return false;
        }else{
        	if(rotate == 1){
        		 if(Row >= -1 && Row <= 7){
                	 return true;
                }
               return false;
        		
        	}else{
        		 if(Row >= 0 && Row <= 8){
                	 return true;
                }
               return false;
        		
        	}
        }
    }

    @Override
    public boolean canRotate(int[][] a, int Row, int Column) {
    	 //check within borders
    	boolean status = true;
        if(rotate == 0){    	
        		if (Column < 17 && a[Row + 1][Column + 0] == 0 && a[Row + 1][Column + 1] == 0 &&    //top : next below
                a[Row + 1][Column + 2] == 0 && a[Row +2][Column + 1] == 0 && a[Row][Column +2] == 0 
                && a[Row +2][Column+2] == 0 && a[Row +2][Column] == 0){
        			status = true;
        		}else{
        			 status = false;
        		}
        		
        }else if(rotate == 1){
        	if(Row >= 0){
        		if (Column < 17 && a[Row + 1][Column + 0] == 0 && a[Row + 1][Column + 1] == 0 &&    //top : next below
                a[Row][Column + 1] == 0 && a[Row+2][Column + 1] == 0 && a[Row + 2][Column + 2] == 0 
                && a[Row+2][Column] == 0 && a[Row][Column] == 0){
        			status = true;
        		}else{
        			 status = false;
        		}
        		
        		
            }else{
            	status = false;
            }
        	
        }else if(rotate == 2){
        	
        		if (Column < 18 && a[Row + 1][Column] == 0 && a[Row + 1][Column + 1] == 0 &&    //top : next below
                a[Row +1][Column + 2] == 0 && a[Row][Column + 1] == 0
                && a[Row][Column] == 0 && a[Row+2][Column] == 0
                && a[Row][Column + 2] == 0){
        			status = true;
        		}else{
        			 status = false;
        		}	         
        }else {
        	if(Row <=7){
        		if (Column < 17 && a[Row ][Column + 1] == 0 && a[Row + 1][Column + 1] == 0 &&    //top : next below
                a[Row + 2][Column + 1] == 0 && a[Row+1][Column + 2] == 0 && a[Row][Column + 2] == 0 
                && a[Row+2][Column+2] == 0 && a[Row][Column] == 0){
        			status = true;
        		}else{
        			 status = false;
        		}
        		
        		
            }else{
            	status = false;
            }
        
        }
        return status;
    }

    @Override
    public void rotate(int[][] a, int Row, int Column) {
    	if(canRotate(a, Row, Column)){
            rotate++;
        	}
            if(rotate == 1){
            	drawOnBoard(a, Row, Column);
            }else if(rotate == 2){ 
            	drawOnBoard(a, Row, Column);           
            }else if(rotate == 3){ 
            	drawOnBoard(a, Row, Column);           
            }else{ 
            	rotate = 0;          
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
		return rotate;
	}
}

