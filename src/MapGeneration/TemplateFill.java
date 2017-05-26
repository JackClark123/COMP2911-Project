package MapGeneration;
import java.util.Random;

public class TemplateFill {
	
	Random r;
	
	/**
	 * Class constructor 
	 * @param r Random seed
	 */
	public TemplateFill(Random r){
		this.r = r;
	}
	
	/**
	 * Fills map with Templates
	 * @param tm Map to fill
	 */
	public void fill(TemplateMap tm){
		for(int x = 0; x <(tm.getWidth()+2)/3; x++){
			for(int y = 0; y < (tm.getHeight()+2)/3; y++){
				Template t = Template.A;
				tm.put(x, y, t);
			}
		}
		for(int x = 0; x <(tm.getWidth()+2)/3; x++){
			for(int y = 0; y < (tm.getHeight()+2)/3; y++){
				int num =0;
				Template top =null;
				Template buttom =null;
				Template left = null;
				Template right = null;
				if(x!=0){
					left = tm.getTemplate(new Vectors(x-1,y));
				}
				if(x!= (tm.getWidth()+2)/3){
					right = tm.getTemplate(new Vectors(x+1,y));
				}
				if(y!=0){
					top = tm.getTemplate(new Vectors(x,y-1));
				}
				if(x!=(tm.getHeight()+2)/3){
					buttom = tm.getTemplate(new Vectors(x,y+1));
				}
				
				if((tm.getHeight()+2)/3==1 ||(tm.getWidth()+2)/3 ==1){
					num=2;
					
				}
				Template t = Template.values()[r.nextInt(Template.values().length)];				
				if( buttom!= null && isConnected(t,buttom,"buttom")){
					num++;
				}
				if( top!= null && isConnected(t,top,"top")){
					num++;
				}
				if( left!= null && isConnected(t,left,"left")){
					num++;
				}
				if( right!= null && isConnected(t,right,"right")){
					num++;
				}
				if(num<2){
					y--;
					continue;
				}
				tm.put(x, y, t);
			}
			
		}
	}
	
	/**
	 * Checks whether two templates are connected to each other.
	 * Templates are considered connected if floor exists between templates
	 * @param a First template
	 * @param b Template to compare
	 * @param name Position to compare
	 * @return True if templates are connected. False otherwise
	 */
	private boolean isConnected(Template a,Template b,String name){
		for(int i = 0;i<3;i++){
			for(int j = 0;j<3;j++){
				if(i==0&&a.tiles[i][j]==Tiles.FLOOR&&name.equals("left")){
					if(b.tiles[2][j]==Tiles.FLOOR) return true;
				}
				if(i==2&&a.tiles[i][j]==Tiles.FLOOR&&name.equals("right")){
					if(b.tiles[0][j]==Tiles.FLOOR) return true;
				}
				if(j==0&&a.tiles[i][j]==Tiles.FLOOR&&name.equals("top")){
					if(b.tiles[i][2]==Tiles.FLOOR) return true;
				}
				if(j==2&&a.tiles[i][j]==Tiles.FLOOR&&name.equals("buttom")){
					if(b.tiles[i][0]==Tiles.FLOOR) return true;
				}
			}
		}
		return false;
	}
}
