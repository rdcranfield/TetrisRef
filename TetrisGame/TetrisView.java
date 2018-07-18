package TetrisGame;

import javax.swing.*;

import Shapes.Block;
import Shapes.JBlock;
import Shapes.LBlock;
import Shapes.LineBlock;
import Shapes.SBlock;
import Shapes.Square;
import Shapes.TBlock;
import Shapes.ZBlock;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static java.awt.Color.*;

/**
 * Created with IntelliJ IDEA.
 * User: daacra
 * Reg: 1004888
 * Date: 28/11/12
 * Time: 13:59
 */
public class TetrisView extends JComponent {
    JPanel myTetrisCanvas;
    public boolean canMoveLeft = true;
    public boolean isCanMoveLeft() {
		return canMoveLeft;
	}

	public void setCanMoveLeft(boolean canMoveLeft) {
		this.canMoveLeft = canMoveLeft;
	}

	static int count = 0;
    static int count1 = 0;
    static JLabel HighScore;
    static JLabel name;
    static JLabel regno;
    static JLabel gameOver;
    static boolean game_over;
    boolean hasBlockFallen;
    MouseHandler handler = new MouseHandler();
    static Color[] colors = {WHITE, black, green, blue, red,
            yellow, magenta, pink, cyan, DARK_GRAY, ORANGE};
    static int[][] a;
    int w, h;
    Random r = new Random();
    int randomR = this.r.nextInt(7) + 1;
    static int[][] array;
    static int size = 20;
    public static boolean game_going = true;

    int currentDroppingRow, currentDroppingColumn;
    int endCurrentDroppingRows;
    int endCurrentDroppingColumn;
   // Block block = new SBlock(Color.red, 10, 10, 1);
   
	
	//array of randomPoolOfBlocks, creates new referenced objects, will be chosen randomly 
	//in the newPiece method, I start the tetris shapes with a square, as seen above
	//then once square reaches bottom of grid, a random newPiece will be generated
    Block[] randomPoolOfBlocks = {
        new Square(Color.red, 10, 10, 1),
        new SBlock(Color.red, 10, 10, 1),
        new ZBlock(Color.red, 10, 10, 1),
        new LineBlock(Color.red, 10, 10, 1),
        new LBlock(Color.red, 10, 10, 1),
       new JBlock(Color.red, 10, 10, 1),
       new TBlock(Color.red, 10, 10, 1)




    };
    int randomIndex = r.nextInt(randomPoolOfBlocks.length);
  Block block = randomPoolOfBlocks[randomIndex];
  //  Block block = new LBlock(Color.red, 10, 10, 1);
	//TetrisView component
	
    public TetrisView(int[][] a) {
        this.a = a;
        w = a.length;
        h = a[0].length;
        currentDroppingColumn = 0;
        currentDroppingRow = 4;
        endCurrentDroppingRows = 19;
        endCurrentDroppingColumn = 9;
        hasBlockFallen = false;
        HighScore = new JLabel("Score: " + String.valueOf(count));
        name = new JLabel("Danny Cranfield : 1004888 ");
        gameOver = new JLabel("Let the games begin");
        //regno = new JLabel("1004888");
        boolean game_over = false;

        addMouseListener(handler);
        startTimer();
        //for (int i = 0; i < a.length; i++) {
    }
  
