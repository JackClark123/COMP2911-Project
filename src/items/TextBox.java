package items;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;

public class TextBox extends JComponent implements KeyListener{

	private static final long serialVersionUID = 1L;
	
	private String text = "Enter number";
	private int posX, posY;
	
	private boolean empty = true;

	public TextBox(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public void paint(Graphics g) {
		g.setFont(new Font("TimesRoman", Font.PLAIN, 18)); 
		if (empty) {
			g.drawString(text, posX, posY);
			g.drawString("of boxes", posX + 12, posY + 30);
		} else {
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
			g.drawString(text, posX + 35 - 4 * text.length(), posY + 30);
		}
	}
	
	public int getNum() {
		if (!text.equals("Enter number")) {
			return Integer.parseInt(text);
		}
		return 0;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char key = e.getKeyChar();
		
		if (Character.isDigit(key)) {
			if (empty == true) {
				text = "";
			}
			text += key;
			empty = false;
		}
		
		repaint();
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

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			if(text.length() != 0) {
				text = text.substring(0, text.length() - 1);
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
