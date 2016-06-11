import java.util.Comparator;
import java.io.Serializable;

public class ComparatorTriploStringIntIntBySnd
			 implements Comparator<TriploStringIntInt>, Serializable {

	public int compare(TriploStringIntInt t1, TriploStringIntInt t2) {
		return t2.second() - t1.second();
	}

}
