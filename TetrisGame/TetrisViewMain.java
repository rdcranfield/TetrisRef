package TetrisGame;

import javax.swing.*;

import Shapes.Grid;
import Shapes.IGrid;

import java.awt.*;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: daacra
 * Reg: 1004888
 * Date: 08/11/12
 * Time: 15:06
 */

// Don’t need a main method to begin the program as the applet container basically does this, 
// I have also set the size in this class via the setSize method for when the applet is run as an applet 
// rather than through an internet browser. 
// Public init method implements the testing of the TetrisView component via 
// TetrisView tv = newTetrisView(a) and is added to the panel by the add(tv, borderLayout.CENTER)
public class TetrisViewMain extends JApplet {
	private static final long serialVersionUID = 7933907317239276340L;

	public void init(){
        // test the view component
        Random r = new Random();
        int w = 10;
        int h = 20;
        IGrid grid = new Grid(w, h);
        String name = "Danny Cranfield";
        String regno = "1004888";
        TetrisView tv = new TetrisView(grid,new StudentPlayer(name, regno));
        add(tv, BorderLayout.CENTER);
        getContentPane().add(TetrisView.getStats().getHighScore(), BorderLayout.EAST);
        getContentPane().add(TetrisView.getStats().getName(), BorderLayout.NORTH);
        getContentPane().add(TetrisView.getStats().getGameMessage(), BorderLayout.SOUTH);
        setSize(255, 440);
        this.setVisible(true);
        repaint();
    }

}