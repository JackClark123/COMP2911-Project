package States;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Map.MultiplayerMap;
import Map.ReadFile;
import Window.PanelController;
import items.Button;
import items.Player;
import items.TextBox;

public class Multiplayer extends JPanel implements GameState, KeyListener, MouseMotionListener {
	
	private ReadFile fileLeft, fileRight;
	private MultiplayerMap mapLeft, mapRight;
	
	private Player player1, player2;
	
	private boolean keyListenerActive = true;
	private TextBox textBox;
	
	private ImageIcon background;
	private Image img;
	
	private Button pause, play, restart;
	
	private static final long serialVersionUID = 1L;

	/**
	 * Multiplayer constructor
	 * @param pc Panel Controller
	 */
	public Multiplayer(PanelController pc) {
		background = new ImageIcon("Images/background.png");
		img = background.getImage();

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				Multiplayer.this.requestFocusInWindow();
			}
		});

		this.setPreferredSize(new Dimension(1280, 900));
		
		fileLeft = new ReadFile("MultiPlayerLevels/levelLeft.txt");
		mapLeft = fileLeft.getMultiplayerMap();
		mapLeft.generateMap();
		
		fileRight = new ReadFile("MultiPlayerLevels/levelRight.txt");
		mapRight = fileRight.getMultiplayerMap();
		mapRight.setOffset(1280/2);
		mapRight.generateMap();

		player2 = new Player(15, 7, mapRight.getGridSpacing(), mapRight.getGridSpacing(), 1);
		player1 = new Player(4, 7, mapLeft.getGridSpacing(), mapLeft.getGridSpacing(), 2);

		textBox = new TextBox(598, 280);
		
		restart = new Button("Images/resetButtonUp.png", "Images/resetButtonDown.png", "restart", pc, this);
		restart.setPosition(590, 430);
		
		
		pause = new Button("Images/pauseButtonUp.png", "Images/pauseButtonDown.png", "multiplayerPaused", pc, this);
		pause.setPosition(590, 535);
		
		play = new Button("Images/playButtonUp.png", "Images/playButtonDown.png", "play", pc, this, this);
		play.setPosition(590, 320);
		
		this.addKeyListener(this);
		this.addMouseMotionListener(this);
		this.addMouseListener(restart);
		this.addMouseMotionListener(restart);
		this.addMouseListener(pause);
		this.addMouseMotionListener(pause);
		this.addMouseListener(play);
		this.addMouseMotionListener(play);
		
		this.addKeyListener(player1);
		this.addKeyListener(player2);
		
		this.addKeyListener(textBox);	
		
		this.add(textBox);
		this.add(restart);
		this.add(pause);
		this.add(play);
		this.add(player1);
		this.add(player2);
	}
	
	/**
	 * Generates Multiplayer map
	 */
	public void generateMaps() {
		int seed = 	(int) System.currentTimeMillis();
		
		player1.setPosition(4, 7);
		player2.setPosition(15, 7);
		
		fileLeft = new ReadFile("MultiPlayerLevels/levelLeft.txt");
		mapLeft = fileLeft.getMultiplayerMap();
		mapLeft.clearALL();
		mapLeft.placeBoxesAndCrosses(textBox.getNum(), seed);
		mapLeft.generateMap();
	
		fileRight = new ReadFile("MultiPlayerLevels/levelRight.txt");
		mapRight = fileRight.getMultiplayerMap();
		mapRight.clearALL();
		mapRight.setOffset(1280/2);
		mapRight.placeBoxesAndCrosses(textBox.getNum(), seed);
		mapRight.generateMap();
		
		if (keyListenerActive == false) {
			this.addKeyListener(player1);
			this.addKeyListener(player2);
		}
		
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, background.getIconWidth(), background.getIconHeight(), null);
		
		mapLeft.playerCollisonHandling(player1.getPosX(), player1.getPosY(), player1.getPrevX(), player1.getPrevY(), player1);
		mapRight.playerCollisonHandling(player2.getPosX(), player2.getPosY(), player2.getPrevX(), player2.getPrevY(), player2);
		
		mapLeft.paint(g);
		mapRight.paint(g);
		player1.paint(g);
		player2.paint(g);
		
		if (mapLeft.mapComplete() == true || mapRight.mapComplete() == true) {
			this.removeKeyListener(player1);
			this.removeKeyListener(player2);
			keyListenerActive = false;
		}
		
		pause.paint(g);
		play.paint(g);
		restart.paint(g);
		textBox.paint(g);
		
		if (player1.getMoves() == 0) {
			ImageIcon leftInfo = new ImageIcon("Images/infoLeft.png");
			Image img1 = leftInfo.getImage();
			g.drawImage(img1, 140, 100, leftInfo.getIconWidth(), leftInfo.getIconHeight(), null);
		}
		
		if (player2.getMoves() == 0) {
			ImageIcon rightInfo = new ImageIcon("Images/infoRight.png");
			Image img2 = rightInfo.getImage();
			g.drawImage(img2, 850, 100, rightInfo.getIconWidth(), rightInfo.getIconHeight(), null);
		}
		
	}
	
	@Override
	public void restartMap() {
		mapLeft.resetMap(player1);
		player1.setPosition(4, 7);
		mapLeft.setNumBoxesInPlace(0);
		
		mapRight.resetMap(player2);
		player2.setPosition(15, 7);
		mapRight.setNumBoxesInPlace(0);
		
		if (keyListenerActive == false) {
			this.addKeyListener(player1);
			this.addKeyListener(player2);
		}
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		repaint();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
