package Window;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import States.DifficultySelect;
import States.Expert;
import States.Intermediate;
import States.Novice;
import States.MainMenu;
import States.PlayGame;
import States.Options;
import States.Objective;
import States.Movement;

public class PanelController extends JPanel{
	
	private static final long serialVersionUID = 1L;

	private CardLayout cardLayout;
	
	private MainMenu mainmenu;
	private PlayGame game;
	//private HowToPlay howToPlay;
	private DifficultySelect diffSelect;
	private JFrame frame;
	private Novice novice;
	private Intermediate intermediate;
	private Expert expert;
	private Options options;
	private Objective objective;
	private Movement movement;
	
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
		//howToPlay = new HowToPlay(this);
		diffSelect = new DifficultySelect(this);
		novice = new Novice(this);
		intermediate = new Intermediate(this);
		expert = new Expert(this);
		options = new Options(this);
		objective = new Objective(this);
		movement = new Movement(this);
		
		this.add(mainmenu, "mainmenu");
		this.add(game, "game");
		//this.add(howToPlay, "howToPlay");
		this.add(diffSelect, "diffselect");
		this.add(novice, "novice");
		this.add(intermediate, "intermediate");
		this.add(expert, "expert");
		this.add(options, "options");
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
		}else if(panelName.equals("intermediate")){
			currentDifficulty = "intermediate";
		}else if(panelName.equals("expert")){
			currentDifficulty = "expert";
		}
	}
	
	public String getCurrentDifficulty(){
		return currentDifficulty;
	}

}
