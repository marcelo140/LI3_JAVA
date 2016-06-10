import java.io.Serializable;

public class ArraysIntIntDouble implements Serializable {
	private int[] fst, snd;
	private double[] trd;

	public ArraysIntIntDouble(int[] fst, int[] snd, double[] trd) {
		this.fst = fst.clone();
		this.snd = snd.clone();
		this.trd = trd.clone();
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
}
