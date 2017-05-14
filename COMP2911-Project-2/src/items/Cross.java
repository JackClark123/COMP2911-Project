package items;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Cross {

	private final String wallImage = "Images/greencross.png";
	private int posX, posY, size, spacing;
	private ImageIcon img;
	
	public Cross(int posX, int posY, int size, int spacing) {
		this.posX = posX;
		this.posY = posY;
		this.size = size;
		this.spacing = spacing;
		img = new ImageIcon(wallImage);
	}
	
	public Cross() {
		img = new ImageIcon(wallImage);
		posX = 0;
		posY = 0;
	}

	public void print(Graphics g) {
		Image cross = img.getImage();
		g.drawImage(cross, posX * spacing, posY * spacing, size, size, null);
	}
	
	public void setPosition(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getX() {
		return posX;
	}
	
	public int getY() {
		return posY;
	}
	
	public int getSize() {
		return size;
	}

}
