
/**
 * Write a description of class Venda here.
 */
public class Venda
{
    private String produto;
    private String cliente;
    private double preco;
    private int unidades;
    private boolean promocao;
    private int mes;
    private int filial;

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

	public Venda(Venda v) {
		this.produto = v.getProduto();
		this.cliente = v.getCliente();
		this.preco = v.getPreco();
		this.unidades = v.getUnidades();
		this.promocao = v.getPromocao();
		this.mes = v.getMes();
		this.filial = v.getFilial();
	}

	public String getProduto() {
		return this.produto;
	}

	public String getCliente() {
		return this.cliente;
	}

	public double getPreco() {
		return this.preco;
	}

	public int getUnidades() {
		return this.unidades;
	}

	public boolean getPromocao() {
		return this.promocao;
	}

	public int getMes() {
		return this.mes;
	}

	public int getFilial() {
		return this.filial;
	}

	public Venda clone() {
		return new Venda(this);
	}

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
