package Map;
import java.util.*;

import MapGeneration.Vectors;

public class VecotorsComparator implements Comparator<Vectors>{

	@Override
	public int compare(Vectors S1, Vectors s2) {
		return (int)(S1.getFx() - s2.getFx());
	}
	
}
