import java.util.Random;

public class TemplateFill {
	
	Random r;
	
	public TemplateFill(Random r){
		this.r = r;
	}
	
	public void fill(TemplateMap tm){
		for(int x = 0; x <(tm.getWidth()+2)/3; x++){
			for(int y = 0; y < (tm.getHeight()+2)/3; y++){
				Template t = Template.values()[r.nextInt(Template.values().length)];
				tm.put(x, y, t);
			}
		}
	}
}
