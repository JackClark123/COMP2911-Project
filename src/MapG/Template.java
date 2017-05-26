package MapG;
public enum Template {
	
	A(makeTiles(
			makeRow(Tiles.FLOOR,Tiles.FLOOR,Tiles.FLOOR),
			makeRow(Tiles.FLOOR,Tiles.FLOOR,Tiles.FLOOR),
			makeRow(Tiles.FLOOR,Tiles.FLOOR,Tiles.FLOOR))),
	B(makeTiles(
			makeRow(Tiles.WALL,Tiles.FLOOR,Tiles.FLOOR),
			makeRow(Tiles.FLOOR,Tiles.FLOOR,Tiles.FLOOR),
			makeRow(Tiles.FLOOR,Tiles.FLOOR,Tiles.FLOOR))),
	C(makeTiles(
			makeRow(Tiles.WALL,Tiles.WALL,Tiles.FLOOR),
			makeRow(Tiles.FLOOR,Tiles.FLOOR,Tiles.FLOOR),
			makeRow(Tiles.FLOOR,Tiles.FLOOR,Tiles.FLOOR))),
	D(makeTiles(
			makeRow(Tiles.WALL,Tiles.WALL,Tiles.WALL),
			makeRow(Tiles.FLOOR,Tiles.FLOOR,Tiles.FLOOR),
			makeRow(Tiles.FLOOR,Tiles.FLOOR,Tiles.FLOOR))),
	E(makeTiles(
			makeRow(Tiles.WALL,Tiles.WALL,Tiles.WALL),
			makeRow(Tiles.WALL,Tiles.FLOOR,Tiles.FLOOR),
			makeRow(Tiles.WALL,Tiles.FLOOR,Tiles.FLOOR))),
	F(makeTiles(
			makeRow(Tiles.WALL,Tiles.FLOOR,Tiles.FLOOR),
			makeRow(Tiles.FLOOR,Tiles.FLOOR,Tiles.FLOOR),
			makeRow(Tiles.FLOOR,Tiles.FLOOR,Tiles.WALL))),
	G(makeTiles(
			makeRow(Tiles.WALL,Tiles.FLOOR,Tiles.FLOOR),
			makeRow(Tiles.FLOOR,Tiles.FLOOR,Tiles.FLOOR),
			makeRow(Tiles.WALL,Tiles.FLOOR,Tiles.FLOOR))),
	H(makeTiles(
			makeRow(Tiles.WALL,Tiles.FLOOR,Tiles.FLOOR),
			makeRow(Tiles.FLOOR,Tiles.FLOOR,Tiles.FLOOR),
			makeRow(Tiles.WALL,Tiles.FLOOR,Tiles.WALL))),
	I(makeTiles(
			makeRow(Tiles.WALL,Tiles.FLOOR,Tiles.WALL),
			makeRow(Tiles.FLOOR,Tiles.FLOOR,Tiles.FLOOR),
			makeRow(Tiles.WALL,Tiles.FLOOR,Tiles.WALL))),
	//J(makeTiles(
	//		makeRow(Tiles.WALL,Tiles.FLOOR,Tiles.WALL),
	//		makeRow(Tiles.WALL,Tiles.FLOOR,Tiles.FLOOR),
	//		makeRow(Tiles.WALL,Tiles.WALL,Tiles.WALL))),
	K(makeTiles(
			makeRow(Tiles.WALL,Tiles.WALL,Tiles.WALL),
			makeRow(Tiles.WALL,Tiles.FLOOR,Tiles.FLOOR),
			makeRow(Tiles.FLOOR,Tiles.FLOOR,Tiles.FLOOR))),
	L(makeTiles(
			makeRow(Tiles.FLOOR,Tiles.FLOOR,Tiles.FLOOR),
			makeRow(Tiles.WALL,Tiles.FLOOR,Tiles.WALL),
			makeRow(Tiles.FLOOR,Tiles.FLOOR,Tiles.FLOOR))),
	M(makeTiles(
			makeRow(Tiles.WALL,Tiles.WALL,Tiles.WALL),
			makeRow(Tiles.WALL,Tiles.WALL,Tiles.WALL),
			makeRow(Tiles.FLOOR,Tiles.FLOOR,Tiles.FLOOR))),
	N(makeTiles(
			makeRow(Tiles.WALL,Tiles.WALL,Tiles.WALL),
			makeRow(Tiles.FLOOR,Tiles.WALL,Tiles.FLOOR),
			makeRow(Tiles.FLOOR,Tiles.FLOOR,Tiles.FLOOR))),
	
	
	
	;
	
	public Tiles[][] tiles;
	
	private Template(Tiles[][] t){
		this.tiles = t;
	}
	
	private static Tiles[] makeRow(Tiles...t){
		return t;
	}
	
	private static Tiles[][] makeTiles(Tiles[]...rows){
		Tiles[][] result = new Tiles[rows[0].length][rows.length];
		
		for(int x = 0; x<rows.length;x++){
			for(int y = 0; y< rows[x].length; y++){
				result[y][x]=rows[x][y];
			}
		}
		return result;
	}
}
