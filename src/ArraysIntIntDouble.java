import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class ArraysIntIntDouble implements Serializable {
	public static final long serialVersionUID = 18L;

	private int[] fst, snd;
	private double[] trd;

	public ArraysIntIntDouble(int[] fst, int[] snd, double[] trd) {
		this.fst = fst.clone();
		this.snd = snd.clone();
		this.trd = trd.clone();
	}

	public ArraysIntIntDouble(ArraysIntIntDouble a) {
		fst = a.first();
		snd = a.second();
		trd = a.third();
	}

	public int[] first() {
		return fst.clone();
	}

	public int[] second() {
		return snd.clone();
	}

	public double[] third() {
		return trd.clone();
	}

	public List<String> toListString() {
		List<String> ret = new ArrayList<String>();

		for (int i = 0; i < fst.length; i++) {
			String str = new String((i+1) + fst[i] + "\t" + snd[i] + "\t" + trd[i]);
			ret.add(str);
		}

		return ret;
	}
}
