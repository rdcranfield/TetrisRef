package TetrisGame;

public class StudentPlayer extends Player{
	
    String regno;
    
    public String getRegno() {
		return regno;
	}

	public StudentPlayer(String name, String regno) {
    	super(name);
    	this.regno = regno;
    }

}
