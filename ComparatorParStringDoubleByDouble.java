/**
 * Comparator de ParStringDouble - ordeção por double
 */

import java.util.Comparator;

public class ComparatorParStringDoubleByDouble implements Comparator<ParStringDouble> {
	public int compare(ParStringDouble par1, ParStringDouble par2) {
		return Double.compare(par1.getDouble(), par2.getDouble());
	}
}
