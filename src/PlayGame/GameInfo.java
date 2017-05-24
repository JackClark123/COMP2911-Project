package PlayGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
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

public class GameInfo implements LineListener {
	
	private int offsetX, offsetY;
	private int moves = 0;
	private boolean complete = false;
	private ImageIcon background, completed;
	private Image img, img2;
	private boolean soundPlayed = false;

	public GameInfo(int offsetX, int offsetY, String difficulty) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		
		if (difficulty.equals("novice")) {
			background = new ImageIcon("Images/infoNovice.png");
		} else if (difficulty.equals("intermediate")) {
			background = new ImageIcon("Images/infoIntermediate.png");
		} else if (difficulty.equals("expert")) {
			background = new ImageIcon("Images/infoExpert.png");
		}
		
		img = background.getImage();
		
		completed = new ImageIcon("Images/completed.png");
		img2 = completed.getImage();
	}
	
	public void setMoves(int moves) {
		this.moves = moves;
	}
	
	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
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

	public void print(Graphics g) {
		g.translate(offsetX, offsetY);
		
		g.drawImage(img, 0, 0, background.getIconWidth(), background.getIconHeight(), null);
		
		g.setFont(new Font("Helvetica", Font.PLAIN, 40)); 
		g.setColor(Color.WHITE);
		g.drawString("" + moves, 180, 550);
		
		g.translate(-offsetX, -offsetY);
		
		if (complete) {
			if (soundPlayed == false) {
				play("Sounds/winner.wav");
				soundPlayed = true;
			}
			g.drawImage(img2, 0, 0, completed.getIconWidth(), completed.getIconHeight(), null);
		}
		
	}

	@Override
	public void update(LineEvent event) {
		// TODO Auto-generated method stub
		
	}

}
