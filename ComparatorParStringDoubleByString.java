/**
 * Comparator de ParStringDouble - ordenação por String
 */

import java.util.Comparator;

public class ComparatorParStringDoubleByString implements Comparator<ParStringDouble> {
	public int compare(ParStringDouble par1, ParStringDouble par2) {
		return par1.getString().compareTo(par2.getString());
	}	
}
