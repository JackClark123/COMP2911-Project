package States;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Window.PanelController;
import items.Button;

public class Objective extends JPanel implements State, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	
	private ImageIcon background, howToPlay, player;
	private Image img, info, data;
	private Button returnToMM, objective, movement;

	/**
	 * Class constructor
	 * @param pc Panel Controller
	 */
	public Objective(PanelController pc) {
		background = new ImageIcon("Images/howToPlayBackground.png");
		img = background.getImage();
		
		howToPlay = new ImageIcon("Images/objective.png");
		info = howToPlay.getImage();
		
		player = new ImageIcon("Images/playerFront.png");
		data = player.getImage();
		
		returnToMM = new Button("Images/mainMenuButtonUp.png", "Images/mainMenuButtonDown.png", "mainmenu", pc);
		returnToMM.setPosition(900, 750);
		
		objective = new Button("Images/objectiveButtonUp.png", "Images/objectiveButtonDown.png", "objective", pc);
		objective.setPosition(50, 200);
		
		movement = new Button("Images/movementButtonUp.png", "Images/movementButtonDown.png", "movement", pc);
		movement.setPosition(50, 290);
		
		this.addMouseListener(returnToMM);
		this.addMouseMotionListener(returnToMM);
		this.addMouseListener(objective);
		this.addMouseMotionListener(objective);
		this.addMouseListener(movement);
		this.addMouseMotionListener(movement);
		
		this.addMouseMotionListener(this);
		
		this.add(returnToMM);
		this.add(objective);
		this.add(movement);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, background.getIconWidth(), background.getIconHeight(), null);
		g.drawImage(info, 400, 200, howToPlay.getIconWidth(), howToPlay.getIconHeight(), null);
		g.drawImage(data, 50, 450, 300, 300, null);
		returnToMM.paint(g);
		objective.paint(g);
		movement.paint(g);
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
