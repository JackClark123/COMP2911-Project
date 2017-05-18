package items;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import States.GameState;
import Window.PanelController;


public class Button extends JComponent implements MouseListener, MouseMotionListener{
	
	private static final long serialVersionUID = 1L;
	
	private ImageIcon img1, img2;
	private int posX, posY;
	private int cursorPosX, cursorPosY;
	private String nextState;
	private PanelController pc;
	private GameState gs;

	public Button(String image1, String image2, String nextState, PanelController pc) {
		this.pc = pc;
		img1 = new ImageIcon(image1);
		img2 = new ImageIcon(image2);
		this.nextState = nextState;
		this.addMouseListener(this);
		this.addMouseListener(this);
	}
	
	public Button(String image1, String image2, String nextState, PanelController pc, GameState gs) {
		this.pc = pc;
		this.gs = gs;
		img1 = new ImageIcon(image1);
		img2 = new ImageIcon(image2);
		this.nextState = nextState;
		this.addMouseListener(this);
		this.addMouseListener(this);
	}
	
	
	
	public void setPosition(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	private boolean isCollision() {
		if (cursorPosX >= posX && cursorPosX <= posX + img1.getIconWidth() && cursorPosY >= posY && cursorPosY <= posY + img1.getIconHeight()) {
			return true;
		} 
		return false;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		if (isCollision()) {
			Image button = img1.getImage();
			g.drawImage(button, posX, posY, img1.getIconWidth(), img1.getIconHeight(), null);
		} else {
			Image button = img2.getImage();
			g.drawImage(button, posX, posY, img2.getIconWidth(), img2.getIconHeight(), null);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (isCollision()) {
			
			if (nextState.equals("restart")) {
				gs.restartMap();
			}else{
				if (nextState.equals("diffselect") && gs != null) {
					gs.restartMap();
				}
				pc.setCurrentDifficulty(nextState);
				pc.setPanel(nextState);
			}
		}
		
	}

	public String getNextState() {
		return nextState;
	}

	public void setNextState(String nextState) {
		this.nextState = nextState;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		cursorPosX = e.getX();
		cursorPosY = e.getY();
	}
	
}
