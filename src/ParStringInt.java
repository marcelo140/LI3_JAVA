public class ParStringInt {
	private String str;
	private int integer;


	/**
 	 * Cria uma nova instância de ParStringInt com a String e o integer dados.
 	 */
	public ParStringInt(String str, int i) {
		this.str = str;
		this.integer = i;
	}

	/**
 	 * Cria uma nova instância de ParStringInt a partir do par dado
 	 * @param p ParStringInt a ser copiado
 	 */
	public ParStringInt(ParStringInt p) {
		str = p.firts();
		integer = p.second();
	}

	/**
 	 * Obtém o primeiro objeto do par
 	 * @return string
 	 */
	public String first() {
		return str;
	}

	/**
 	 * Obtém o segundo onjeto do par
 	 * @return integer
 	 */
	public int second() {
		return integer;
	}
	

	/**
 	 * Retorna um cópia deste par
 	 * @return cópia
 	 */
	public ParStringInt clone(){
		return new ParStringInt(this);
	}

	/**
 	 * Compara este par com o objeto dado. Returna true casa o objeto seja um ParStringInt
 	 * que represente a mesma informação que este par.
 	 * @return true se representarem a mesma informação
 	 */
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (o == null || o.getClass() != this.getClass())
			return false;

		ParStringInt p = (ParStringInt) o;
		return p.firts().equals(str) &&
			   p.second()== integer;
	}

	/**
 	 * Retorna um string que descreve este par
 	 * @return String que descreve o par
 	 */
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("String: ").append(str).append("\n");
        sb.append("Integer:").append(integer).append("\n");
        
        return sb.toString();
    }

	/**
 	 * Retorna uma hash para este par
 	 * @return hash
 	 */
	public int hashCode() {
        int hash = 7;

        hash = 31*hash + str.hashCode();
        hash = 31*hash + integer;
        

        return hash;
    }
}
