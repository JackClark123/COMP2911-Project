package PlayGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class GameInfo {
	
	private int offsetX, offsetY;
	private int moves = 0;
	private boolean complete = false;
	private ImageIcon background, completed;
	private Image img, img2;

	public GameInfo(int offsetX, int offsetY, String difficulty) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		
		if (difficulty.equals("novice")) {
			background = new ImageIcon("Images/infoNovice.png");
		} else if (difficulty.equals("intermediate")) {
			background = new ImageIcon("Images/infoIntermediate.png");
		} else if (difficulty.equals("expert")) {
			background = new ImageIcon("Images/infoExpert.png");
		}
		
		img = background.getImage();
		
		completed = new ImageIcon("Images/completed.png");
		img2 = completed.getImage();
	}
	
	public void setMoves(int moves) {
		this.moves = moves;
	}
	
	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public void print(Graphics g) {
		g.translate(offsetX, offsetY);
		
		g.drawImage(img, 0, 0, background.getIconWidth(), background.getIconHeight(), null);
		
		g.setFont(new Font("Helvetica", Font.PLAIN, 40)); 
		g.setColor(Color.WHITE);
		g.drawString("" + moves, 180, 550);
		
		g.translate(-offsetX, -offsetY);
		
		if (complete) {
			g.drawImage(img2, 0, 0, completed.getIconWidth(), completed.getIconHeight(), null);
		}
		
	}

}
