package menus;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameState {
	
	private String curr;
	private State newState;
	
	public GameState(){
		curr = "MAINMENU";
		displayOption(curr);
	}
	
	private void displayOption(String state){
		if(state.equals("MAINMENU")){
			newState = new MainMenu(this);
		}
	}

	public void draw(Graphics2D g) {
		newState.draw(g);
	}

	public void mousePressed(KeyEvent e) {
		newState.MousePressed(e.getX(), e.getY());
	}
}
