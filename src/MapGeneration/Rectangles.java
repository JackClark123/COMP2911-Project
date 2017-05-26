package MapGeneration;
public class Rectangles {
	
	private int x,y,w,h;
	
	public Rectangles(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getWidth(){
		return this.w;
	}
	
	public int getHeight(){
		return this.h;
	}
	
	public int bottom(){
		return this.y + this.getHeight();
	}
	
	public int right(){
		return this.x + this.getWidth();
	}
	
	
	
}
