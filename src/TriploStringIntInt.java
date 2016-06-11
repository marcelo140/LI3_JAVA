import java.io.Serializable;
public class TriploStringIntInt implements Serializable{


	private String str;
	private int d1, d2;


	/**
 	 * Cria uma nova instância de TriploStringIntInt com os objetos dados. 
 	 */
	public TriploStringIntInt(String str, int d1, int d2) {
		this.str = str;
		this.d1 = d1;
		this.d2 = d2;
	}

	/**
 	 * Cria uma nova instância de TriploStringIntInt a partir do objeto dado.
 	 * @param t TriploStringIntInt a ser copiada
 	 */
	public TriploStringIntInt(TriploStringIntInt t) {
		str = t.first();
		d1 = t.second();
		d2 = t.third();
	}

	
	/**
 	 * Obtém o primeiro objeto do triplo.
 	 * @return String
 	 */	
	public String first() {
		return str;
	}

	/**
 	 * Obtém o segundo objeto do triplo.
 	 * @return int
 	 */	
	public int second() {
		return d1;
	}

	/**
 	 * Obtém o terceiro objeto do triplo.
 	 * @return int
 	 */	
	public int third() {
		return d2;
	}
	/**
 	 * Retorna um cópia deste triplo
 	 * @return cópia
 	 */
	public TriploStringIntInt clone(){
		return new TriploStringIntInt(this);
	}

	/**
 	 * Compara este par com o objeto dado. Returna true casa o objeto seja um TriploStringIntInt
 	 * que represente a mesma informação que este triplo.
 	 * @return true se representarem a mesma informação
 	 */
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (o == null || o.getClass() != this.getClass())
			return false;

		TriploStringIntInt p = (TriploStringIntInt) o;
		return p.first().equals(str) &&
			   p.second()== d1 &&
			   p.third() == d2;
	}

	/**
 	 * Retorna um string que descreve este triplo
 	 * @return String que descreve o triplo
 	 */
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("First ").append(str).append("\n");
        sb.append("Second").append(d1).append("\n");
        sb.append("Third").append(d2).append("\n");
        
        return sb.toString();
    }

	/**
 	 * Retorna uma hash para este triplo
 	 * @return hash
 	 */
	public int hashCode() {
        int hash = 7;

        hash = 31*hash + str.hashCode();
        hash = 31*hash + d1;
        hash = 31*hash + d2;
        

        return hash;
    }

}
