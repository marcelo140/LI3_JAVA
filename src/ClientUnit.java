import java.util.Arrays;
import java.io.Serializable;

/**
 * Descreve as transações de um cliente sobre um produto específico
 */

public class ClientUnit implements Serializable {
    private final int MESES = 12;

	private int quantidade;
	private double faturado;

    /**
     * Constrói uma nova instância de ClientUnit
     */
    public ClientUnit() {
		quantidade = 0;
		faturado   = 0;
    }

	/**
	 * Construtói uma nova instância de ClientUnit com os dados fornecidos
	 * @param quantidade quantidade do produto que foi comprada
	 * @param faturado montante gasto a comprar o produto
	 */
	public ClientUnit(int quantidade, double faturado) {
		this.quantidade = quantidade;
		this.faturado = faturado;
	}

	/**
	 * Cria uma nova instância de ClientUnit a partir de uma já existente
	 * @param clu ClientUnit a ser copiado
	 */
	public ClientUnit(ClientUnit clu) {
		quantidade = clu.getQuantidade();
		faturado = clu.getFaturado();
	}

	/**
	 * Retorna a quantidade total comprada pelo cliente
	 * @return Quantidade total comprada pelo cliente
	 */
	public int getQuantidade() {
		return quantidade;
	}

	/**
	 * Retorna o montante gasto pelo cliente neste produto
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
 	 * Adiciona os dados novos à informação atual do cliente
 	 * @param unidades unidades compradas
 	 * @param faturado montante gasto
 	 */
	public void add(int unidades, double faturado) {
		this.quantidade += unidades;
		this.faturado += faturado;
	}

	/**
 	 * Adiciona os dados de um outro ClientUnit
 	 * @param clu ClientUnit que será adicionado
 	 */
	public void add(ClientUnit clu) {
		quantidade += clu.getQuantidade();
		faturado += clu.getFaturado();
	}

	/**
	 * Retorna uma cópia desta instância de ClientUnit.
	 * @return cópia
	 */
	public ClientUnit clone() {
		return new ClientUnit(this);
	}

	/**
	 * Cria uma string de acordo com as variáveis deste objeto
	 * @return String que descreve esta instância de ClientUnit
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append("Quantidade:" + quantidade + "\n");
		str.append("Faturado:" + faturado + "\n");

		return str.toString();
	}

	/**
	 * Compara um dado objeto com esta instância de ClientUnit.
	 * @param o Objeto a ser comparado
	 * @return true caso o objeto dado seja igual a este
	 */
	public boolean equals(Object o) {
		if (o == this) 
			return true;

		if ( o == null || o.getClass() != this.getClass() ) 
			return false;

		ClientUnit pu = (ClientUnit) o;
		return pu.getQuantidade() == getQuantidade() &&
			   pu.getFaturado() == getFaturado();
	}

	/**
	 * Devolve um hash único para esta instância de ClientUnit
	 * @return hash único para esta instância
	 */
	public int hashCode() {
		return Arrays.hashCode(new Object[] { quantidade, faturado });
	}
}