	//Paint component is the method which implements the drawing onto the applet.
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        // a[6][10] = 3;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                g.setColor(colors[a[i][j]]);
                g.fill3DRect(i * size, j * size, size, size, true);
            }
        }
    }
	
	// timer will be used to drop the tetris block at fixed intervals via the sheduleAtFixedRate
	// will set the time, while game going if block canDrop(method checks block of reached bottom of
	// grid or a different block then stack ontop, as the shape is dropping down also remove any 
	// full lines that occur, else newPiece (when previous shape reaches bottom of grid or stacks
	// on another shape, if block can't drop then stop game and display game over & score
    public void startTimer() {
        final Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                //timer.start();
                //if (!game_going) {
                try {
                    if (game_going) {
                        //block.canDrop(a, currentDroppingRow, currentDroppingColumn);
                        //hasBlockFallen = false;
                        System.out.println("game_going");
                        if(block.canRightLeftMove(a, currentDroppingRow, currentDroppingColumn)){
                        if (block.canDrop(a, currentDroppingRow, currentDroppingColumn)) {

                            System.out.println("dropping..");
                            dropDown(); //this makes the block drop a line
                           // if(!block.canRightLeftMove(a, currentDroppingRow, currentDroppingColumn)){
                            	
                           // }

                            //removeFullLine();
                            //if(a[currentDroppingRow].length == 10){
                            
                        

                            //removeFullLine(a[currentDroppingRow] = [10]);
                            //removeFullLine(a[currentDroppingRow][currentDroppingColumn]);

                           
                        } else {
                            System.out.println("new piece");
                            // need to drop a new one.
                            currentDroppingColumn = 0; //start newPiece from top column of grid
                            currentDroppingRow = 4;		// and at the middle row of grid
                            newPiece();


                            if (!(block.canDrop(a, currentDroppingRow, currentDroppingColumn))) {
                                game_going = false;
                                timer.cancel();
                                gameOver.setText("Game Over! your score was " + String.valueOf(count));
                                HighScore.setText("Score: " + String.valueOf(count));
                            }
                        }
                    }else{
                    	//setCanMoveLeft(false);
                    	//if type of lineblock
                    	if(block instanceof LineBlock){
                    	if(block.getRotate() == 0 || block.getRotate() == 2){
                    		if(currentDroppingRow < 0){
                    			//	block.clearOnBoard(a, currentDroppingRow, currentDroppingColumn);
                    			currentDroppingRow = currentDroppingRow+1;
                    		
                    		}else if(currentDroppingRow>9){
                    			currentDroppingRow = currentDroppingRow-1;
                    		}
                    	}else{
                    		if(currentDroppingRow < 1){
                            	//	block.clearOnBoard(a, currentDroppingRow, currentDroppingColumn);
                            		currentDroppingRow = currentDroppingRow+1;
                            		
                            }else if(currentDroppingRow>=8){
                            		currentDroppingRow = currentDroppingRow-1;
                            }
                    		
                    	}
                    	}else if(block instanceof TBlock){
                    		if(currentDroppingRow < 1){
                            		currentDroppingRow = currentDroppingRow+1;
                            		
                            }else if(currentDroppingRow>7){
                            		currentDroppingRow = currentDroppingRow-1;
                            		
                            }
                    		
                    	}else if(block instanceof Square){
                    		if(currentDroppingRow < 1){
                        		currentDroppingRow = currentDroppingRow+1;
                        		
                    		}else if(currentDroppingRow>7){
                        		currentDroppingRow = currentDroppingRow-1;
                        		
                    		}
                    		
                    	}else if(block instanceof SBlock){
                    		if(currentDroppingRow <0){
                        		currentDroppingRow = currentDroppingRow+1;
                        		
                    		}else if(currentDroppingRow>7){
                        		currentDroppingRow = currentDroppingRow-1;
                        		
                    		}
                    		
                    	}else if(block instanceof ZBlock){
                    		if(block.getRotate() == 0 || block.getRotate() == 2){
                    		if(currentDroppingRow < 0){
                        		currentDroppingRow = currentDroppingRow+1;
                        		
                    		}else if(currentDroppingRow>7){
                        		currentDroppingRow = currentDroppingRow-1;
                        		
                    		}
                    		}else{
                    			if(currentDroppingRow < 0){
                            		currentDroppingRow = currentDroppingRow+1;
                            		
                        		}else if(currentDroppingRow>8){
                            		currentDroppingRow = currentDroppingRow-1;
                            		
                        		}
                    		}
                    		
                    	}else if(block instanceof JBlock){
                    	if(block.getRotate() == 0 || block.getRotate() == 4){
                    		if(currentDroppingRow <0){
                        		currentDroppingRow = currentDroppingRow+1;
                        		
                    		}else if(currentDroppingRow>=8){
                        		currentDroppingRow = currentDroppingRow-1;
                        		
                    		}
                    	}else if(block.getRotate() == 1){
                    		if(currentDroppingRow <0){
                        		currentDroppingRow = currentDroppingRow+1;
                        		
                    		}else if(currentDroppingRow>=7){
                        		currentDroppingRow = currentDroppingRow-1;
                        		
                    		}
                    		
                    	}else if(block.getRotate() == 2){
                    		if(currentDroppingRow <-1){
                        		currentDroppingRow = currentDroppingRow+1;
                        		
                    		}else if(currentDroppingRow>=7){
                        		currentDroppingRow = currentDroppingRow-1;
                        		
                    		}
                    		
                    	}else{
                    		if(currentDroppingRow <0){
                        		currentDroppingRow = currentDroppingRow+1;
                        		
                    		}else if(currentDroppingRow>=7){
                        		currentDroppingRow = currentDroppingRow-1;
                        		
                    		}
                    		
                    	}
                    		
                    	}else if(block instanceof LBlock){
                    		
                    		if(currentDroppingRow <1){
                        		currentDroppingRow = currentDroppingRow+1;
                        		
                    		}
                    		if(block.getRotate() == 0 || block.getRotate() == 1 || block.getRotate() == 2){
                    			if(currentDroppingRow>=8){
                        		currentDroppingRow = currentDroppingRow-1;
                        		
                    			}
                    		}else{
                    			if(currentDroppingRow>=8){
                            		currentDroppingRow = currentDroppingRow-1;
                    			}
                    		}
                    		//}else if(block.getRotate() == 1){
                    			
                    		//}else if(block.getRotate() == 2){
                    			
                    		//}else if(block.getRotate() == 3){
                    			
                    		//}
                    		
                    		
                    	//if type tblock
                    	}
                    	}
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();

                }
                repaint();
            }

        };
        timer.scheduleAtFixedRate(task, 0, 200);  //timer delay

    }

	// MouseHandler is used to listen to any events involving the mouse, such as a left or right click
	// or clicking the centre mouse button in; left click will move tetris shape left & right click moves
	// tetris block right & centre button rotate block 90degrees clockwise(didn't implement rotation)
	// while a call to block.clearOnBoard will get rid of the previous location of the block on the grid 
	// each time its moved 
    public class MouseHandler implements MouseListener, KeyListener {//MouseMotionListener for mouseDragged
        TetrisView tv;

        MouseHandler() {
            //this.tv = parent;
            //int amount = i;
            /*Counter += amount;
            Highscore.setText(""+Counter); */

        }

        public void mouseClicked(MouseEvent e) {
            //int xPos = e.getX();  //get x pos of mouse
            //int yPos = e.getY();  //get y pos of mouse
        }

     

        public void mousePressed(MouseEvent e) {
            //called when mouse button is pressed while mouse cursor is on component
            if (e.getButton() == MouseEvent.BUTTON3) {  //right mouse button
                //move currently active right
            	//if(canMoveLeft){
                block.clearOnBoard(a, currentDroppingRow, currentDroppingColumn);
                currentDroppingRow++;
            	//}
            	//else{
            	//	currentDroppingRow = currentDroppingRow-1;
            	//}

            } else if (e.getButton() == MouseEvent.BUTTON2) {   //middle mouse button
                block.clearOnBoard(a, currentDroppingRow, currentDroppingColumn);
               

                block.rotate(a, currentDroppingRow, currentDroppingColumn);
                //rotate currently active by 90 degrees

            } else { // left mouse button
                 //move currently active left
            	//if(canMoveLeft){
                block.clearOnBoard(a, currentDroppingRow, currentDroppingColumn);
                currentDroppingRow--;
            	//}else{
            		
                	//	currentDroppingRow = currentDroppingRow+1;
                	
            	//}

                //statusBar.setText outputs botton of component(572)
            }
        }


        public void mouseReleased(MouseEvent e) {
            //called when mouse button is released after being pressed,
            //always preceded by call to mousePressed & 1 or more to mouseDragged
        }


        public void mouseEntered(MouseEvent e) {
            //enters bounds of component
        }


        public void mouseExited(MouseEvent e) {
            //mouse moved out of bounds?
        }

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		   public void keyPressed(KeyEvent e){
	        	if(e.getKeyCode() == KeyEvent.VK_Z){
	        		block.clearOnBoard(a, currentDroppingRow, currentDroppingColumn);
	                currentDroppingRow++;
	        	}
	        	else if(e.getKeyCode() == KeyEvent.VK_UP){
	        		block.clearOnBoard(a, currentDroppingRow, currentDroppingColumn);
	                block.rotate(a, currentDroppingRow, currentDroppingColumn);
	                //rotate currently active by 90 degrees
	        	}
	        	else if(e.getKeyCode() == KeyEvent.VK_LEFT){
	        		 //move currently active left
	                block.clearOnBoard(a, currentDroppingRow, currentDroppingColumn);
	                currentDroppingRow--;
	        	}
	        }
    }

	// Method fullLine(int r) checks for a full line in int r(rows) while private void removelines() 
	// uses fullLine method to select full lines, and implements the removal of the line and brings the 
	// row above down, also updates score count and line count to display on the applet; current score each 
	// time a line is cleared/completed +10points, and the amount of lines cleared.
    private boolean fullLine(int r) {
        for (int c = 0; c < 10; c++) {
            if (a[c][r] == 0) {
                return false;
            }

        }
        return true;
    }

    private void removelines() {
        //int noOfFullLines = 0;
    //	if(!block.canDrop(a, currentDroppingRow, currentDroppingColumn)){
        for (int r = 19; r > 0; r--) {
            if (fullLine(r)) {
            	
            	  
                for (int j = r-1 ; j > 0; j--) {

                    // copy j to the row below
                    for (int c = 0; c < 10; c++) {
                        a[c][j + 1] = a[c][j];
                        //for(int z=0; z<10; z++){
                        //a[c][j+2] = a[c][j];
                    }
                }
                r++;
              
                block.clearOnBoard(a, currentDroppingRow, currentDroppingColumn);
                //noOfFullLines = noOfFullLines + 1;
                count = count + 10;
                count1 = count1 + 1;
                HighScore.setText("Score: " + String.valueOf(count));

                gameOver.setText("you filled up: " + String.valueOf(count1) + " lines!");
            }
      //  }
        }
    }

    //a[4][0] = 3; //sets the starting place for the piece in the middle of the array grid
	
    // creates a random new Tetris Block with a possibility of 7 different shapes by a call to array 
	// randomPoolOfBlocks; random index randomly choosing the shape to be picked, followed by a call 
	// to the drawOnBoard method which depending on shape picked will display the shape on the grid
    public void newPiece() {
        int randomIndex = r.nextInt(randomPoolOfBlocks.length);
       block = randomPoolOfBlocks[randomIndex];
        canMoveLeft = true;
      //  block = new LBlock(Color.red, 10, 10, 1);
        block.drawOnBoard(a, currentDroppingRow, currentDroppingColumn);
        game_going = true;
    }

	// call to clear previous position of the block while it drops at fixed intervals by incrementing 
	// currentDroppingColumn while the drawOnBoard method draws the shape on the grid as it drops at fixed intervals. 
    public void dropDown() {

        block.clearOnBoard(a, currentDroppingRow, currentDroppingColumn);
        currentDroppingColumn++;
        block.drawOnBoard(a, currentDroppingRow, currentDroppingColumn);
     //   if(currentDroppingColumn == 19){
        removelines();
        

        //if (block.canRotate(a, currentDroppingRow, currentDroppingColumn)){
          //  block.rotate(a, currentDroppingRow, currentDroppingColumn);
        //}
        }

    public Dimension getPreferredSize() {
        return new Dimension(w * size, h * size);
    }

}



