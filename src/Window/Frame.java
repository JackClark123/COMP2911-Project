package Window;

import java.awt.Container;

import javax.swing.JFrame;

public class Frame {
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 900;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Warehouse Boss");
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		Container contentPane = frame.getContentPane();
		contentPane.add(new PanelController(frame));
		frame.setVisible(true);
	}

}
