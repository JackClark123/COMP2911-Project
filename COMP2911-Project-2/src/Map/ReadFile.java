package Map;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ReadFile {
	
	private Scanner sc;
	
	private Map map;

	public ReadFile(String filename) {
		
		map = new Map(10, 10);
		
		sc = null;
		try {
			sc = new Scanner(new FileReader(filename));
			int linenumber = 0;
			while (sc.hasNext()) {
				String str = sc.next(); 
                char[] line = str.toCharArray();
                lineHandler(line, linenumber);
                linenumber++;
			}

		} catch (FileNotFoundException e) {
		} finally {
			if (sc != null)
				sc.close(); //closes scanner
		}
	}
	
	private void lineHandler(char[] line, int linenumber) {
		for (int i = 0; i < line.length; i++) {
			map.setMapValue(i, linenumber, Character.getNumericValue(line[i]));
		}
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
	
}
