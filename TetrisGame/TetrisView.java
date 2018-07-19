package TetrisGame;

import javax.swing.*;

import Shapes.Block;
import Shapes.IGrid;
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
	static Color[] tblock_colors = {WHITE, BLACK, GREEN, BLUE, RED,
	            YELLOW, MAGENTA, PINK, CYAN, DARK_GRAY, ORANGE};
	private static final long serialVersionUID = 1L;
	JPanel myTetrisCanvas;
    public boolean canMoveLeft = true;
    public boolean isCanMoveLeft() {
		return canMoveLeft;
	}
    
    int w, h;
    static int size = 20;
    Random r = new Random();

	int score = 0;
    int linesMatched = 0;
    boolean game_over;
    
    boolean hasBlockFallen;
    MouseHandler handler = new MouseHandler();
   
    public static boolean game_going = true;

    int currentDroppingRow, currentDroppingColumn;
    int endCurrentDroppingRows, endCurrentDroppingColumn;
    static GameStats stats;  

    public static GameStats getStats() {
		return stats;
	}

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
	private IGrid board;
	
    public TetrisView(IGrid board, StudentPlayer player) {
        this.board = board;
    
        currentDroppingColumn = 4;
        currentDroppingRow = 0;
        endCurrentDroppingRows = 19;
        endCurrentDroppingColumn = 9;
        hasBlockFallen = false;
       
        stats = new GameStats( player.getName(),  player.getRegno(), Constants.startGameMsg );

        addMouseListener(handler);
        startGameTimer();
    }
 
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                g.setColor(tblock_colors[board.getGrid()[i][j]]);
                g.fill3DRect(i * size, j * size, size, size, true);
            }
        }
    }

    public boolean isGameOver(int[][] a, int currentDroppingRow, int currentDroppingColumn) {
    	return !(block.canDrop(a, currentDroppingRow, currentDroppingColumn));
    }
    public void startGameTimer() {
        final Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                try {
                    if (game_going) {;
                        if(block.canRightLeftMove(currentDroppingRow, currentDroppingColumn)){
	                        if (block.canDrop(board.getGrid(), currentDroppingRow, currentDroppingColumn)) {	
	                            dropDown(); //this makes the block drop a line	                           
	                        } else {
	                        	resetBlockPositions();
	                            // need to drop a new one.
	                            newPiece();	
	                            if (isGameOver(board.getGrid(), currentDroppingRow, currentDroppingColumn)) {
	                            	gameOver(timer);	                               
	                            }
	                        }
                        }else{                                         	
                        	currentDroppingRow = block.getDropRow(currentDroppingRow);                        
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

	protected void resetBlockPositions() {
		 currentDroppingColumn = 4; 
         currentDroppingRow = 0;		
	}

	protected void gameOver(Timer timer) {
		 game_going = false;
         timer.cancel();
         stats.getGameMessage().setText(Constants.endGameMsg + String.valueOf(score));
         stats.getHighScore().setText(Constants.scoreTxt + String.valueOf(score));
	}
	
    public class MouseHandler implements MouseListener {
        public void mouseClicked(MouseEvent e) {
        }
        
        public void mousePressed(MouseEvent e) {
            //called when mouse button is pressed while mouse cursor is on component
            if (e.getButton() == MouseEvent.BUTTON3) {  //right mouse button
                //move currently active right
                block.clearOnBoard(board.getGrid(), currentDroppingRow, currentDroppingColumn);
                currentDroppingRow++;
            } else if (e.getButton() == MouseEvent.BUTTON2) {   //middle mouse button
                block.clearOnBoard(board.getGrid(), currentDroppingRow, currentDroppingColumn);          
                block.rotate(board.getGrid(), currentDroppingRow, currentDroppingColumn);
                //rotate currently active by 90 degrees
            } else { 
                block.clearOnBoard(board.getGrid(), currentDroppingRow, currentDroppingColumn);
                currentDroppingRow--;
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
    }

    private boolean fullLine(int r) {
        for (int c = 0; c < 10; c++) {
            if (board.getGrid()[c][r] == 0) {
                return false;
            }
        }
        return true;
    }

    private void removelines() {
    	for (int r = 19; r > 0; r--) {
            if (fullLine(r)) {         	         
                for (int j = r-1 ; j > 0; j--) {
                    for (int c = 0; c < 10; c++) {
                    	board.getGrid()[c][j+1] = board.getGrid()[c][j];
                    }
                }
                r++;
                block.clearOnBoard(board.getGrid(), currentDroppingRow, currentDroppingColumn);
                updateScore();
                updateTotalLines();
            }
        }
    }
 
    private void updateTotalLines() {
    	linesMatched++;
    	stats.getGameMessage().setText("you filled up: " + String.valueOf(linesMatched) + " lines!");
	}

	private void updateScore() {
		score += 10;
		stats.getHighScore().setText(Constants.scoreTxt + String.valueOf(score));
	}

	public void newPiece() {
        int randomIndex = r.nextInt(randomPoolOfBlocks.length);
        block = randomPoolOfBlocks[randomIndex];
        canMoveLeft = true;
        block.drawOnBoard(board.getGrid(), currentDroppingRow, currentDroppingColumn);
        game_going = true;
    }

	// call to clear previous position of the block while it drops at fixed intervals by incrementing 
	// currentDroppingColumn while the drawOnBoard method draws the shape on the grid as it drops at fixed intervals. 
    public void dropDown() {
        block.clearOnBoard(board.getGrid(), currentDroppingRow, currentDroppingColumn);
        currentDroppingColumn++;
        block.drawOnBoard(board.getGrid(), currentDroppingRow, currentDroppingColumn);
        removelines();
    }

    public Dimension getPreferredSize() {
        return new Dimension(w * size, h * size);
    }
}



