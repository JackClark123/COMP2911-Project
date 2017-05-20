import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MapDisplay extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Generator gen;
	private Renderer render;
	TemplateMap tm;
	Graphics2D buffG;
	Dimension bufferDim;
	private BufferedImage buffer;
	
	public MapDisplay(){
		
		render = new Renderer();
		Random r = new Random();
		int seed = r.nextInt();
		generate(seed);
		repaint();
	}
	
	public Generator generate(int seed){
		gen = new Generator(new Random(seed), 9, 9, true);
		gen.generate();
		return gen;
	}
	
	@Override
	public void paint(Graphics g){
		
		fixBuffer(g);
	        
	    buffG.setColor(Color.BLACK);
	    buffG.fillRect(0, 0, bufferDim.width, bufferDim.height);
		
		render.draw(buffG, bufferDim, gen.getMap());
		
		g.drawImage(buffer, 0, 0, this);
	}
	
	private void fixBuffer(Graphics g) {
        // If the size of the frame has changed, recreate the buffer
        if (!getSize().equals(bufferDim)) {
            bufferDim = new Dimension(getSize());
            buffer = new BufferedImage(bufferDim.width, bufferDim.height,
                    BufferedImage.TYPE_INT_ARGB);
            buffG = buffer.createGraphics();
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Map Display");
		MapDisplay panel = new MapDisplay();
		panel.setPreferredSize(new Dimension(640, 480));
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

	}

}
