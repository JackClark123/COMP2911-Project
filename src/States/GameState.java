package States;

import java.awt.Graphics;

public interface GameState {
	
	public void paint(Graphics g);
	
	/**
	 * Restarts the map
	 */
	public void restartMap();
	
	/**
	 * Undo last move
	 */
	public void undo();
}
