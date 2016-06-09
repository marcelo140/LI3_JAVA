import java.util.ArrayList;
/**
 * Cada cliente terá a quantidade vendida e o total gasto.
 *
 */
public class ClientUnit implements Cloneable {
    private final int MESES = 12;

	private int quantidade;
	private double faturado;

    /**
     * Construtor padrão
     */
    public ClientUnit() {
		quantidade = 0;
		faturado   = 0;
    }

	/**
	 * Construtor por parametros
	 */
	public ClientUnit(int quantidade, double faturado) {
		this.quantidade = quantidade;
		this.faturado = faturado;
	}

	/**
	 * Construtor por cópia
	 */
	public ClientUnit(ClientUnit p) {
		quantidade = p.getQuantidade();
		faturado = p.getFaturado();
	}

	/**
	 * Retorna a quantidade total comprada pelo cliente
	 * @return Quantidade total comprada pelo cliente
	 */
	public int getQuantidade() {
		return quantidade;
	}

	/**
	 * Retorna o total gasto pelo cliente
	 * @return Total gasto pelo cliente
	 */
	public double getFaturado() {
		return faturado;
	}

	/**
	 * Adiciona uma venda ao ClientUnit
	 * @param v Venda a adicionar
	 */
	public void add(Venda v) {

		faturado += v.getUnidades() * v.getPreco();
		quantidade += v.getUnidades();
	}

	/**
	 * Retorna uma copia desta instancia de ClientUnit.
	 * @return um clone desta instancia de ClientUnit
	 */
	public ClientUnit clone() {
		return new ClientUnit(this);
	}

	/**
	 * Cria uma string de acordo com as variáveis deste objeto
	 * @return String que representa esta instancia de ClientUnit
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append("ClientUnit:\n");
		str.append("Quantidade:" + quantidade + "\n");
		str.append("Faturado:" + faturado + "\n");

		return str.toString();
	}

	/**
	 * Compara um dado objeto com esta instancia de ClientUnit.
	 * @param o Objeto a ser comparado com este ClientUnit
	 * @return True caso o objeto dado seja igual a este,
	 * false caso contrário
	 */
	public boolean equals(Object o) {
		if (o == this) return true;
		if ( o == null || o.getClass() != this.getClass() ) return false;

		ClientUnit pu = (ClientUnit) o;
		return (pu.getQuantidade() == this.getQuantidade() &&
				pu.getFaturado() == this.getFaturado());
	}

	/**
	 * Devolve um hash único para esta instancia de ClientUnit
	 * @return hash único para esta instancia
	 */
	public int hashCode() {
		ArrayList<Object> lista = new ArrayList<>();

		lista.add(quantidade);
		lista.add(faturado);

		return lista.hashCode();
	}
}
