package MapG;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Renderer {
	
	private Graphics2D g;
	private Rectangles bounds;
	private TemplateMap tm;
	private int scale;
	
	private void drawFloor(){
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, scale+1, scale+1);
	}
	
	private void drawWall(){
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, scale, scale);
	}
	
	private void drawTile(Tiles t, int x, int y){
		AffineTransform origXfm = g.getTransform();
		g.translate(x*scale,y*scale);
		switch(t){
		case FLOOR:
			drawFloor();
			break;
		case WALL:
			drawWall();
			break;
		//case GOAL:
		//	break;
		default:
			break;
		}
		g.setTransform(origXfm);
	}
	
	private void drawMap(){
		for(int x = 0; x<bounds.getWidth();x++){
			for(int y=0;y<bounds.getHeight();y++){
				Vectors xy = new Vectors(x,y);
				drawTile(tm.getTiles(xy), x, y);
			}
		}
	}
	
	public void draw(Graphics2D g, Dimension dim, TemplateMap tm){
		this.g= g;
		if(g == null){
			System.out.println("g is null");
		}
		this.tm = tm;
		bounds = tm.getBounds();
		
		AffineTransform origXfm = g.getTransform();
		
		scale = Math.min((dim.width) / (bounds.getWidth() + 2),
                (dim.height) / (bounds.getHeight() + 2));
		g.translate(bounds.getX(),bounds.getY());
		
		drawMap();
		
		g.setTransform(origXfm);
	}

}
