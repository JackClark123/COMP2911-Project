package MapGeneration;
import java.util.Random;

public class Generator {
	
	int width, height;
	boolean bound;
	
	TemplateMap templateMap;
	TemplateFill filler;
	Random r;
	
	/**
	 * Generator constructor
	 * @param r random seed
	 * @param width Map width
	 * @param height Map height
	 */
	public Generator(Random r, int width, int height){
		this.r=r;
		this.width=width;
		this.height=height;
		
		filler = new TemplateFill(r);
	}
	
	/**
	 * Randomly fills map with given templates
	 */
	public void generate(){
		templateMap = new TemplateMap(width, height);
		filler.fill(templateMap);
	}
	
	/**
	 * Gets the generated map
	 * @return Generated map
	 */
	public TemplateMap getMap(){
		if(this.templateMap == null){
			System.out.println("Empty map");
		}
		return this.templateMap;
	}
}
