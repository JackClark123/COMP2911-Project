package States;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import Map.Map;
import Map.ReadFile;
import PlayGame.GameInfo;
import Window.PanelController;
import items.Player;

public class PlayGame extends JPanel implements State, KeyListener{

	private static final long serialVersionUID = 1L;
	
	private ReadFile file;
	private Map map;
	private GameInfo info;
	private Player player;

	public PlayGame(PanelController pc) {
		
		this.addComponentListener( new ComponentAdapter() {
	        @Override
	        public void componentShown( ComponentEvent e ) {
	            PlayGame.this.requestFocusInWindow();
	        }
	    });
		
		this.setPreferredSize(new Dimension(1280, 900));
		
		file = new ReadFile("input.txt");
		map = file.getMap();
		map.generateMap();

		info = new GameInfo(900, 0, "novice");
		player = new Player(map.getPlayerX(), map.getPlayerY(), map.getGridSpacing(), map.getGridSpacing());
		
		this.addKeyListener(player);
		this.addKeyListener(this);
		this.add(player);
	}

	@Override
	public void paint(Graphics g) {
		
		map.playerCollisonHandling(player.getPosX(), player.getPosY(), player.getPrevX(), player.getPrevY(), player);
		
		map.paint(g);
		player.paint(g);
		
		info.setMoves(player.getMoves());
		info.setComplete(map.mapComplete());
		
		info.print(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
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
