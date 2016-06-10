import java.util.Comparator;
import java.io.Serializable;

public class ComparatorParStringDoubleByDouble implements Comparator<ParStringDouble>, Serializable {
	public int compare(ParStringDouble p1, ParStringDouble p2) {
		return Double.compare(p1.getDouble(), p2.getDouble());	
	}
}
