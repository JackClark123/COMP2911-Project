package items;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Wall {
	
	private final String wallImage = "Images/crate.png";
	private int posX, posY, size, spacing;
	private ImageIcon img;
	
	public Wall(int posX, int posY, int size , int spacing) {
		this.posX = posX;
		this.posY = posY;
		this.size = size;
		this.spacing = spacing;
		img = new ImageIcon(wallImage);
	}
	
	public Wall() {
		img = new ImageIcon(wallImage);
		posX = 0;
		posY = 0;
	}

	public void print(Graphics g) {
		Image wall = img.getImage();
		g.drawImage(wall, posX * spacing, posY * spacing, size, size, null);
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
