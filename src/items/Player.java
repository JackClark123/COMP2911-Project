package items;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class Player extends JComponent implements KeyListener,Cloneable{
	
	private static final long serialVersionUID = 1L;
	
	private final int UP = 1;
	private final int DOWN = 2;
	private final int RIGHT = 3;
	private final int LEFT = 4;
	
	private int playerState = 1;
	
	private int posX, posY, size, spacing;
	private int prevX, prevY;
	private ImageIcon up, down, left, right;
	
	private int moves = 0;
	private int playerNum;
	private boolean moving = false;
	
	public Player(int posX, int posY, int size, int spacing) {
		playerNum = 1;
		this.posX = posX;
		this.posY = posY;
		this.size = size;
		this.spacing = spacing;
		this.addKeyListener(this);
		up = new ImageIcon("Images/playerFront.png");
		down = new ImageIcon("Images/playerBack.png");
		left = new ImageIcon("Images/playerLeft.png");
		right = new ImageIcon("Images/playerRight.png");
	}
	
	public Player(int posX, int posY, int size, int spacing, int playerNum) {
		this.playerNum = playerNum;
		this.posX = posX;
		this.posY = posY;
		this.size = size;
		this.spacing = spacing;
		this.addKeyListener(this);
		up = new ImageIcon("Images/playerFront.png");
		down = new ImageIcon("Images/playerBack.png");
		left = new ImageIcon("Images/playerLeft.png");
		right = new ImageIcon("Images/playerRight.png");
	}
	
	public Player clone(){
		Player o = null;
		try{
			o = (Player)super.clone();
		}catch(CloneNotSupportedException e){ 
			e.printStackTrace(); 
		} 
		return o;
	}
	
	public void paintComponent(Graphics g) {	
		Image player = null;
		if (playerState == UP) {
			player = down.getImage();
		} else if (playerState == DOWN) {
			player = up.getImage();
		} else if (playerState == LEFT) {
			player = left.getImage();
		} else if (playerState == RIGHT) {
			player = right.getImage();
		}
		
		g.drawImage(player, posX * spacing, posY * spacing, size, size, null);
	}
	
	public int getSpacing() {
		return spacing;
	}

	public void setSpacing(int spacing) {
		this.spacing = spacing;
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
	
	public void decreaseMoves(){
		if(moves <= -1){
			return;
		}else{
			moves--;
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
			
			if (playerNum == 1) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					posY--;
					moving = true;
					playerState = UP;
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					posY++;
					moving = true;
					playerState = DOWN;
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					posX--;
					moving = true;
					playerState = LEFT;
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					posX++;
					moving = true;
					playerState = RIGHT;
				}
			} 
			
			if (playerNum == 2) {
				if (e.getKeyCode() == KeyEvent.VK_W) {
					posY--;
					moving = true;
					playerState = UP;
				} else if (e.getKeyCode() == KeyEvent.VK_S) {
					posY++;
					moving = true;
					playerState = DOWN;
				} else if (e.getKeyCode() == KeyEvent.VK_A) {
					posX--;
					moving = true;
					playerState = LEFT;
				} else if (e.getKeyCode() == KeyEvent.VK_D) {
					posX++;
					moving = true;
					playerState = RIGHT;
				}
			}   
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
