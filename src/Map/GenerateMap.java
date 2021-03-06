package Map;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

import MapGeneration.Generator;
import MapGeneration.Tiles;
import MapGeneration.Vectors;

public class GenerateMap {
	private Generator gen;
	private Map map;
	private int box, moveHolder;

	/**
	 * Constructor generates random map and populates it
	 * @param seed Random generation seed
	 * @param size Overall map size
	 * @param box Maximum number of boxes
	 * @param move handles box placement during map generation
	 */
	public GenerateMap(int seed, int size, int box, int move) {
		this.box = box;
		this.moveHolder = move;
		size = 3  *  size;
		map = new Map();
		gen = new Generator(new Random(seed), size, size);
		gen.generate();
		int[][] mapPre = new int[gen.getMap().getWidth()][gen.getMap().getWidth()];
		for (int i = 0; i < gen.getMap().getWidth(); i++) {
			for (int j = 0; j < gen.getMap().getHeight(); j++) {
				if (gen.getMap().getTiles(new Vectors(i, j)) == Tiles.FLOOR) {
					mapPre[i][j] = 0;
				}
				if (gen.getMap().getTiles(new Vectors(i, j)) == Tiles.WALL) {
					mapPre[i][j] = 1;
				}
				if (gen.getMap().getTiles(new Vectors(i, j)) == Tiles.PLAYER) {
					mapPre[i][j] = 2;
				}
			}
		}
		ArrayList<Vectors> boxes = new ArrayList<Vectors>();
		ArrayList<Vectors> goal = new ArrayList<Vectors>();
		Vectors Player = new Vectors(1, 1);

		int[][] Mapin = new int[mapPre.length][mapPre.length];
		for (int i = 0; i < mapPre.length; i++) {
			for (int j = 0; j < mapPre[0].length; j++) {
				Mapin[j][i] = mapPre[j][i];
			}
		}

		while (!this.addItems(mapPre, boxes, goal, Player)) {
			gen = new Generator(new Random(seed), size, size);
			gen.generate();
			mapPre = new int[gen.getMap().getWidth()][gen.getMap().getWidth()];
			for (int i = 0; i < gen.getMap().getWidth(); i++) {
				for (int j = 0; j < gen.getMap().getHeight(); j++) {
					if (gen.getMap().getTiles(new Vectors(i, j)) == Tiles.FLOOR) {
						mapPre[i][j] = 0;
					}
					if (gen.getMap().getTiles(new Vectors(i, j)) == Tiles.WALL) {
						mapPre[i][j] = 1;
					}
					if (gen.getMap().getTiles(new Vectors(i, j)) == Tiles.PLAYER) {
						mapPre[i][j] = 2;
					}
				}
			}
		}

		for (int i = -1; i <= mapPre.length; i++) {
			ArrayList<Integer> row = new ArrayList<Integer>();
			for (int j = -1; j <= mapPre[0].length; j++) {
				if (i == -1 || j == -1 || i == gen.getMap().getWidth() || j == gen.getMap().getHeight()) {
					row.add(1);
					continue;
				}
				row.add(mapPre[j][i]);
			}
			map.addRow(row);
		}
	}

	/**
	 * Gets fully generated map
	 * @return Generated map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * Populates generated map with player, boxes and goals
	 * @param map Map to populate
	 * @param boxes List to store box data
	 * @param goal List to store goal data
	 * @param player Stores player position
	 * @return Fully generated puzzle
	 */
	private boolean addItems(int[][] map, ArrayList<Vectors> boxes, ArrayList<Vectors> goal, Vectors player) {
		// should be change
		boxes = new ArrayList<Vectors>();
		goal = new ArrayList<Vectors>();
		player = new Vectors(1, 1);
		// set goals
		for (int i = 0; i < box; i++) {
			int max = map.length;
			int x1 = (int) (Math.random() * max);
			int y1 = (int) (Math.random() * max);
			Vectors pos = new Vectors(x1, y1);
			if (map[x1][y1] == 0) {
				map[x1][y1] = 7;
				boxes.add(pos);
				goal.add(pos);
			} else {
				i--;
			}
		}
		// set player
		boolean ps = false;
		while (!ps) {
			int max = map.length;
			int x1 = (int) (Math.random() * max);
			int y1 = (int) (Math.random() * max);
			if (map[x1][y1] == 0) {
				map[x1][y1] = 2;
				player.setX(x1);
				player.setY(y1);
				ps = true;
			}
		}

		return moveItems(map, boxes, goal, player);
	} 

