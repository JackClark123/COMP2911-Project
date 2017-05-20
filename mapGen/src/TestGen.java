import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class TestGen {

	@Test
	public void test() {
		
		boolean bounded = true;
		
		Random r = new Random();
		
		TemplateMap tm = new TemplateMap(bounded, 6,6);
		
		TemplateFill filler = new TemplateFill(r);
		
		filler.fill(tm);
		
		Vectors v1 = new Vectors(0,0);
		Vectors v2 = new Vectors(1,2);
		
		Template t1 = tm.getTemplate(v1);
		Template t2 = tm.getTemplate(v2);
	}

}
