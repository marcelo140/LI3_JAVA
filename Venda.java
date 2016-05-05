
/**
 * Venda é a class que descreve os elementos envolvidos numa transação
 */
public class Venda implements Serializable
{
    private final String produto;
    private final String cliente;
    private final double preco;
    private final int unidades;
    private final boolean promocao;
    private final int mes;
    private final int filial;

	/**
 	 * Construtor por argumentos
 	 */
	public Venda(String produto, double preco, int unidades, boolean promocao,
                 String cliente, int mes, int filial) 
	{
		this.produto = produto;
		this.cliente = cliente;
		this.preco = preco;
		this.unidades = unidades;
		this.promocao = promocao;
		this.mes = mes;
		this.filial = filial;
	}

	/**
 	 * Construtor por cópia
 	 */
	public Venda(Venda v) {
		this.produto = v.getProduto();
		this.cliente = v.getCliente();
		this.preco = v.getPreco();
		this.unidades = v.getUnidades();
		this.promocao = v.getPromocao();
		this.mes = v.getMes();
		this.filial = v.getFilial();
	}

	/**
 	 * Obter produto
 	 * @return Produto
 	 */
	public String getProduto() {
		return this.produto;
	}

	/**
 	 * Obter cliente
 	 * @return Cliente
 	 */
	public String getCliente() {
		return this.cliente;
	}

	/**
 	 * Obter preço
 	 * @return Preço
 	 */
	public double getPreco() {
		return this.preco;
	}

	/**
 	 * Obter unidades
 	 * @return Unidades
 	 */
	public int getUnidades() {
		return this.unidades;
	}

	/**
 	 * Verifica se a venda foi efetuada em promoção
 	 * @return 
 	 */
	public boolean getPromocao() {
		return this.promocao;
	}

	/**
 	 * Obter mês
 	 * @return Mês
 	 */
	public int getMes() {
		return this.mes;
	}

	/**
 	 * Obter filial
 	 * @return Filial
 	 */
	public int getFilial() {
		return this.filial;
	}

	/**
 	 * Cria uma cópia da venda
 	 * @return Venda
 	 */
	public Venda clone() {
		return new Venda(this);
	}

	/**
 	 * Compara a venda com o objeto dado
 	 * @return 
 	 */
	public boolean equals(Object o) {
		if (o == this)
			return true;
		
		if (o == null || o.getClass() != this.getClass())
			return false;

		Venda v = (Venda) o;
		return v.getProduto().equals(produto) &&
               v.getCliente().equals(cliente) &&
			   v.getPreco() == preco &&
               v.getUnidades() == unidades &&
               v.getPromocao() == promocao &&
			   v.getMes() == mes &&
               v.getFilial() == filial;
	}

	/**
 	 * Converte uma instância venda numa String
 	 * @return String
 	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Produto: ").append(produto).append("\n");
		sb.append("Preço: ").append(preco).append("\n");
		sb.append("Unidades: ").append(unidades).append("\n");
		sb.append("Promoção: ").append(promocao).append("\n");
		sb.append("Cliente: ").append(cliente).append("\n");
		sb.append("Mês: ").append(mes).append("\n");
		sb.append("Filial: ").append(filial).append("\n");
		
		return sb.toString();
	}
}
