import java.util.Arrays;
import java.io.Serializable;

/**
 * Descreve as transações sobre este produto por um dado cliente
 */
public class ProductUnit implements Serializable {
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
	 * Construtor por parâmetros
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
	 * @return quantidade total vendida
	 */
	public int getQuantidade() {
		return quantidade;
	}

	/**
	 * Retorna o total faturado
	 * @return total faturado
	 */
	public double getFaturado() {
		return faturado;
	}

	/**
	 * Adiciona uma venda ao ProductUnit
	 * @param v venda a adicionar
	 */
	public void add(Venda v) {
		faturado += v.getUnidades() * v.getPreco();
		quantidade += v.getUnidades();
	}

	/**
 	 * Adiciona os dados novas à informação atual do cliente
 	 * @param unidades unidades compradas
 	 * @param faturado montante gasto
 	 */
	public void add(int unidades, double faturado) {
		this.quantidade += unidades;
		this.faturado += faturado;
	}

	/**
 	 * Adiciona os dados de um outro ProductUnit
 	 * @param pu ProductUnit a ser adicionado
 	 */
	public void add(ProductUnit pu) {
		quantidade += pu.getQuantidade();
		faturado += pu.getFaturado();
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

		str.append("Quantidade: ").append(quantidade).append("\n");
		str.append("Faturado: ").append(faturado).append("\n");

		return str.toString();
	}

	/**
	 * Compara um dado objeto com esta instancia de ProductUnit.
	 * @param o Objeto a ser comparado com este ProductUnit
	 * @return true caso o objeto dado seja igual a este
	 */
	public boolean equals(Object o) {
		if (o == this) 
			return true;

		if ( o == null || o.getClass() != this.getClass() )
			 return false;

		ProductUnit pu = (ProductUnit) o;
		return pu.getQuantidade() == this.getQuantidade() &&
			   pu.getFaturado() == this.getFaturado();
	}

	/**
	 * Devolve um hash único para esta instancia de ProductUnit
	 * @return hash único para esta instancia
	 */
	public int hashCode() {
		return Arrays.hashCode( new Object[] {quantidade, faturado} );
	}
}
