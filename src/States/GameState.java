package States;

import java.awt.Graphics;

public interface GameState {
	
	public void paint(Graphics g);
	
	public void restartMap();
}
