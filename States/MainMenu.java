package States;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Window.PanelController;
import items.Button;

public class MainMenu extends JPanel implements State, MouseMotionListener{

	private static final long serialVersionUID = 1L;
	
	private Button startButton, howToPlayButton, exitButton;
	private ImageIcon background;
	private Image img;

	public MainMenu(PanelController pc) {
		this.setPreferredSize(new Dimension(100, 100));
		startButton = new Button("Images/startButtonUp.png", "Images/startButtonDown.png", "diffselect", pc);
		startButton.setPosition(100, 350);
		
		howToPlayButton = new Button("Images/howToPlayButtonUp.png", "Images/howToPlayButtonDown.png", "objective", pc);
		howToPlayButton.setPosition(100, 440);
		
		exitButton = new Button("Images/exitButtonUp.png", "Images/exitButtonDown.png", "exit", pc);
		exitButton.setPosition(100, 530);
		
		background = new ImageIcon("Images/MainMenu.png");
		img = background.getImage();
		
		this.addMouseListener(startButton);
		this.addMouseListener(howToPlayButton);
		this.addMouseListener(exitButton);
		
		this.addMouseMotionListener(startButton);
		this.addMouseMotionListener(howToPlayButton);
		this.addMouseMotionListener(exitButton);
		this.addMouseMotionListener(this);
		
		this.add(startButton);
		this.add(howToPlayButton);
		this.add(exitButton);
	}
	

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, background.getIconWidth(), background.getIconHeight(), null);
		
		startButton.paint(g);
		howToPlayButton.paint(g);
		exitButton.paint(g);
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		repaint();
	}

}
