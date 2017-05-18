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
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Map.Map;
import Map.ReadFile;
import PlayGame.GameInfo;
import Window.PanelController;
import items.Button;
import items.Player;

public class Intermediate extends JPanel implements GameState, KeyListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;

	private ReadFile file;
	private Map map;
	//new
	private Stack<Map> mapStack;
	private GameInfo info;
	//new
	private Player player;
	private Stack<Player> playerStack;
	
	private ImageIcon background;
	private Image img;
	
	private Button restart, difficulty, next,undo;

	public Intermediate(PanelController pc) {
		background = new ImageIcon("Images/background.png");
		img = background.getImage();

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				Intermediate.this.requestFocusInWindow();
			}
		});

		this.setPreferredSize(new Dimension(1280, 900));

		file = new ReadFile("input.txt");
		map = file.getMap();
		map.generateMap();
		//
		mapStack = new Stack<Map>();
		Map mapPre = map.clone();
		mapStack.push(mapPre);
		
		info = new GameInfo(900, 0, "intermediate");
		player = new Player(map.getPlayerX(), map.getPlayerY(), map.getGridSpacing(), map.getGridSpacing());
		//
		Player playerPre = player.clone();
		playerStack = new Stack<Player>();
		playerStack.push(playerPre);
		
		restart = new Button("Images/resetButtonUp.png", "Images/resetButtonDown.png", "restart", pc, this);
		restart.setPosition(1040, 580);
		//new
		undo = new Button("Images/resetButtonUp.png", "Images/resetButtonDown.png", "undo", pc, this);
		undo.setPosition(1140, 480);
		
		difficulty = new Button("Images/difficultyButtonUp.png", "Images/difficultyButtonDown.png", "diffselect", pc, this);
		difficulty.setPosition(940, 690);
		
		next = new Button("Images/newMapButtonUp.png", "Images/newMapButtonDown.png", "intermediate", pc);
		next.setPosition(940, 780);
		
		//this.addKeyListener(player);
		this.addKeyListener(this);
		this.addMouseMotionListener(this);
		this.addMouseListener(difficulty);
		this.addMouseMotionListener(difficulty);
		this.addMouseListener(next);
		this.addMouseMotionListener(next);
		this.addMouseListener(restart);
		this.addMouseMotionListener(restart);
		//new
		this.addMouseListener(undo);
		this.addMouseMotionListener(undo);
		
		this.add(restart);
		this.add(difficulty);
		this.add(next);
		this.add(player);
		this.add(undo);
	}
	
	public void restartMap() {
		map.resetMap(player);
		player.setPosition(map.getPlayerX(), map.getPlayerY());
		if (this.getKeyListeners() == null) {
			this.addKeyListener(player);
		}
		map.setNumBoxesInPlace(0);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, background.getIconWidth(), background.getIconHeight(), null);
		//map.playerCollisonHandling(player.getPosX(), player.getPosY(), player.getPrevX(), player.getPrevY(), player);

		map.paint(g);
		player.paint(g);

		info.setMoves(player.getMoves());
		info.setComplete(map.mapComplete());
		
		if (map.mapComplete() == true) {
			this.removeKeyListener(player);
		}

		info.print(g);
		//new
		undo.paint(g);
		
		restart.paint(g);
		difficulty.paint(g);
		next.paint(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		//
		player.keyPressed(e);
		map.playerCollisonHandling(player.getPosX(), player.getPosY(), player.getPrevX(), player.getPrevY(), player);
		
		Map mapPre = map.clone();
		mapStack.push(mapPre);
		
		Player playerpre = player.clone();
		playerStack.push(playerpre);
		
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		

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
	public void undo() {
		playerStack.pop();
		if(playerStack.isEmpty()){
			return;
		}
		mapStack.pop();
		map = mapStack.peek();
		//playerStack.pop();
		player.setPosX(playerStack.peek().getPosX());
		player.setPosY(playerStack.peek().getPosY());
		repaint();
	}

}
