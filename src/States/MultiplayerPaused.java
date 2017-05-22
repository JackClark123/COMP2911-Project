package States;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Window.PanelController;
import items.Button;

public class MultiplayerPaused extends JPanel implements State, MouseMotionListener {
	private static final long serialVersionUID = 1L;

	private ImageIcon background;
	private Image img;
	private Button  returnToMM, exitButton, returnToGame;
	private PanelController pc;
	
	public MultiplayerPaused(PanelController pc){
		
		this.pc = pc;
		
		background = new ImageIcon("Images/pauseBackground.png");
		img = background.getImage();
		
		returnToMM = new Button("Images/mainMenuButtonUp.png", "Images/mainMenuButtonDown.png", "mainmenu", pc);
		returnToMM.setPosition(475, 390);
		
		returnToGame = new Button("Images/resumeButtonUp.png", "Images/resumeButtonDown.png", pc.getCurrentDifficulty(), pc);
		returnToGame.setPosition(475, 480);
		
		exitButton = new Button("Images/exitButtonUp.png", "Images/exitButtonDown.png", "exit", pc);
		exitButton.setPosition(475, 570);
		
		this.addMouseListener(returnToMM);
		this.addMouseMotionListener(returnToMM);
		this.addMouseListener(returnToGame);
		this.addMouseMotionListener(returnToGame);
		this.addMouseListener(exitButton);
		this.addMouseMotionListener(exitButton);
		
		this.addMouseMotionListener(this);
		
		this.add(returnToMM);
		this.add(returnToGame);
		this.add(exitButton);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 470, 385, 310, 275, null);
		returnToMM.paint(g);
		returnToGame.paint(g);
		exitButton.paint(g);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		repaint();
		returnToGame.setNextState(pc.getCurrentDifficulty());
	}
}
