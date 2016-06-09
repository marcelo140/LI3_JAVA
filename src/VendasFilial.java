
/**
 * Classe que implementa Vendas por filial. Contém um conjunto de clientes, 
 * para cada cliente a sua quantidade mensal e cada produto que o qual comprou
 * 
 */
public class VendasFilial {

    private CatalogMap<String, Client> clientes;
	private CatalogMap<String, Product> produtos;

	/**
     * Construtor padrão 
     */
    public VendasFilial() {
		clientes = new CatalogMap<String, Client>(12);
		produtos = new CatalogMap<String, Product>(12);
    }

	/**
	 * Construtor por parametros
	 */
	public VendasFilial(CatalogMap<String, Client> clientes, 
			CatalogMap<String, Product> produtos) {
		this.clientes = clientes;
		this.produtos = produtos;
	}

	/**
	 * Construtor por cópia
	 * NÃO FUNCIONA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 */
	public VendasFilial(VendasFilial v) {
		clientes = v.getClientes();
		produtos = v.getProdutos();	
	}

	/**
	 * Retorna um CatalogMap que mapeia para cada Cliente, a quantidade que
	 * comprou e, para cada produto, a quantidade comprada e o total gasto.
	 */
	private CatalogMap<String, Client> getClientes() {
		return clientes;
	}

	/**
	 * Retorna um CatalogMap que mapeia para cada Produto, a quantidade 
	 * total vendida e total faturado, e, para cada cliente, o tipo de
	 * promoção a que este o comprou.
	 */
	private CatalogMap<String, Product> getProdutos() {
		return produtos;
	}

	/**
	 * Adiciona uma venda a esta estrutura
	 * @param venda Venda a adicionar
	 */
	public void add(Venda v) {
		int mes = v.getMes();
	    String produto = v.getProduto();
	    String cliente = v.getCliente();
	    /*
		produtos.get(mes, produto).add(v);
		clientes.get(mes, cliente).add(v); */
	}
	
}
