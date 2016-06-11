import java.util.Comparator;
import java.io.Serializable;

public class ComparatorTriploStringIntIntBySnd
			 implements Comparator<TriploStringIntInt>, Serializable {

	public static final long serialVersionUID = 10L;

	public int compare(TriploStringIntInt t1, TriploStringIntInt t2) {
		return t2.second() - t1.second();
	}

}
