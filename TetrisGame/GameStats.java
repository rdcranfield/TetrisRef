package TetrisGame;

import javax.swing.JLabel;

public class GameStats {
	 JLabel HighScore;
	 JLabel name;
	 JLabel regno;
	 JLabel gameMessage;
	 
	public GameStats(String playerName, String regno, String gameDescr) {
		 HighScore = new JLabel("Score: " + String.valueOf(0));
		 name = new JLabel(playerName + " : " + regno);
		 gameMessage = new JLabel(gameDescr);
	 }

	public JLabel getHighScore() {
		return HighScore;
	}

	public JLabel getName() {
		return name;
	}

	public JLabel getRegno() {
		return regno;
	}

	public JLabel getGameMessage() {
		return gameMessage;
	}
}
