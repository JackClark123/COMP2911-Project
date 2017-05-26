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
	
	/**
	 * Class constructor
	 * @param frame Window frame
	 */
	public PanelController(JFrame frame) {
		this.frame = frame;
		this.currentDifficulty = null;
		
		cardLayout = new CardLayout();
		this.setPreferredSize(new Dimension(1280, 900));
		this.setLayout(cardLayout);
		this.setFocusable(true);
		
		mainmenu = new MainMenu(this);
		game = new PlayGame(this);
		diffSelect = new DifficultySelect(this);
		options = new Options(this);
		multiplayerPaused = new MultiplayerPaused(this);
		objective = new Objective(this);
		movement = new Movement(this);
		
		this.add(mainmenu, "mainmenu");
		this.add(game, "game");
		this.add(diffSelect, "diffselect");
		this.add(options, "options");
		this.add(multiplayerPaused, "multiplayerPaused");
		this.add(objective, "objective");
		this.add(movement, "movement");
	}
	
	/**
	 * Sets action depending on panel name
	 * @param panelName Panel name to check
	 */
	public void setPanel(String panelName) {
		if (panelName.equals("exit")) {
			frame.dispose();
		}
		
		if (panelName.equals("novice")) {
			novice = new Novice(this);
			this.add(novice, "novice");
		} else if (panelName.equals("intermediate")) {
			intermediate = new Intermediate(this);
			this.add(intermediate, "intermediate");
		} else if (panelName.equals("expert")) {
			expert = new Expert(this);
			this.add(expert, "expert");
		} else if (panelName.equals("multiplayer")) {
			multiplayer = new Multiplayer(this);
			this.add(multiplayer, "multiplayer");
		}
		
		cardLayout.show(this, panelName);
	}
	
	public void setPanelResume(String panelName) {
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
