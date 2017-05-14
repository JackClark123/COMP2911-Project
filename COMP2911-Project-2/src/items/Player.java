package items;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class Player extends JComponent implements KeyListener{
	
	private static final long serialVersionUID = 1L;
	
	private final String playerImage = "Images/player.png";
	private int posX, posY, size, spacing;
	private int prevX, prevY;
	private ImageIcon img;
	
	private int moves = 0;
	private boolean moving = false;
	
	public Player(int posX, int posY, int size, int spacing) {
		this.posX = posX;
		this.posY = posY;
		this.size = size;
		this.spacing = spacing;
		this.addKeyListener(this);
		img = new ImageIcon(playerImage);
	}

	public void paintComponent(Graphics g) {		
		Image player = img.getImage();
		g.drawImage(player, posX * spacing, posY * spacing, size, size, null);
	}
	
	public void setPosition(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public void incrementPosition(int deltaX, int deltaY) {
		posX += deltaX;
		posY += deltaY;
	}
	
	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}
	
	public void incrementMoves() {
		if (moving) {
			moves++;
			moving = false;
		}
	}

	public int getPrevX() {
		return prevX;
	}

	public void setPrevX(int prevX) {
		this.prevX = prevX;
	}

	public int getPrevY() {
		return prevY;
	}

	public void setPrevY(int prevY) {
		this.prevY = prevY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int imgSize() {
		return size;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		prevX = posX;
		prevY = posY;
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			posY--;
			moving = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			posY++;
			moving = true;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			posX--;
			moving = true;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			posX++;
			moving = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
