package MapGeneration;
import java.util.Random;

public class Generator {
	
	int width, height;
	boolean bound;
	
	TemplateMap templateMap;
	TemplateFill filler;
	Random r;
	
	public Generator(Random r, int width, int height, boolean bound){
		this.r=r;
		this.width=width;
		this.height=height;
		this.bound = bound;
		
		filler = new TemplateFill(r);
	}
	
	public void generate(){
		templateMap = new TemplateMap(bound, width, height);
		filler.fill(templateMap);
	}
	
	public TemplateMap getMap(){
		if(this.templateMap == null){
			System.out.println("Empty map");
		}
		return this.templateMap;
	}
}
