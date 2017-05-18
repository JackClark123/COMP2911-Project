package items;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class player extends JComponent implements KeyListener{
	private int x, y;
	private int iniX, iniY;
	
	private final String url;
	private ImageIcon img;
	
	private int size, spacing;
	
	private int moveCount;
	private boolean moved;
	
	
	public player(int X, int Y, int spacing, int size, String url){
		this.iniX = X;
		this.iniY = Y;
		this.x = X;
		this.y = Y;
		this.url = url;
		
		this.spacing = spacing;
		this.size = size;
		moveCount = 0;
		moved = false;
		this.addKeyListener(this);
		this.img = new ImageIcon(url);
	}
	
	public void reset(){
		this.x = this.iniX;
		this.y = this.iniY;
		moveCount = 0;
	}
	
	public int getMoveCount(){
		return moveCount;
	}
	
	public void drawPlayer(Graphics g){
		Image player = img.getImage();
		g.drawImage(player, x, y, size, size, null);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
//		prevX = posX;
//		prevY = posY;
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			y--;
			//moving = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			y++;
//			moving = true;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			x--;
//			moving = true;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			x++;
//			moving = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
