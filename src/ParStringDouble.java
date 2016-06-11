import java.io.Serializable;
import java.util.Arrays;

public class ParStringDouble implements Serializable{

	private String str;
	private double d;

    /**
     * Cria uma nova instância de ParStringDouble com a String e o double dados.
     */
    public ParStringDouble(String str, double d) {
        this.str = str;
        this.d = d;
    }

    /**
     * Cria uma nova instância de ParStringDouble a partir do par dado
     * @param p ParStringDouble a ser copiado
     */
    public ParStringDouble(ParStringDouble p) {
        str = p.first();
        d = p.second();
    }

    /**
     * Obtém o primeiro objeto do par.
     * @return String
     */ 
    public String first() {
        return str;
    }

     /**
     * Obtém o segundo objeto do par
     * @return double
     */ 
    public double second() {
        return d;
    }

    /**
     * Retorna um cópia deste par
     * @return cópia
     */
    public ParStringDouble clone(){
        return new ParStringDouble(this);
    }

    /**
     * Compara este par com o objeto dado. Returna true casa o objeto seja um ParStringDouble
     * que represente a mesma informação que este par.
     * @return true se representarem a mesma informação
     */
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (o == null || o.getClass() != this.getClass())
            return false;

        ParStringDouble p = (ParStringDouble) o;
        return p.first().equals(str) &&
               p.second() == d;
    }

    /**
     * Retorna um string que descreve este par
     * @return String que descreve o par
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\t").append(str).append("\t").append(d);
        
        return sb.toString();
    }

	/**
	 * Devolve um hash único para esta instancia de ParStringDouble
	 * @return hash único para esta instancia
	 */
	public int hashCode() {
		return Arrays.hashCode( new Object[] {str,d} );
	}

}
    
