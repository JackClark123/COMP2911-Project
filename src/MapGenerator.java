import java.util.*;

public class MapGenerator {
	public static final int Empty = 0;
	public static final int People = 2;
	public static final int Box = 3;
	public static final int Block = 1;
	public static final int Target = 4;
	
	public static final int DMAX = 4;
	public static final int H = 7;
	public static final int L = 15;
	
	
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, 1, 0, -1};
	
//	private enum info {DMAX = 4, H = 7, L = 15};
	private int[][] Map;
	private int px, py; //position of people
	private int bx, by; //position of box
	private boolean succeed, prove;
	private int dir;
	
	private Node curr;
	
	public static void main(String args[]){
		MapGenerator m = new MapGenerator();
		while(!m.prove){
			m.resetMap();
			m.createMap();
		}
		m.printMap();
	}
	
	public MapGenerator(){
		Map = new int[H][L];
	}
	
	public void createMap(){
		dir = -1;
		succeed = prove = false;
		int seed = (int) new Date().getTime();
		Random rnd = new Random();
		 int count = 0;
		 int x = 0;
		 int y = 0;
		 
		 for(int i = 0; i < H; ++i){
			 for(int k = 0; k < L; ++k){
				 Map[i][k] = 0;
			 }
		 }
		//generate 25 blocks
		 while(count != 25){
			 x = rnd.nextInt(seed)%H;
			 y = rnd.nextInt(seed)%L;
			 if(Map[x][y] == 0){
				 Map[x][y] = Block;
				 count++;
			 }
		 }
		 //random generate start position of people
		 while(true){
			 x = rnd.nextInt(seed)%H;
			 y = rnd.nextInt(seed)%L;
			 if(Map[x][y] == 0){
				 Map[x][y] = People;
				 px = x;
				 py = y;
				 break;
			 }
		 }
		 //random generate box position
		 while(true){
			 x = rnd.nextInt(seed)%H;
			 y = rnd.nextInt(seed)%L;
			 if(Map[x][y] == 0 && x!=0 && y!=0 && x != H-1 && y != L-1){
				 Map[x][y] = Box;
				 bx = x;
				 by = y;
				 break;
			 }
		 }
		 //random generate target position
		 while(true){
			 x = rnd.nextInt(seed)%H;
			 y = rnd.nextInt(seed)%L;
			 if(Map[x][y] == 0){
				 Map[x][y] = Target;
				 break;
			 }
		 }
		 box_bfs(bx, by, px, py);
//		 if(!prove){
//			 resetMap();
//			 createMap();
//		 }else{
//			 printMap();
//		 }
		 printMap();
	}
	
	private void box_bfs(int bx, int by, int px, int py){
		boolean[][][][] visit = new boolean[H][L][H][L];
		Queue<Node> _box = new LinkedList<Node>();
		curr = new Node(bx, by, px, py);
		Node e = new Node(bx, by, px, py);
		_box.add(curr);
		int pe_x =0, pe_y =0;
		while(!_box.isEmpty()){
			curr = _box.poll();
			if(Map[curr.bx][curr.by] == Target){
				prove = true;
				break;
			}
			for(int i = 0; i < DMAX; ++i){
				e.bx = curr.bx + dx[i];
				e.by = curr.by +dx[i];
				//the position of people pushing box
				switch(i){
					case 0: pe_x = curr.bx + dx[2]; pe_y = curr.by + dy[2]; break;
					case 1: pe_x = curr.bx + dx[3]; pe_y = curr.by + dy[3]; break;
					case 2: pe_x = curr.bx + dx[0]; pe_y = curr.by + dy[0]; break;
					case 3: pe_x = curr.bx + dx[1]; pe_y = curr.by + dy[1]; break;
				}
				if(!check(e.bx, e.by) 
					|| !check(pe_x, pe_y) 
					|| Map[e.bx][e.by] == Block
					|| Map[pe_x][pe_y] == Block
					|| visit[curr.bx][curr.by][e.bx][e.by]){
						continue;
					}
				if(people_bfs(pe_x, pe_y)){
					e.px = pe_x;
					e.py = pe_y;
					_box.add(e);
					visit[curr.bx][curr.by][e.bx][e.by] = true;
				}
			}
		}
	}//end of box_bfs
	
	boolean people_bfs(int ex, int ey){
		boolean[][] visit = new boolean[H][L];
		Node t, end;
		Queue<Node> _people = new LinkedList<Node>();
		t = new Node(0,0,0,0);
		end = new Node(0,0,0,0);
		t.px = curr.px;
		t.py = curr.py;
		_people.add(t);
		visit[t.px][t.py] = true;
		while(!_people.isEmpty()){
			t = _people.poll();
			if(t.px == ex && t.py == ey){
				return true;
			}
			for(int i = 0; i < DMAX; ++i){
				end.px = t.px + dx[i];
				end.py = t.py + dy[i];
				if(!check(end.px, end.py) 
					|| Map[end.px][end.py] == Block
					|| Map[end.px][end.py] == Box
					||visit[end.px][end.py]){
					continue;
				}
				_people.add(end);
				visit[end.px][end.py] = true;
			}
		}
		return false;
	}
	
	private boolean check(int x, int y){
		if(x < 0 || x >= H || y < 0 || y >= L){
			return false;
		}else{
			return true;
		}
	}
	
	private void resetMap(){
		for(int i = 0; i < H; ++i){
			for(int k =0; k < L; ++ k){
				Map[i][k] = -1;
			}
		}
	}
	
	private void printMap(){
		for(int i = 0; i < H; ++ i){
			for(int k = 0; k < L; ++k){
				System.out.print(Map[i][k] + " ");
			}
			System.out.println();
		}
		System.out.println(prove);
	}
}
