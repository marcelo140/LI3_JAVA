/**
 * Write a description of class ParStringDouble here.
 */

import java.util.Comparator;

public class ParStringDouble
{
	private String s;
	private double d;

	/**
 	 * Construtor por argumentos
 	 */
	public ParStringDouble(String s, double d) {
		this.s = s;
		this.d = d;
	}

	/**
 	 * Construtor por cópia
 	 */
	public ParStringDouble(ParStringDouble par) {
		s = par.getString();
		d = par.getDouble();
	}

	/**
 	 * Obter string
 	 * @return String
 	 */
	public String getString() {
		return s;
	}

	/**
 	 * Obter double
 	 * @return double
 	 */
	public double getDouble() {
		return d;
	}

	/**
 	 * Define string
 	 * @param s
 	 */
	public void setString(String s) {
		this.s = s;
	}

	/**
 	 * Define double
 	 * @param d
 	 */
	public void setDouble(double d) {
		this.d = d;
	}

	/**
 	 * Verifica se os dois objetos são iguais
 	 * @return
 	 */
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (o == null | o.getClass() != this.getClass())
			return false;

		ParStringDouble obj = (ParStringDouble) o;
		return obj.getDouble() == d && obj.getString().equals(s);
	}
	
	/**
 	 * Transforma um par numa string
 	 * @return String
 	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("String: '").append(s).append("'\n");
		sb.append("Double: ").append(d).append("\n");
	
		return sb.toString();
	}

	/**
 	 * Cria uma cópia do par
 	 * @return ParStringDouble
 	 */
	public ParStringDouble clone() {
		return new ParStringDouble(this);
	}

	public int compareByDouble(ParStringDouble par1, ParStringDouble par2) {
		return Double.compare(par1.getDouble(), par2.getDouble());
	}
}
