package States;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Window.PanelController;
import items.Button;

public class HowToPlay extends JPanel implements State, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	
	private Button returnToMM;
	private ImageIcon background;
	private Image img;

	/**
	 * Class constructor
	 * @param pc Panel Controller
	 */
	public HowToPlay(PanelController pc) {
		background = new ImageIcon("Images/howToPlayBackground.png");
		img = background.getImage();
		
		returnToMM = new Button("Images/returnButtonUp.png", "Images/returnButtonDown.png", "mainmenu", pc);
		returnToMM.setPosition(900, 750);
		
		this.addMouseListener(returnToMM);
		this.addMouseMotionListener(returnToMM);
		this.addMouseMotionListener(this);
		
		this.add(returnToMM);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, background.getIconWidth(), background.getIconHeight(), null);
		returnToMM.paint(g);
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
