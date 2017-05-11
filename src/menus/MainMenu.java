package menus;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class MainMenu extends State{
	
	private String curr = null;
	
	public MainMenu(GameState gs){
		this.gs = gs;
	}
	
	public void init(){
		
	}
	
	public void draw(Graphics2D g){
		ImageIcon img = new ImageIcon("Images/background.png");
		Image background = img.getImage();
		g.drawImage(background, 0, 0, 1280, 900, null);
		
		img = new ImageIcon("Images/title.png");
		Image title = img.getImage();
		g.drawImage(title, 240, -50, 800, 300, null);

		img = new ImageIcon("Images/menuBackground.jpg");
		Image menubackground = img.getImage();
		g.drawImage(menubackground, 539, 324, 202, 415, null);
		
		img = new ImageIcon("Images/newGame.png");
		Image button1 = img.getImage();
		g.drawImage(button1, 540, 325, 200, 100, null);
		
		img = new ImageIcon("Images/options.png");
		Image button2 = img.getImage();
		g.drawImage(button2, 540, 430, 200, 100, null);
		
		img = new ImageIcon("Images/howToPlay.png");
		Image button3 = img.getImage();
		g.drawImage(button3, 540, 535, 200, 100, null);
		
		img = new ImageIcon("Images/exit.png");
		Image button4 = img.getImage();
		g.drawImage(button4, 540, 640, 200, 100, null);
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void MousePressed(double x, double y) {
		if(x >= 0 && x <= 1280){
			if(y >= 0 && y <= 900){
				curr = "how";
				System.out.println(curr);
			}
		}
		
		change(curr);
	}

	public void change(String s){
		if(curr.equals("how")){
			System.exit(0);
		}else if(curr.equals(null)){
			System.out.println("What?");
		}
	}
	
	@Override
	public void MouseReleased(double x, double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void MouseMoved(double x, double y) {
		// TODO Auto-generated method stub
		System.out.println(x + " " + y);
		
	}
}
