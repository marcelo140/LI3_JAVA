public class TriploStringIntInt {
	String str;
	int d1, d2;

	TriploStringIntInt(String str, int d1, int d2) {
		this.str = str;
		this.d1 = d1;
		this.d2 = d2;
	}

	public String first() {
		return str;
	}

	public int second() {
		return d1;
	}

	public int third() {
		return d2;
	}
}
