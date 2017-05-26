package Map;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import items.Box;
import items.Cross;
import items.Player;
import items.Wall;

public class MultiplayerMap implements Cloneable{

	private final int EMPTY = 0;
	private final int WALL = 1;
	private final int PLAYER = 2;
	private final int BOX = 3;
	private final int CROSS = 4;
	private final int SCALE = 900;
	
	private int offset;
	private boolean completed = false;
	
	private ArrayList<ArrayList<Integer>> orginalMapArrayList;
	
	private int playerX, playerY;
	private int numBoxesInPlace = 0;
	private int numCrosses = 0;
	
	private int gridSpacing;
	
	private List<Wall> walls;
	private List<Box> boxes;
	private List<Cross> crosses;
	
	private ArrayList<ArrayList<Integer>> mapArrayList;
	
	public MultiplayerMap(int offset) {
		this.offset = offset;
		walls = new ArrayList<Wall>();
		boxes = new ArrayList<Box>();
		crosses = new ArrayList<Cross>();
		mapArrayList = new ArrayList<ArrayList<Integer>>();
		orginalMapArrayList = new ArrayList<ArrayList<Integer>>();
	}
	
	
	public void clearALL() {
		boxes.removeAll(boxes);
		crosses.removeAll(crosses);
		walls.removeAll(walls);
		setEmpty(mapArrayList);
		setEmpty(orginalMapArrayList);
	}
	
	public void setEmpty(ArrayList<ArrayList<Integer>> list) {
		for (ArrayList<Integer> temp1 : list) {
			for (Integer temp2 : temp1) {
				temp2.equals(EMPTY);
			}
		}
	}
	
	//new undo button
	public List<Box> getBoxes() {
		return boxes;
	}
	public void setBoxes(List<Box> boxes) {
		this.boxes = boxes;
	}
	
	//new undo button
	public ArrayList<ArrayList<Integer>> getMapArrayList() {
		return mapArrayList;
	}
	public void setMapArrayList(ArrayList<ArrayList<Integer>> mapArrayList) {
		this.mapArrayList = mapArrayList;
	}
	
	public int getGridSpacing() {
		return gridSpacing;
	}

	public void setGridSpacing(int gridSpacing) {
		this.gridSpacing = gridSpacing;
	}
	
	
	public void addRow(ArrayList<Integer> row) {
		mapArrayList.add(row);
	}
	
	
	public boolean mapComplete() {
		if (numBoxesInPlace == numCrosses && numCrosses != 0) {
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
				if (numBoxes > numBoxesInPlace) {
				}
			}
			
		}
		
		numBoxesInPlace = numBoxes;
		
		if (setBack == false && (deltaX != 0 || deltaY != 0)) {
			player.incrementMoves();
		}
	}
	
	public void playerCollisonHandling(int posX, int posY, int prevX, int prevY, Player player) {
		if (offset != 0) {
			posX -= 10;
			prevX -= 10;
		}
		if (posY >= 0 && posY < mapArrayList.size() && posX >= 0 && posX < mapArrayList.get(posY).size()) {
			if (mapArrayList.get(posY).get(posX) != EMPTY && mapArrayList.get(posY).get(posX) != PLAYER && mapArrayList.get(posY).get(posX) != CROSS) {
				
				if (mapArrayList.get(posY).get(posX) == WALL) {
					if (offset != 0) {
						prevX += 10;
					}
					player.setPosX(prevX);
					player.setPosY(prevY);
				} else if (mapArrayList.get(posY).get(posX) == BOX) {
					int deltaX = posX - prevX;
					int deltaY = posY - prevY;
					int newX = posX + deltaX;
					int newY = posY + deltaY;
					
					mapArrayList.set(posY, mapArrayList.get(posY)).set(posX, EMPTY);
					
					if (newY >= 0 && newY < mapArrayList.size() && newX >= 0 && newX < mapArrayList.get(newY).size()) {
						mapArrayList.get(newY).set(newX, BOX);
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
	
	public void placeBoxesAndCrosses(int num, int seed) {
		if (num < 1000) {
			Random rand = new Random(seed);

			int  randboxX, randboxY, randcrossX, randcrossY;
			boolean boxPlaced, crossPlaced;
			for (int i = 0; i < num; i++) {
				boxPlaced = false;
				crossPlaced = false;
				int loopNumber = 0;
				while (boxPlaced == false || crossPlaced == false) {
					loopNumber++;
					randboxX = rand.nextInt(5) + 2;
					randboxY = rand.nextInt(10) + 2;
					randcrossX = rand.nextInt(7) + 1;
					randcrossY = rand.nextInt(12) + 1;
					
					if (loopNumber > 100) {
						boxPlaced = true;
						crossPlaced = true;
					}
					
					if (offset != 0) {
						randboxX++;
						randcrossX++;
					}
					
					if (mapArrayList.get(randboxY).get(randboxX) == EMPTY && boxPlaced == false) {
						if ((randboxX != 4 && randboxY != 7) || (randboxX != 5 && randboxY != 7)) {
							mapArrayList.get(randboxY).set(randboxX, BOX);
							boxPlaced = true;
						}
					}
					
					if (mapArrayList.get(randcrossY).get(randcrossX) == EMPTY && crossPlaced == false) {
						if (randcrossX != 4 || randcrossY != 7 || randcrossX != 5) {
							mapArrayList.get(randcrossY).set(randcrossX, CROSS);
							crossPlaced = true;
						}
					}
				}
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
		
		
		gridSpacing = SCALE / mapArrayList.size();
		
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
	
	public void paintWinner(Graphics g) {
		if (mapComplete()) {
			if (completed == false) {
				completed = true;
			}
			g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
			if (offset == 0) {
				g.drawString("Winner", 200, 200);
			} else {
				g.drawString("Winner", 300, 200);
			}
		}
	}
	
	public void paint(Graphics g) {
		g.translate(offset, 0);
		paintWalls(g);
		paintCrosses(g);
		paintBoxes(g);
		paintWinner(g);
		g.translate(-offset, 0);
	}
	
	

	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
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

