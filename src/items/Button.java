package items;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import States.GameState;
import States.Multiplayer;
import Window.PanelController;


public class Button extends JComponent implements MouseListener, MouseMotionListener, LineListener {
	
	private static final long serialVersionUID = 1L;
	
	private ImageIcon img1, img2;
	private int posX, posY;
	private int cursorPosX, cursorPosY;
	private String nextState;
	private PanelController pc;
	private GameState gs;
	private Multiplayer mp;
	private boolean hovering = false;

	public Button(String image1, String image2, String nextState, PanelController pc) {
		this.pc = pc;
		img1 = new ImageIcon(image1);
		img2 = new ImageIcon(image2);
		this.nextState = nextState;
		this.addMouseListener(this);
		this.addMouseListener(this);
	}
	
	public Button(String image1, String image2, String nextState, PanelController pc, GameState gs) {
		this.pc = pc;
		this.gs = gs;
		img1 = new ImageIcon(image1);
		img2 = new ImageIcon(image2);
		this.nextState = nextState;
		this.addMouseListener(this);
		this.addMouseListener(this);
	}
	
	public Button(String image1, String image2, String nextState, PanelController pc, GameState gs, Multiplayer mp) {
		this.mp = mp;
		this.pc = pc;
		this.gs = gs;
		img1 = new ImageIcon(image1);
		img2 = new ImageIcon(image2);
		this.nextState = nextState;
		this.addMouseListener(this);
		this.addMouseListener(this);
	}
	
	
	
	public void setPosition(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	private boolean isCollision() {
		if (cursorPosX >= posX && cursorPosX <= posX + img1.getIconWidth() && cursorPosY >= posY && cursorPosY <= posY + img1.getIconHeight()) {
			return true;
		} 
		return false;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		if (isCollision()) {
			if (hovering == false) {
				play("Sounds/hovered.wav");
				hovering = true;
			}
			Image button = img1.getImage();
			g.drawImage(button, posX, posY, img1.getIconWidth(), img1.getIconHeight(), null);
		} else {
			hovering = false;
			Image button = img2.getImage();
			g.drawImage(button, posX, posY, img2.getIconWidth(), img2.getIconHeight(), null);
		}
	}
	
	void play(String audioFilePath) {
        File audioFile = new File(audioFilePath);
 
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
 
            AudioFormat format = audioStream.getFormat();
 
            DataLine.Info info = new DataLine.Info(Clip.class, format);
 
            Clip audioClip = (Clip) AudioSystem.getLine(info);
 
            audioClip.addLineListener(this);
 
            audioClip.open(audioStream);
             
            audioClip.start();
             
             
        } catch (UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
         
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		if (isCollision()) {
			play("Sounds/clickedDown.wav");
			if (nextState.equals("restart")) {
				gs.restartMap();
			}else if (nextState.equals("undo")){
				gs.undo();
			} else if (nextState.equals("play")) {
				mp.generateMaps();
			} else {
				if (nextState.equals("diffselect") && gs != null) {
					gs.restartMap();
				}
				pc.setCurrentDifficulty(nextState);
				pc.setPanel(nextState);
			}
		}
		
	}

	public String getNextState() {
		return nextState;
	}

	public void setNextState(String nextState) {
		this.nextState = nextState;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		cursorPosX = e.getX();
		cursorPosY = e.getY();
	}

	@Override
	public void update(LineEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}
