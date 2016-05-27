
/**
 * Classe que implementa Vendas por filial. Contém um conjunto de clientes, 
 * para cada cliente a sua quantidade mensal e cada produto que o qual comprou
 * 
 */
public class VendasFilial {

    private CatalogMap<String, ClientSale> clientes;
	private CatalogMap<String, ProductSale> produtos;

	/**
     * Construtor padrão 
     */
    public VendasFilial() {
		clientes = new CatalogMap<String, ClientSale>();
		produtos = new CatalogMap<String, ProductSale>();
    }

	/**
	 * Construtor por parametros
	 */
	public VendasFilial(CatalogMap<String, ClientSale> clientes, 
			CatalogMap<String, ProductSale> produtos) {
		this.clientes = new CatalogMap<String, ClientSale>(clientes);
		this.produtos = new CatalogMap<String, ProductSale>(produtos);
	}

	/**
	 * Construtor por cópia
	 */
	public VendasFilial(VendasFilial v) {
		clientes = new CatalogMap<String, ClientSale> (v.getClientes());
		produtos = new CatalogMap<String, ProductSale>(v.getProdutos());	
	}

	/**
	 * Retorna um CatalogMap que mapeia para cada Cliente, a quantidade que
	 * comprou e, para cada produto, a quantidade comprada e o total gasto.
	 */
	public CatalogMap<String, ClientSale> getClientes() {
		return new CatalogMap<String, ClientSale>(clientes);
	}

	/**
	 * Retorna um CatalogMap que mapeia para cada Produto, a quantidade 
	 * total vendida e total faturado, e, para cada cliente, o tipo de
	 * promoção a que este o comprou.
	 */
	public CatalogMap<String, ProductSale> getProdutos() {
		return new CatalogMap<String, ProductSale>(produtos);
	}

	/**
	 * Adiciona uma venda a esta estrutura
	 * @param venda Venda a adicionar
	 */
	public void add(Venda v) {
		
		produtos.add(v);
		clientes.add(v);
	}
	
}
