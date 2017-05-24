package MapG;
public class Vectors {
	private int x, y;
	private double fx=0;
	private double gs=0;
	
	public double getFx() {
		return fx;
	}

	public void addFx(double a) {
		this.fx += a;
	}

	public double getGs() {
		return gs;
	}

	public void addGs(double a) {
		this.gs += a;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

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