	/**
	 * Move items in map to make the puzzle
	 * @param map Generated map
	 * @param boxes List of box data
	 * @param goal List of goal data
	 * @param player Player position
	 * @return True if puzzle successfully generated. False otherwise
	 */
	private boolean moveItems(int map[][], ArrayList<Vectors> boxes, ArrayList<Vectors> goal, Vectors player) {
		// Set maximum number of times items are moved
		int move = moveHolder;
		int test = 0;
		while (move != 0 && test < 20000) {
			// Choose box to move
			int max = boxes.size();
			int boxnum = (int) (Math.random() * max);
			// Choose direction
			max = 3;
			int d = (int) (Math.random() * max);
			//Box not yet moved
			boolean moved = false;
			// Test for valid box placement. 
			// moved set to true once valid placement is found
			if (d == 0 && boxes.get(boxnum).getX() > 1
					&& map[boxes.get(boxnum).getX() - 1][boxes.get(boxnum).getY()] != 1
					&& map[boxes.get(boxnum).getX() - 2][boxes.get(boxnum).getY()] != 1
					&& map[boxes.get(boxnum).getX() - 1][boxes.get(boxnum).getY()] != 7
					&& map[boxes.get(boxnum).getX() - 2][boxes.get(boxnum).getY()] != 7
					&& map[boxes.get(boxnum).getX() - 1][boxes.get(boxnum).getY()] != 3
					&& map[boxes.get(boxnum).getX() - 2][boxes.get(boxnum).getY()] != 3
					&& Asearch(player, new Vectors(boxes.get(boxnum).getX() - 1, boxes.get(boxnum).getY()), map)) {
				map[player.getX()][player.getY()] -= 2;
				player.setX(boxes.get(boxnum).getX() - 2);
				player.setY(boxes.get(boxnum).getY());
				map[player.getX()][player.getY()] += 2;
				map[boxes.get(boxnum).getX()][boxes.get(boxnum).getY()] -= 3;
				map[boxes.get(boxnum).getX() - 1][boxes.get(boxnum).getY()] += 3;
				boxes.get(boxnum).setX(boxes.get(boxnum).getX() - 1);
				moved = true;
			}
			if (d == 1 && boxes.get(boxnum).getX() < map.length - 2
					&& map[boxes.get(boxnum).getX() + 1][boxes.get(boxnum).getY()] != 1
					&& map[boxes.get(boxnum).getX() + 2][boxes.get(boxnum).getY()] != 1
					&& map[boxes.get(boxnum).getX() + 1][boxes.get(boxnum).getY()] != 7
					&& map[boxes.get(boxnum).getX() + 2][boxes.get(boxnum).getY()] != 7
					&& map[boxes.get(boxnum).getX() + 1][boxes.get(boxnum).getY()] != 3
					&& map[boxes.get(boxnum).getX() + 2][boxes.get(boxnum).getY()] != 3
					&& Asearch(player, new Vectors(boxes.get(boxnum).getX() + 1, boxes.get(boxnum).getY()), map)) {
				map[player.getX()][player.getY()] -= 2;
				player.setX(boxes.get(boxnum).getX() + 2);
				player.setY(boxes.get(boxnum).getY());
				map[player.getX()][player.getY()] += 2;
				map[boxes.get(boxnum).getX()][boxes.get(boxnum).getY()] -= 3;
				map[boxes.get(boxnum).getX() + 1][boxes.get(boxnum).getY()] += 3;
				boxes.get(boxnum).setX(boxes.get(boxnum).getX() + 1);
				moved = true;
			}
			if (d == 2 && boxes.get(boxnum).getY() > 1
					&& map[boxes.get(boxnum).getX()][boxes.get(boxnum).getY() - 1] != 1
					&& map[boxes.get(boxnum).getX()][boxes.get(boxnum).getY() - 2] != 1
					&& map[boxes.get(boxnum).getX()][boxes.get(boxnum).getY() - 1] != 7
					&& map[boxes.get(boxnum).getX()][boxes.get(boxnum).getY() - 2] != 7
					&& map[boxes.get(boxnum).getX()][boxes.get(boxnum).getY() - 1] != 3
					&& map[boxes.get(boxnum).getX()][boxes.get(boxnum).getY() - 2] != 3
					&& Asearch(player, new Vectors(boxes.get(boxnum).getX(), boxes.get(boxnum).getY() - 1), map)) {
				map[player.getX()][player.getY()] -= 2;
				player.setX(boxes.get(boxnum).getX());
				player.setY(boxes.get(boxnum).getY() - 2);
				map[player.getX()][player.getY()] += 2;
				map[boxes.get(boxnum).getX()][boxes.get(boxnum).getY()] -= 3;
				map[boxes.get(boxnum).getX()][boxes.get(boxnum).getY() - 1] += 3;
				boxes.get(boxnum).setY(boxes.get(boxnum).getY() - 1);
				moved = true;
			}
			if (d == 3 && boxes.get(boxnum).getY() > map[0].length - 2
					&& map[boxes.get(boxnum).getX()][boxes.get(boxnum).getY() + 1] != 1
					&& map[boxes.get(boxnum).getX()][boxes.get(boxnum).getY() + 2] != 1
					&& map[boxes.get(boxnum).getX()][boxes.get(boxnum).getY() + 1] != 7
					&& map[boxes.get(boxnum).getX()][boxes.get(boxnum).getY() + 2] != 7
					&& map[boxes.get(boxnum).getX()][boxes.get(boxnum).getY() + 1] != 3
					&& map[boxes.get(boxnum).getX()][boxes.get(boxnum).getY() + 2] != 3
					&& Asearch(player, new Vectors(boxes.get(boxnum).getX(), boxes.get(boxnum).getY() + 1), map)) {
				map[player.getX()][player.getY()] -= 2;
				player.setX(boxes.get(boxnum).getX());
				player.setY(boxes.get(boxnum).getY() + 2);
				map[player.getX()][player.getY()] += 2;
				map[boxes.get(boxnum).getX()][boxes.get(boxnum).getY()] -= 3;
				map[boxes.get(boxnum).getX()][boxes.get(boxnum).getY() + 1] += 3;
				boxes.get(boxnum).setY(boxes.get(boxnum).getY() + 1);
				moved = true;
			}
			test++;
			
			//Decrease move when moved is true
			if (moved) {
				move--;
			}
		}
		int find = 0;
		int num =0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == 2) {
					find++;
				}
				if (map[i][j] == 3) {
					num++;
				}
			}
		}
		if (find != 1 || test == 700||num<boxes.size()-1) {
			return false;
		}
		return true;

	}

	/**
	 * A* Search determines box placement relative to player position
	 * @param from Player position
	 * @param to Box position
	 * @param map Generated map
	 * @return true for valid box placement, false otherwise
	 */
	private boolean Asearch(Vectors from, Vectors to, int[][] map) {
		PriorityQueue<Vectors> open = new PriorityQueue<Vectors>(new VecotorsComparator());
		ArrayList<Vectors> close = new ArrayList<Vectors>();
		open.add(from);
		while (!open.isEmpty()) {
			Vectors cur = open.poll();
			if (cur.getX() != 0) {
				Vectors add = new Vectors(cur.getX() - 1, cur.getY());
				if (add.getX() == to.getX() && add.getY() == to.getY()) {
					return true;
				} else if ((map[cur.getX() - 1][cur.getY()] == 0 || map[cur.getX() - 1][cur.getY()] == 4)
						&& !open.contains(add) && !close.contains(add)) {
					boolean contains = false;
					for (int i = 0; i < close.size(); i++) {
						if (close.get(i).getX() == add.getX() && close.get(i).getY() == add.getY()) {
							contains = true;
						}
					}
					if (!contains) {
						add.addGs(1 + cur.getGs());
						add.addFx(add.getGs() + hx(add, to));
						;
						open.add(add);
					}
				}
			}
			if (cur.getX() != map[0].length - 1) {
				Vectors add = new Vectors(cur.getX() + 1, cur.getY());
				if (add.getX() == to.getX() && add.getY() == to.getY()) {
					return true;
				} else if ((map[cur.getX() + 1][cur.getY()] == 0 || map[cur.getX() + 1][cur.getY()] == 4)
						&& !open.contains(add) && !close.contains(add)) {
					boolean contains = false;
					for (int i = 0; i < close.size(); i++) {
						if (close.get(i).getX() == add.getX() && close.get(i).getY() == add.getY()) {
							contains = true;
						}
					}
					if (!contains) {
						add.addGs(1 + cur.getGs());
						add.addFx(add.getGs() + hx(add, to));
						open.add(add);
					}
				}
			}
			if (cur.getY() != 0) {
				Vectors add = new Vectors(cur.getX(), cur.getY() - 1);
				if (add.getX() == to.getX() && add.getY() == to.getY()) {
					return true;
				} else if ((map[cur.getX()][cur.getY() - 1] == 0 || map[cur.getX()][cur.getY() - 1] == 4)
						&& !open.contains(add) && !close.contains(add)) {
					boolean contains = false;
					for (int i = 0; i < close.size(); i++) {
						if (close.get(i).getX() == add.getX() && close.get(i).getY() == add.getY()) {
							contains = true;
						}
					}
					if (!contains) {
						add.addGs(cur.getGs() + 1);
						add.addFx(add.getGs() + hx(add, to));
						open.add(add);
					}
				}
			}
			if (cur.getY() != map.length - 1) {
				Vectors add = new Vectors(cur.getX(), cur.getY() + 1);
				if (add.getX() == to.getX() && add.getY() == to.getY()) {
					return true;
				} else if ((map[cur.getX()][cur.getY() + 1] == 0 || map[cur.getX()][cur.getY() + 1] == 4)
						&& !open.contains(add) && !close.contains(add)) {
					boolean contains = false;
					for (int i = 0; i < close.size(); i++) {
						if (close.get(i).getX() == add.getX() && close.get(i).getY() == add.getY()) {
							contains = true;
						}
					}
					if (!contains) {
						add.addGs(cur.getGs() + 1);
						add.addFx(add.getGs() + hx(add, to));
						;
						open.add(add);
					}
				}
			}
			close.add(cur);
		}
		return false;
	}
	
	/**
	 * Heuristic value for A* method
	 * @param from current position
	 * @param to final position
	 * @return distance between positions
	 */
	private double hx(Vectors from, Vectors to) {
		return Math.sqrt((from.getX() - to.getX()) * (from.getX() - to.getX())
				+ (from.getY() - to.getY()) * (from.getY() - to.getY()));
	}
}