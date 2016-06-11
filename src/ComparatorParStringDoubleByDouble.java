import java.util.Comparator;
import java.io.Serializable;

public class ComparatorParStringDoubleByDouble implements Comparator<ParStringDouble>, Serializable {
	public int compare(ParStringDouble p1, ParStringDouble p2) {
		int res = Double.compare(p2.second(), p1.second());

		if (res == 0)
			res = p2.first().compareTo(p1.first());

		return res;
	}
}
