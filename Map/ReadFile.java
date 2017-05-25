package Map;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
	
	private Scanner sc;
	
	private Map map;
	private MultiplayerMap multiplayerMap;

	public ReadFile(String filename) {
		
		map = new Map();
		multiplayerMap = new MultiplayerMap(0);
		
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
		ArrayList<Integer> row = new ArrayList<Integer>();
		for (int i = 0; i < line.length; i++) {
			row.add(Character.getNumericValue(line[i]));
		}
		multiplayerMap.addRow(row);
		map.addRow(row);
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
	
	public MultiplayerMap getMultiplayerMap() {
		return multiplayerMap;
	}
	
}
