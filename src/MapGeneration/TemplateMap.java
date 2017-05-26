package MapGeneration;
public class TemplateMap {
	
	int width, height;
	Template[][] templates;
	
	/**
	 * Map constructor
	 * @param width Map width
	 * @param height Map height
	 */
	public TemplateMap(int width, int height){
		this.width = width;
		this.height = height;
		
		int tpCols = (width+2)/3;
		int tpRows = (height+2)/3;
		
		templates = new Template[tpCols][tpRows];
	}
	
	/**
	 * Checks if a position has a template
	 * @param pos Position to check
	 * @return True if template exists at position. False otherwise
	 */
	public boolean hasTemplate(Vectors pos){
		return pos.getX() >= 0 && pos.getX()<templates.length &&
			   pos.getY() >= 0 && pos.getY()<templates[0].length;
	}
	
	/**
	 * Gets the template at the given position if it exists
	 * @param xy Position to get
	 * @return Template at position if it exists. Null otherwise
	 */
	public Template getTemplate(Vectors xy){
		if(hasTemplate(xy) !=false){
			return templates[xy.getX()][xy.getY()];
		}
		
		return null;
	}
	
	/**
	 * Gets Tile corresponding to given position in a map
	 * @param xy Position to get
	 * @return Tile value of given position
	 */
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
	
	/**
	 * Puts a template at the given position
	 * @param x X position to put
	 * @param y Y position to put
	 * @param t Template to put
	 */
	public void put(int x, int y, Template t){
		templates[x][y] = t;
	}
	
	/**
	 * Gets map width
	 * @return Width of map
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 *  Gets map height
	 * @return Height of map
	 */
	public int getHeight(){
		return height;
	}
}
