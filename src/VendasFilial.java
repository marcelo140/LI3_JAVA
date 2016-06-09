
/**
 * Classe que implementa Vendas por filial. Contém um conjunto de clientes,
 * para cada cliente a sua quantidade mensal e cada produto que o qual comprou
 *
 */
public class VendasFilial {

    private final int MESES = 12;
    private int numClientes;
    private int[] numClientesMes;
    private CatalogMap<String, Client> clientes;
	private CatalogMap<String, Product> produtos;

	/**
     * Construtor padrão
     */
    public VendasFilial() {
        numClientes = 0;
        numClientesMes = new int[MESES];
		clientes = new CatalogMap<String, Client>(MESES);
		produtos = new CatalogMap<String, Product>(MESES);
    }

	/**
	 * Construtor por parametros
	 */
	public VendasFilial(int numClientes, int[] numClientesMes,
                        CatalogMap<String, Client> clientes,
                        CatalogMap<String, Product> produtos) {

	}

	/**
	 * Construtor por cópia
	 * NÃO FUNCIONA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 */
	public VendasFilial(VendasFilial v) {

        numClientes = v.getNumClientes();
        numClientesMes = v.getNumClientesMes();
		clientes = v.getClientes();
		produtos = v.getProdutos();
	}

    /**
     * Retorna o número de clientes que compraram nesta filial.
     * @return o número de clientes que compraram nesta filial
     */
    public int getNumClientes() {
         return numClientes;
    }

    /**
      * Retorna um array com o número de clientes que compraram em cada mês.
      * @return um array com o número de clientes que compraram em cada mês
      */
    public int[] getNumClientesMes() {
        return numClientesMes;
    }

    /**
     * Retorna o número de clientes que compraram no mês dado.
     * @param mes mês
     * @return o número de clientes que compraram no mês dado
     */
    public int getNumClientesMes(int mes) throws InvalidMonthException {
        if (mes < 0 || mes > 11)
            throw new InvalidMonthException("Mês inválido.");

       return numClientesMes[mes];
   }

    /**
	 * Retorna um CatalogMap que mapeia para cada Cliente, a quantidade que
	 * comprou e, para cada produto, a quantidade comprada e o total gasto.
	 */
	private CatalogMap<String, Client> getClientes() {
		return new CatalogMap<>(clientes);
	}

	/**
	 * Retorna um CatalogMap que mapeia para cada Produto, a quantidade
	 * total vendida e total faturado, e, para cada cliente, o tipo de
	 * promoção a que este o comprou.
	 */
	private CatalogMap<String, Product> getProdutos() {
		return new CatalogMap<>(produtos);
	}

    /**
     * Retorna um Client com todas as propriedas associadas a um cliente,
     * ou null caso não haja nenhum Client associado ao cliente dado.
     * @param cliente Cliente cujo o Client associado será retornado
     * @return um Client com todas as propriedas associadas a um cliente
     */
    public Client getCliente(String cliente) {
        return new Client(clientes.get(cliente));
    }

    /**
     * Retorna um Product com todas as propriedas associadas a um produto,
     * ou null caso não haja nenhum Product associado ao produto dado.
     * @param produto produto cujo o Product associado será retornado
     * @return um Product com todas as propriedas associadas a um produto
     */
    public Product getProduct(String produto) {
        return new Product(produtos.get(produto));
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
