package States;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Window.PanelController;
import items.Button;

public class DifficultySelect extends JPanel implements State, MouseMotionListener{
private static final long serialVersionUID = 1L;
	
	private Button returnToMM, novice, intermediate, expert;
	private ImageIcon background;
	private Image img;

	public DifficultySelect(PanelController pc) {
		background = new ImageIcon("Images/selectDifficultyBackground.png");
		img = background.getImage();
		
		returnToMM = new Button("Images/returnButtonUp.png", "Images/returnButtonDown.png", "mainmenu", pc);
		returnToMM.setPosition(900, 750);
		
		novice = new Button("Images/noviceButtonUp.png", "Images/noviceButtonDown.png", "novice", pc);
		novice.setPosition(200, 200);
		
		intermediate = new Button("Images/intermediateButtonUp.png", "Images/intermediateButtonDown.png", "intermediate", pc);
		intermediate.setPosition(500, 200);
		
		expert = new Button("Images/expertButtonUp.png", "Images/expertButtonDown.png", "expert", pc);
		expert.setPosition(800, 200);
		
		this.addMouseListener(returnToMM);
		this.addMouseMotionListener(returnToMM);
		this.addMouseListener(novice);
		this.addMouseMotionListener(novice);
		this.addMouseListener(intermediate);
		this.addMouseMotionListener(intermediate);
		this.addMouseListener(expert);
		this.addMouseMotionListener(expert);
		
		this.addMouseMotionListener(this);
		
		this.add(returnToMM);
		this.add(novice);
		this.add(intermediate);
		this.add(expert);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, background.getIconWidth(), background.getIconHeight(), null);
		returnToMM.paint(g);
		novice.paint(g);
		intermediate.paint(g);
		expert.paint(g);
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
