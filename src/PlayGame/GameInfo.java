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

	/**
	 * Class constructor
	 * @param offsetX X position of button
	 * @param offsetY Y position of button
	 * @param difficulty String to check
	 */
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
	
	/**
	 * Sets current number of moves
	 * @param moves number to set
	 */
	public void setMoves(int moves) {
		this.moves = moves;
	}

	/**
	 * Sets completion state
	 * @param complete flag set 
	 */
	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	//Prints text in given font and size
	public void print(Graphics g) {
		g.translate(offsetX, offsetY);
		
		g.drawImage(img, 0, 0, background.getIconWidth(), background.getIconHeight(), null);
		
		g.setFont(new Font("Helvetica", Font.PLAIN, 40)); 
		g.setColor(Color.WHITE);
		
		if(moves < 10){
			g.drawString("" + moves, 178, 370);
		}else if(moves < 100){
			g.drawString("" + moves, 170, 370);
		}else{
			g.drawString("" + moves, 152, 370);
		}
		
		g.translate(-offsetX, -offsetY);
		
		if (complete) {
			g.drawImage(img2, 0, 0, completed.getIconWidth(), completed.getIconHeight(), null);
		}
		
	}

}
