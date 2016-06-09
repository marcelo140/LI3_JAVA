import java.util.*;
/**
 * Cada produto terá a quantidade vendida e o total faturado.
 *
 */
public class ProductUnit {
	private int quantidade;
	private double faturado;

    /**
     * Construtor padrão
     */
    public ProductUnit() {
		quantidade = 0;
		faturado   = 0;
    }

	/**
	 * Construtor por parametros
	 */
	public ProductUnit(int quantidade, double faturado) {
		this.quantidade = quantidade;
		this.faturado = faturado;
	}

	/**
	 * Construtor por cópia
	 */
	public ProductUnit(ProductUnit p) {
		quantidade = p.getQuantidade();
		faturado = p.getFaturado();
	}

	/**
	 * Retorna a quantidade total vendida
	 * @return Quantidade total vendida
	 */
	public int getQuantidade() {
		return quantidade;
	}

	/**
	 * Retorna o total faturado
	 * @return Total faturado
	 */
	public double getFaturado() {
		return faturado;
	}

	/**
	 * Adiciona uma venda ao ProductUnit
	 * @param v Venda a adicionar
	 */
	public void add(Venda v) {

		faturado += v.getUnidades() * v.getPreco();
		quantidade += v.getUnidades();
	}

	/**
	 * Retorna uma copia desta instancia de ProductUnit.
	 * @return um clone desta instancia de ProductUnit
	 */
	public ProductUnit clone() {
		return new ProductUnit(this);
	}

	/**
	 * Cria uma string de acordo com as variáveis deste objeto
	 * @return String que representa esta instancia de ProductUnit
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append("ProductUnit:\n");
		str.append("Quantidade: ").append(quantidade).append("\n");
		str.append("Faturado: ").append(faturado).append("\n");

		return str.toString();
	}

	/**
	 * Compara um dado objeto com esta instancia de ProductUnit.
	 * @param o Objeto a ser comparado com este ProductUnit
	 * @return True caso o objeto dado seja igual a este,
	 * false caso contrário
	 */
	public boolean equals(Object o) {
		if (o == this) return true;
		if ( o == null || o.getClass() != this.getClass() ) return false;

		ProductUnit pu = (ProductUnit) o;
		return (pu.getQuantidade() == this.getQuantidade() &&
				pu.getFaturado() == this.getFaturado());
	}

	/**
	 * Devolve um hash único para esta instancia de ProductUnit
	 * @return hash único para esta instancia
	 */
	public int hashCode() {
		ArrayList<Object> lista = new ArrayList<>();

		lista.add(faturado);
		lista.add(quantidade);

		return lista.hashCode();
	}
}
