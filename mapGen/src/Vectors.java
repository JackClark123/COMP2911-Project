
public class Vectors {
	private int x, y;
	
	public Vectors(int x, int y){
		this.x=x;
		this.y = y;
	}
	
	public String toString(){
		return this.x+","+this.y;
	}
	
	public Vectors add(Vectors xy){
		return new Vectors(x+xy.x,y+xy.y);
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
}
