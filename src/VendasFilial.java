import java.util.*;

/**
 * Classe que implementa Vendas por filial. Contém um conjunto de clientes,
 * para cada cliente a sua quantidade mensal e cada produto que o qual comprou
 *
 */
public class VendasFilial {

    private final int MESES = 12;
	private final int LETRAS = 26;

    private CatalogMap<String, Client> clientes;
	private CatalogMap<String, Product> produtos;

	/**
     * Construtor padrão
     */
    public VendasFilial() {
		clientes = new CatalogMap<String, Client>(LETRAS);
		produtos = new CatalogMap<String, Product>(LETRAS);
    }

	/**
	 * Construtor por parâmetros
	 */
	public VendasFilial(CatalogMap<String, Client> clientes,
                        CatalogMap<String, Product> produtos) {

		setClientes(clientes);
		setProdutos(produtos);
	}

	/**
	 * Construtor por cópia
	 */
	public VendasFilial(VendasFilial v) {
		clientes = v.getClientes();
		produtos = v.getProdutos();
	}

    /**
	 * Retorna um CatalogMap que mapeia para cada Cliente, a quantidade que
	 * comprou e, para cada produto, a quantidade comprada e o total gasto.
	 * @return catálogo de clientes
	 */
	public CatalogMap<String, Client> getClientes() {
		CatalogMap<String, Client> catalog = new CatalogMap<>(LETRAS);

		for (int i = 0; i < LETRAS; i++) {
			final int letra = i;
			clientes.get(i).forEach( (k,v) -> { catalog.put(letra, k, v.clone()); });
		}

		return catalog;
	}

	/**
	 * Retorna um CatalogMap que mapeia para cada Produto, a quantidade
	 * total vendida e total faturado, e, para cada cliente, o tipo de
	 * promoção a que este o comprou.
	 * @return catálogo de produtos
	 */
	public CatalogMap<String, Product> getProdutos() {
		CatalogMap<String, Product> catalog = new CatalogMap<>(LETRAS);

		for (int i = 0; i < LETRAS; i++) {
			final int letra = i;
			produtos.get(i).forEach( (k,v) -> { catalog.put(letra, k, v.clone()); });
		}

		return catalog;
	}

	/**
 	 * Define o catálogo de produtos
 	 * @param produtos catálogo a ser copiado
 	 */
	private void setProdutos(CatalogMap<String, Product> produtos) {
		this.produtos = new CatalogMap<>(LETRAS);

		for (int i = 0; i < LETRAS; i++) {
			final int letra = i;
			produtos.get(i).forEach( (k,v) -> { this.produtos.put(letra, k, v.clone()); });
		}
	}

	/**
 	 * Define o catálogo de clientes
 	 * @param clientes catálogo a ser copiado
 	 */
	private void setClientes(CatalogMap<String, Client> clientes) {
		this.clientes = new CatalogMap<>(LETRAS);

		for (int i = 0; i < LETRAS; i++) {
			final int letra = i;
			clientes.get(i).forEach( (k,v) -> { this.clientes.put(letra, k, v.clone()); });
		}
	}

	/**
 	 * Obtém o conjunto de todos os clientes que compraram nesta filial
 	 * @return conjunto
 	 */
	public CatalogSet<String> getClientesCompraram() {
		CatalogSet<String> res = new CatalogSet<>(LETRAS);

		for(int i = 0; i < LETRAS; i++) {
			final int letra = i;
			clientes.get(i).forEach( (k,v) -> { if (v.comprou()) res.add(letra, k); } );
		}

		return res;
	}

	/**
 	 * Obtém o conjunto de todos os clientes que compraram nesta filial, num dado mês
 	 * @param mes
	 * @return conjunto
 	 */
	public CatalogSet<String> getClientesCompraramMes(int mes) {
		CatalogSet<String> res = new CatalogSet<>(LETRAS);

		for(int i = 0; i < LETRAS; i++) {
			final int letra = i;
			clientes.get(i).forEach( (k,v) -> { if (v.comprou(mes)) res.add(letra, k); } );
		}

		return res;
	}
	/**
 	 * Obtém o conjunto de todos os clientes que não compraram nesta filial
 	 * @return conjunto
 	 */
	public CatalogSet<String> getClientesNaoCompraram() {
		CatalogSet<String> res = new CatalogSet<>(LETRAS);

		for(int i = 0; i < LETRAS; i++) {
			final int letra = i;
			clientes.get(i).forEach( (k,v) -> { if (!v.comprou()) res.add(letra, k); } );
		}

		return res;
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
        return produtos.get(produto).clone();
    }

	/**
 	 * Adiciona um novo produto ao catálogo
 	 * @param produto produto a ser adicionado
 	 */
	public void addProduto(String produto) {
		produtos.put(produto.charAt(0) - 'A', produto, new Product());
	}

	/**
 	 * Adiciona um novo cliente ao catálogo
 	 * @param cliente cliente a ser adicionado
 	 */
	public void addCliente(String cliente) {
		clientes.put(cliente.charAt(0) - 'A', cliente, new Client());
	}

	public TreeSet<ParStringDouble> getClientesByFaturado() {
		TreeSet<ParStringDouble> res = new TreeSet( new ComparatorParStringDoubleByDouble() );

		for(int i = 0; i < LETRAS; i++)
			clientes.get(i).forEach((k,v) -> 
			                res.add(new ParStringDouble(k, v.getGastosTotal())));

		return res;
	}

	/**
 	 * Devolve a lista de clientes que comprou em cada mês
 	 * @return lista de clientes
 	 */
	public List<Set<String>> getClientesCompraramMes() {
		List<Set<String>> lista = new ArrayList<>(MESES);

		for(int i = 0; i < MESES; i++)
			lista.add( new TreeSet<String>() );

		for(int i = 0; i < LETRAS; i++) {
			clientes.get(i).forEach( (k,v) -> { for (int j = 0; j < MESES; j++)
			                                        	if (v.getCompras(j) > 0)
			                                        		lista.get(j).add(k);
			                                  });
		}

		return lista;
	}

	/**
	 * Adiciona uma venda a esta estrutura
	 * @param venda Venda a adicionar
	 */
	public void add(Venda v) throws InvalidMonthException {
		clientes.get(v.getCliente().charAt(0) - 'A', v.getCliente()).add(v);
        produtos.get(v.getProduto().charAt(0) - 'A', v.getProduto()).add(v);
    }

    /**
     * Cria um clone desta instância de VendasFilial.
     * @return um clone desta instância de VendasFilial
     */
    public VendasFilial clone() {
        return new VendasFilial(this);
    }

    /**
     * Verifica se um objeto dado é igual a este VendasFilial.
     * Retorna true se e só se o objeto dado não for null e seja igual a este.
     * @param o objeto a comparar a este VendasFilial
     * @return true se e só se o objeto dado não for null e seja igual a este
     */
     public boolean equals(Object o) {
         if (this == o)
			return true;

         if ( o == null || o.getClass() != this.getClass())
			return false;

         VendasFilial vf = (VendasFilial) o;
         return vf.clientes.equals(clientes) &&
                vf.produtos.equals(produtos);
     }

     /**
      * Retorna hash desta instância de VendasFilial
      * @return hash desta instância de VendasFilial
      */
      public int hashCode() {
			return Arrays.hashCode( new Object[] { clientes, produtos });
      }
}
