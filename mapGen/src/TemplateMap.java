
public class TemplateMap {
	
	boolean boundary;
	int width, height;
	Template[][] templates;
	
	public TemplateMap(boolean bounds, int width, int height){
		this.boundary = bounds;
		this.width = width;
		this.height = height;
		
		int tpCols = (width+2)/3;
		int tpRows = (height+2)/3;
		
		templates = new Template[tpCols][tpRows];
	}
	
	public boolean hasTemplate(Vectors pos){
		return pos.getX() >= 0 && pos.getX()<templates.length &&
			   pos.getY() >= 0 && pos.getY()<templates[0].length;
	}
	
	public Template getTemplate(Vectors xy){
		if(hasTemplate(xy) !=false){
			return templates[xy.getX()][xy.getY()];
		}
		
		return null;
	}
	
	public Tiles getTiles(Vectors xy){
		
		int px = xy.getX()/3;
		int py = xy.getY()/3;
		int tx = xy.getX() % 3;
		int ty = xy.getY() % 3;
		
		Vectors tpVec = new Vectors(px, py);
		Vectors tileVec = new Vectors(tx, ty);
		
		Template tp = getTemplate(tpVec);
		
		return tp.tiles[tileVec.getX()][tileVec.getY()];
	}
	
	public void put(int x, int y, Template t){
		templates[x][y] = t;
	}
	
	public Rectangles getBounds(){
		return new Rectangles(0,0,width,height);
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public boolean isBounded(){
		return boundary;
	}
}
