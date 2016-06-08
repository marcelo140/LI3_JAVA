public class Marked {
	private boolean mark;

	public Marked(boolean mark) {
		this.mark = mark;
	}

	public void negate() {
		mark = !mark;
	}

	public boolean isMarked() {
		return mark == true;
	}

	public String toString() {
		if (mark)
			return "true";

		return "false";
	}

	public Marked clone() {
		return new Marked(mark);
	}

	public int hashCode() {
		return (mark ? 1 : 0);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null || this.getClass() != obj.getClass())
			return false;

		Marked m = (Marked) obj;
		return m.mark = mark;
	}
}
