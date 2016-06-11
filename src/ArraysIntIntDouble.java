import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class ArraysIntIntDouble implements Serializable {
	private int[] fst, snd;
	private double[] trd;

	/**
 	 * Cria uma nova instância de ArraysIntIntDouble com os objetos dados.
 	 * @param fst primeiro array do triplo
 	 * @param snd segundo array do triplo
 	 * @param trd terceiro array do triplo
 	 */
	public ArraysIntIntDouble(int[] fst, int[] snd, double[] trd) {
		this.fst = fst.clone();
		this.snd = snd.clone();
		this.trd = trd.clone();
	}

	/**
 	 * Cria uma nova instância de ArraysIntIntDouble a partir do objeto dado
 	 * @param a ArraysIntIntDouble a ser copiada
 	 */
	public ArraysIntIntDouble(ArraysIntIntDouble a) {
		fst = a.first();
		snd = a.second();
		trd = a.third();
	}

	/**
 	 * Obtém uma cópia do primeiro objeto do triplo
 	 * @return primeiro array
 	 */
	public int[] first() {
		return fst.clone();
	}

	/**
 	 * Obtém uma cópia do segundo objeto do triplo
 	 * @return segundo array
 	 */
	public int[] second() {
		return snd.clone();
	}

	/**
 	 * Obtém uma cópia do terceiro objeto do triplo
 	 * @return terceiro array
 	 */
	public double[] third() {
		return trd.clone();
	}

	/**
 	 * Converte arrays numa lista de strings
 	 * @return lista de strings
 	 */
	public List<String> toListString() {
		List<String> ret = new ArrayList<String>();

		for (int i = 0; i < fst.length; i++) {
			String str = String.format("%2d\t%d\t%d\t%.2f", (i+1), fst[i], snd[i], trd[i]);
			ret.add(str);
		}

		return ret;
	}

	/**
 	 * Retorna um cópia deste triplo
 	 * @return cópia
 	 */
	public ArraysIntIntDouble clone(){
		return new ArraysIntIntDouble(this);
	}

	/**
 	 * Compara este par com o objeto dado. Returna true casa o objeto seja um ArrayIntIntDouble
 	 * que represente a mesma informação que este triplo.
 	 * @param o objeto com o qual a instância vai ser comparada
 	 * @return true se representarem a mesma informação
 	 */
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (o == null || o.getClass() != this.getClass())
			return false;

		ArraysIntIntDouble p = (ArraysIntIntDouble) o;
		return p.first().equals(fst) &&
			   p.second().equals(snd)&&
			   p.third().equals(trd);
	}

	/**
 	 * Retorna um string que descreve este triplo
 	 * @return String que descreve o triplo
 	 */
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("First :").append(fst).append("\n");
        sb.append("Second:").append(snd).append("\n");
        sb.append("Third ").append(trd).append("\n");

        return sb.toString();
    }

	/**
 	 * Retorna uma hash para este triplo
 	 * @return hash
 	 */
	public int hashCode() {
        int hash = 7;

        hash = 31*hash + fst.hashCode();
        hash = 31*hash + snd.hashCode();
        hash = 31*hash + trd.hashCode();


        return hash;
	}
}
