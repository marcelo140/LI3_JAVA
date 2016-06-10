import java.util.Comparator;
import java.io.Serializable;

public class ComparatorParStringIntByInt implements Comparator<ParStringInt>, Serializable {
	public int compare(ParStringInt p1, ParStringInt p2) {
		int res = p1.getInt() - p2.getInt();

		if (res == 0)
			res = p1.getString().compareTo(p2.getString());

		return res;
	}
}
