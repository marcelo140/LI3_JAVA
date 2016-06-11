import java.util.Comparator;
import java.io.Serializable;

public class ComparatorParStringIntByInt implements Comparator<ParStringInt>, Serializable {
	public static final long serialVersionUID = 12L;

	public int compare(ParStringInt p1, ParStringInt p2) {
		int res = p2.second() - p1.second();

		if (res == 0)
			res = p2.first().compareTo(p1.first());

		return res;
	}
}
