package TetrisGame;

import javax.swing.*;
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
    public void init(){
        // test the view component
        Random r = new Random();
        int w = 10;
        int h = 20;
        int[][] a = new int[w][h];
        //int[][]array = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = h/2; j < h; j++) {
            }

        }
        TetrisView tv = new TetrisView(a);
        add(tv, BorderLayout.CENTER);
        getContentPane().add(TetrisView.HighScore, BorderLayout.EAST);
        getContentPane().add(TetrisView.name, BorderLayout.NORTH);
        getContentPane().add(TetrisView.gameOver, BorderLayout.SOUTH);
        setSize(255, 440);
        //pack();
        this.setVisible(true);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        repaint();

        //new JEasyFrame(tv, "Danny Cranfield : 1004888");
    }

}