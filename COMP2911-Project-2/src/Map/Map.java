package Map;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import items.Box;
import items.Cross;
import items.Player;
import items.Wall;

public class Map {
	
	private final int EMPTY = 0;
	private final int WALL = 1;
	private final int PLAYER = 2;
	private final int BOX = 3;
	private final int CROSS = 4;
	
	private List<Wall> walls;
	private List<Box> boxes;
	private List<Cross> crosses;
	
	private int playerX, playerY;
	private int sizeX, sizeY;
	private int numBoxesInPlace = 0;
	private int numCrosses = 0;
	
	public final int SPACING = 80;
	public final int GRIDSPACING = 90;
	
	private int mapArray[][];
	private int orginalMap[][];

	public Map(int sizeX, int sizeY) {
		
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		mapArray = new int[sizeX][sizeY];
		orginalMap = new int[sizeX][sizeY];
		
		walls = new ArrayList<Wall>();
		boxes = new ArrayList<Box>();
		crosses = new ArrayList<Cross>();
		
	}
	
	public void setMapValue(int x, int y, int value) {
		mapArray[x][y] = value;
	}
	
	public boolean mapComplete() {
		if (numBoxesInPlace == numCrosses) {
			return true;
		}
		return false;
	}
	
	private void itemCollisionHandling(int deltaX, int deltaY, Player player) {
		boolean setBack = false;
		int numBoxes = 0;
		for (Box temp1 : boxes) {
			
			for (Wall temp2 : walls) {
				if (temp1.getX() == temp2.getX() && temp1.getY() == temp2.getY()) {
					player.incrementPosition(-deltaX, -deltaY);
					mapArray[temp1.getX()][temp1.getY()] = EMPTY;
					temp1.incrementPosition(-deltaX, -deltaY);
					mapArray[temp1.getX()][temp1.getY()] = BOX;
					setBack = true;
				}
			}
			
			for (Box temp2 : boxes) {
				if (!temp1.equals(temp2) && temp1.getX() == temp2.getX() && temp1.getY() == temp2.getY()) {
					player.incrementPosition(-deltaX, -deltaY);
					temp1.incrementPosition(-deltaX, -deltaY);
					mapArray[temp1.getX()][temp1.getY()] = BOX;
					setBack = true;
				}
			}
			
			if (orginalMap[temp1.getX()][temp1.getY()] == CROSS) {
				numBoxes++;
			}
			
		}
		
		numBoxesInPlace = numBoxes;
		
		if (setBack == false && (deltaX != 0 || deltaY != 0)) {
			player.incrementMoves();
		}
	}
	
	public void playerCollisonHandling(int posX, int posY, int prevX, int prevY, Player player) {
		if (posX >= 0 && posX < sizeX && posY >= 0 && posY < sizeY) {
			if (mapArray[posX][posY] != EMPTY && mapArray[posX][posY] != PLAYER && mapArray[posX][posY] != CROSS) {
				
				if (mapArray[posX][posY] == WALL) {
					player.setPosX(prevX);
					player.setPosY(prevY);
				} else if (mapArray[posX][posY] == BOX) {
					int deltaX = posX - prevX;
					int deltaY = posY - prevY;
					int newX = posX + deltaX;
					int newY = posY + deltaY;
					
					mapArray[posX][posY] = 0;
					
					if (newX >= 0 && newX < sizeX && newY >= 0 && newY < sizeY) {
						mapArray[newX][newY] = BOX;
					}
					
					for (Box temp : boxes) {
						
						if (temp.getX() == posX && temp.getY() == posY) {
							temp.setPosition(newX, newY);
						}
					}
					
					itemCollisionHandling(deltaX, deltaY, player);
					
				}
			} else {
				player.incrementMoves();
			}
		}
		
	}
	
	public void copyArray(int[][] from, int[][] to) {
		for (int i = 0; i < from.length; i++) {
			for (int j = 0; j < from[i].length; j++) {
				to[i][j] = from[i][j];
			}
		}
	}
	
	public void resetMap(Player player) {
		player.setMoves(0);
		copyArray(orginalMap, mapArray);
		Iterator<Wall> wallIterator = walls.iterator();
		Iterator<Box> boxIterator = boxes.iterator();
		Iterator<Cross> crossIterator = crosses.iterator();
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				if (orginalMap[i][j] == WALL) {
					if (wallIterator.hasNext()) {
						wallIterator.next().setPosition(i, j);
					}
				} else if (orginalMap[i][j] == PLAYER) {
					setPlayerX(i);
					setPlayerY(j);
				} else if (orginalMap[i][j] == BOX) {
					if (boxIterator.hasNext()) {
						boxIterator.next().setPosition(i, j);
					}
				} else if (orginalMap[i][j] == CROSS) {
					if (crossIterator.hasNext()) {
						crossIterator.next().setPosition(i, j);
					}
				}
				
			}
		}
	}
	
	public void generateMap() {
		copyArray(mapArray, orginalMap);
		
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				if (orginalMap[i][j] == WALL) {
					Wall wall = new Wall(i, j, GRIDSPACING, GRIDSPACING);
					walls.add(wall);
				} else if (orginalMap[i][j] == PLAYER) {
					mapArray[i][j] = EMPTY;
					setPlayerX(i);
					setPlayerY(j);
				} else if (orginalMap[i][j] == BOX) {
					Box box = new Box(i, j, GRIDSPACING, GRIDSPACING);
					boxes.add(box);
				} else if (orginalMap[i][j] == CROSS) {
					numCrosses++;
					Cross cross = new Cross(i, j, GRIDSPACING, GRIDSPACING);
					crosses.add(cross);
				}
				
			}
		}
	}
	
	public void paintGrid(Graphics g) {
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				g.setColor(Color.DARK_GRAY);
				g.drawRect(i * GRIDSPACING, j * GRIDSPACING, GRIDSPACING, GRIDSPACING);
			}
		}
	}
	
	public void paintWalls(Graphics g) {
		for (Wall temp : walls) {
			temp.print(g);
		}
	}
	
	public void paintBoxes(Graphics g) {
		for (Box temp : boxes) {
			temp.print(g);
		}
	}
	
	public void paintCrosses(Graphics g) {
		for (Cross temp : crosses) {
			temp.print(g);
		}
	}
	
	public void paint(Graphics g) {
		paintGrid(g);
		paintWalls(g);
		paintCrosses(g);
		paintBoxes(g);
	}

	public int getPlayerX() {
		return playerX;
	}

	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}

	public int getPlayerY() {
		return playerY;
	}

	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}

	public int getNumBoxesInPlace() {
		return numBoxesInPlace;
	}

	public void setNumBoxesInPlace(int numBoxesInPlace) {
		this.numBoxesInPlace = numBoxesInPlace;
	}
	
	
}
