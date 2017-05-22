package Window;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import States.DifficultySelect;
import States.Expert;
import States.Intermediate;
import States.MainMenu;
import States.Movement;
import States.Multiplayer;
import States.MultiplayerPaused;
import States.Novice;
import States.Objective;
import States.Options;
import States.PlayGame;

public class PanelController extends JPanel{
	
	private static final long serialVersionUID = 1L;

	private CardLayout cardLayout;
	
	private MainMenu mainmenu;
	private PlayGame game;
	private Multiplayer multiplayer;
	private DifficultySelect diffSelect;
	private JFrame frame;
	private Novice novice;
	private Intermediate intermediate;
	private Expert expert;
	private Options options;
	private Objective objective;
	private Movement movement;
	private MultiplayerPaused multiplayerPaused;
	
	private String currentDifficulty;
	
	public PanelController(JFrame frame) {
		this.frame = frame;
		this.currentDifficulty = null;
		
		cardLayout = new CardLayout();
		this.setPreferredSize(new Dimension(1280, 900));
		this.setLayout(cardLayout);
		this.setFocusable(true);
		
		mainmenu = new MainMenu(this);
		game = new PlayGame(this);
		multiplayer = new Multiplayer(this);
		diffSelect = new DifficultySelect(this);
		novice = new Novice(this);
		intermediate = new Intermediate(this);
		expert = new Expert(this);
		options = new Options(this);
		multiplayerPaused = new MultiplayerPaused(this);
		objective = new Objective(this);
		movement = new Movement(this);
		
		this.add(mainmenu, "mainmenu");
		this.add(game, "game");
		this.add(multiplayer, "multiplayer");
		this.add(diffSelect, "diffselect");
		this.add(novice, "novice");
		this.add(intermediate, "intermediate");
		this.add(expert, "expert");
		this.add(options, "options");
		this.add(multiplayerPaused, "multiplayerPaused");
		this.add(objective, "objective");
		this.add(movement, "movement");
	}
	
	public void setPanel(String panelName) {
		if (panelName.equals("exit")) {
			frame.dispose();
		}
		cardLayout.show(this, panelName);
	}
	
	public void setCurrentDifficulty(String panelName){
		if(panelName.equals("novice")){
			currentDifficulty = "novice";
		} else if(panelName.equals("intermediate")){
			currentDifficulty = "intermediate";
		} else if(panelName.equals("expert")){
			currentDifficulty = "expert";
		} else if (panelName.equals("multiplayer")) {
			currentDifficulty = "multiplayer";
		}
	}
	
	public String getCurrentDifficulty(){
		return currentDifficulty;
	}

}
