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
		ImageIcon img = new ImageIcon("Images/temp.png");
		Image background = img.getImage();
		g.drawImage(background, 0, 0, 1280, 900, null);

		img = new ImageIcon("Images/temp2.png");
		Image name = img.getImage();
		g.drawImage(name, 400, 25, 500, 200, null);
		
		img = new ImageIcon("Images/temp3.png");
		Image button1 = img.getImage();
		g.drawImage(button1, 550, 400, 200, 100, null);
		
		img = new ImageIcon("Images/temp4.png");
		Image button2 = img.getImage();
		g.drawImage(button2, 550, 650, 200, 100, null);
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
