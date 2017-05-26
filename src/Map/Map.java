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

public class Map implements Cloneable{
	
	private final int EMPTY = 0;
	private final int WALL = 1;
	private final int PLAYER = 2;
	private final int BOX = 3;
	private final int CROSS = 4;
	
	private List<Wall> walls;
	private List<Box> boxes;
	
	//new undo button
	public List<Box> getBoxes() {
		return boxes;
	}
	public void setBoxes(List<Box> boxes) {
		this.boxes = boxes;
	}
	
	private List<Cross> crosses;
	
	private ArrayList<ArrayList<Integer>> mapArrayList;
	
	//new undo button
	public ArrayList<ArrayList<Integer>> getMapArrayList() {
		return mapArrayList;
	}
	public void setMapArrayList(ArrayList<ArrayList<Integer>> mapArrayList) {
		this.mapArrayList = mapArrayList;
	}
	
	private ArrayList<ArrayList<Integer>> orginalMapArrayList;
	
	private int playerX, playerY;
	private int numBoxesInPlace = 0;
	private int numCrosses = 0;
	
	private int gridSpacing;
	
	public int getGridSpacing() {
		return gridSpacing;
	}

	public void setGridSpacing(int gridSpacing) {
		this.gridSpacing = gridSpacing;
	}

	public Map() {
		
		walls = new ArrayList<Wall>();
		boxes = new ArrayList<Box>();
		crosses = new ArrayList<Cross>();
		mapArrayList = new ArrayList<ArrayList<Integer>>();
		orginalMapArrayList = new ArrayList<ArrayList<Integer>>();
	}
	
	public Map clone(){
		//may clone other staff
		Map o = null;
		try{ 
			o = (Map)super.clone();
			List<Box> boxesNew = new ArrayList<Box>();
			for(int i = 0 ;i<boxes.size();i++){
				Box box = new Box(boxes.get(i).getX(),boxes.get(i).getY(), gridSpacing, gridSpacing);
				boxesNew.add(box);
			}
			o.setBoxes(boxesNew);
			o.setMapArrayList(copy(this.mapArrayList));
		}catch(CloneNotSupportedException e){ 
			e.printStackTrace(); 
		} 
			return o;  
	}
	
