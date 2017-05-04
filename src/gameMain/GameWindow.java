package gameMain;
import javax.swing.JFrame;

import gameMain.GamePanel;

public class GameWindow {
	
	public static void main(String[] args){
		JFrame window = new JFrame("Game Window");
		
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
		
		window.pack();
	}
}
