import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class ArraysIntIntDouble implements Serializable {
	private int[] fst, snd;
	private double[] trd;

	
	/**
 	 * Cria uma nova instância de ArraysIntIntDouble com os objetos dados.
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
 	 */
	public int[] first() {
		return fst.clone();
	}

	/**
 	 * Obtém uma cópia do segundo objeto do triplo
 	 */
	public int[] second() {
		return snd.clone();
	}

	/**
 	 * Obtém uma cópia do terceiro objeto do triplo
 	 */
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
 	 * @return true se representarem a mesma informação
 	 */
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (o == null || o.getClass() != this.getClass())
			return false;

		ArraysIntIntDouble p = (ArraysIntIntDouble) o;
		return p.firts().equals(str) &&
			   p.second().equals(d);
	}

	/**
 	 * Retorna um string que descreve este triplo
 	 * @return String que descreve o triplo
 	 */
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Int: ").append(fst).append("\n");
        sb.append("Int:").append(snd).append("\n");
        sb.append("Double ").append(trd).append("\n");
       
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