	public void addRow(ArrayList<Integer> row) {
		mapArrayList.add(row);
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
					mapArrayList.set(temp1.getY(), mapArrayList.get(temp1.getY())).set(temp1.getX(), EMPTY);
					temp1.incrementPosition(-deltaX, -deltaY);
					mapArrayList.set(temp1.getY(), mapArrayList.get(temp1.getY())).set(temp1.getX(), BOX);
					setBack = true;
				}
			}
			
			for (Box temp2 : boxes) {
				if (!temp1.equals(temp2) && temp1.getX() == temp2.getX() && temp1.getY() == temp2.getY()) {
					player.incrementPosition(-deltaX, -deltaY);
					temp1.incrementPosition(-deltaX, -deltaY);
					mapArrayList.set(temp1.getY(), mapArrayList.get(temp1.getY())).set(temp1.getX(), BOX);
					setBack = true;
				}
			}
			
			if (orginalMapArrayList.get(temp1.getY()).get(temp1.getX()) == CROSS) {
				numBoxes++;
			}
			
		}
		
		numBoxesInPlace = numBoxes;
		
		if (setBack == false && (deltaX != 0 || deltaY != 0)) {
			player.incrementMoves();
		}
	}
	
	public void playerCollisonHandling(int posX, int posY, int prevX, int prevY, Player player) {
		if (posX >= 0 && posX < mapArrayList.size() && posY >= 0 && posY < mapArrayList.get(posY).size()) {
			if (mapArrayList.get(posY).get(posX) != EMPTY && mapArrayList.get(posY).get(posX) != PLAYER && mapArrayList.get(posY).get(posX) != CROSS) {
				
				if (mapArrayList.get(posY).get(posX) == WALL) {
					player.setPosX(prevX);
					player.setPosY(prevY);
				} else if (mapArrayList.get(posY).get(posX) == BOX) {
					int deltaX = posX - prevX;
					int deltaY = posY - prevY;
					int newX = posX + deltaX;
					int newY = posY + deltaY;
					
					mapArrayList.set(posY, mapArrayList.get(posY)).set(posX, EMPTY);
					
					if (newX >= 0 && newX < mapArrayList.size() && newY >= 0 && newY < mapArrayList.get(0).size()) {
						mapArrayList.set(newY, mapArrayList.get(newY)).set(newX, BOX);
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
	
	public void resetMap(Player player) {
		player.setMoves(0);
		player.setSpacing(gridSpacing);
		
		mapArrayList = copy(orginalMapArrayList);
		//copy orginalMap to mapArrayList
		
		Iterator<Wall> wallIterator = walls.iterator();
		Iterator<Box> boxIterator = boxes.iterator();
		Iterator<Cross> crossIterator = crosses.iterator();
		for (int i = 0; i < mapArrayList.size(); i++) {
			for (int j = 0; j < mapArrayList.get(i).size(); j++) {
				if (mapArrayList.get(i).get(j) == WALL) {
					if (wallIterator.hasNext()) {
						wallIterator.next().setPosition(j, i);
					}
				} else if (mapArrayList.get(i).get(j) == PLAYER) {
					setPlayerX(j);
					setPlayerY(i);
				} else if (mapArrayList.get(i).get(j) == BOX) {
					if (boxIterator.hasNext()) {
						boxIterator.next().setPosition(j, i);
					}
				} else if (mapArrayList.get(i).get(j) == CROSS) {
					if (crossIterator.hasNext()) {
						crossIterator.next().setPosition(j, i);
					}
				}
				
			}
		}
	}
	
	public static ArrayList<ArrayList<Integer>> copy(ArrayList<ArrayList<Integer>> input) {
	    ArrayList<ArrayList<Integer>> copy = new ArrayList<ArrayList<Integer>>(input.size());
	    
	    for(int i = 0; i < input.size(); i++) {
	        ArrayList<Integer> line = input.get(i);
	        
	        copy.add(i, new ArrayList<Integer>(line.size()));
	        
	        for(int j = 0; j < line.size(); j++) {
	            copy.get(i).add(j, line.get(j)); //
	        }
	    }
	    return copy;
	}
	
	public void generateMap() {
		//copy mapArray to orginalMap
		orginalMapArrayList = copy(mapArrayList);
		
		
		gridSpacing = 900 / mapArrayList.size();
		
		for (int i = 0; i < mapArrayList.size(); i++) {
			for (int j = 0; j < mapArrayList.get(i).size(); j++) {
				if (mapArrayList.get(i).get(j) == WALL) {
					Wall wall = new Wall(j, i, gridSpacing, gridSpacing);
					walls.add(wall);
				} else if (mapArrayList.get(i).get(j) == PLAYER) {
					mapArrayList.set(i, mapArrayList.get(i)).set(j, EMPTY);
					setPlayerX(j);
					setPlayerY(i);
				} else if (mapArrayList.get(i).get(j) == BOX) {
					Box box = new Box(j, i, gridSpacing, gridSpacing);
					boxes.add(box);
				} else if (mapArrayList.get(i).get(j) == CROSS) {
					numCrosses++;
					Cross cross = new Cross(j, i, gridSpacing, gridSpacing);
					crosses.add(cross);
				}
				
			}
		}
		
	}
	
	public void paintGrid(Graphics g) {
		for (int i = 0; i < mapArrayList.size(); i++) {
			for (int j = 0; j < mapArrayList.get(i).size(); j++) {
				g.setColor(Color.DARK_GRAY);
				g.drawRect(i * gridSpacing, j * gridSpacing, gridSpacing, gridSpacing);
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
			for (Cross temp2 : crosses){
				if (temp.getX() == temp2.getX() && temp.getY() == temp2.getY()){
					temp.setImage("Images/playerFront2.png");
				}
			}
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
