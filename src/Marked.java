import java.io.Serializable;

/**
 * Wrapper mutável para booleans
 */

public class Marked implements Serializable {
	private boolean mark;

	/**
 	 * Constrói marca a partir do valor dado
 	 * @param mark valor inicial
 	 */
	public Marked(boolean mark) {
		this.mark = mark;
	}

	/**
 	 * Inverte o valor atual
 	 */
	public void negate() {
		mark = !mark;
	}

	/**
 	 * Verifica se o valor guardado é true
 	 * @return true se estiver marcado
 	 */
	public boolean isMarked() {
		return mark == true;
	}

	/**
 	 * Cria uma String que descreve a marca
 	 * @return string
 	 */
	public String toString() {
		if (mark)
			return "true";

		return "false";
	}

	/**
 	 * Cria uma cópia da marca
 	 * @return cópia
 	 */
	public Marked clone() {
		return new Marked(mark);
	}

	/**
 	 * Determina uma hash para este objeto
 	 * @return hash
 	 */
	public int hashCode() {
		return (mark ? 1 : 0);
	}

	/**
 	 * Compara esta instância com o objeto dado
 	 * @return true se obj for uma Mark que representa a mesma informação que instância com
 	 * que está a ser comparada
 	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null || this.getClass() != obj.getClass())
			return false;

		Marked m = (Marked) obj;
		return m.mark = mark;
	}
}
