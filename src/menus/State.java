package menus;

public abstract class State {
	
	protected GameState gs;
	
	public abstract void init();
	public abstract void draw(java.awt.Graphics2D g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	public abstract void MousePressed(double x,double y);
	public abstract void MouseReleased(double x, double y);
	public abstract void MouseMoved(double x, double y);
	
}
