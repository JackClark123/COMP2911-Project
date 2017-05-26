package items;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Box {

	private final String wallImage = "Images/hayBale.png";
	private int posX, posY, size, spacing;
	private ImageIcon img;
	
	public Box(int posX, int posY, int size, int spacing) {
		this.posX = posX;
		this.posY = posY;
		this.size = size;
		this.spacing = spacing;
		img = new ImageIcon(wallImage);
	}
	
	public Box() {
		img = new ImageIcon(wallImage);
		posX = 0;
		posY = 0;
	}
	
	public void incrementPosition(int deltaX, int deltaY) {
		posX += deltaX;
		posY += deltaY;
	}

	public void print(Graphics g) {
		Image cross = img.getImage();
		g.drawImage(cross, posX * spacing, posY * spacing, size, size, null);
		img = new ImageIcon(wallImage);
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

	public void setImage(String image){
		img = new ImageIcon(image);
	}
}
