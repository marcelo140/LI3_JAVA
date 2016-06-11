import java.io.*;
import java.util.*;

public class Hipermercado implements Serializable {

    private final int LETRAS = 26;
    private final int MESES = 12;
    private static final int NUML = 20;

    private String produtosF, clientesF, vendasF;
    private CatalogSet<String> produtos;
    private CatalogSet<String> clientes;
    private Faturacao fat;
    private VendasFilial[] filiais;

	/**
 	 * Constrói um hipermercado
 	 * @param nFiliais número de filiais
 	 * @param produtosF ficheiro de produtos lido
 	 * @param clientesF ficheiro de clientes lido
 	 * @param vendasF ficheiro de vendas lido
 	 */
    public Hipermercado(int nFiliais, String produtosF, String clientesF,
                        String vendasF) {

        produtos = new CatalogSet<>(LETRAS);
        clientes = new CatalogSet<>(LETRAS);
        fat = new Faturacao(nFiliais);

        filiais = new VendasFilial[nFiliais];
        for(int i = 0; i < nFiliais; i++)
            filiais[i] = new VendasFilial();

        this.produtosF = produtosF;
        this.clientesF = clientesF;
        this.vendasF = vendasF;
    }

	/**
 	 * Obtém o número de produtos presentes no catálogo
 	 * @return número de produtos
 	 */
    public int getProdutos() {
        return produtos.size();
    }

	/**
 	 * Obtém o número de clientes presentes no catálogo
 	 * @return número de clientes
 	 */
    public int getClientes() {
        return clientes.size();
    }

	/**
 	 * Obtém número de transações efetuadas no mês dado
 	 * @param mes mes a ser pesquisado
 	 * @return número de transações
 	 * @throws InvalidMonthException
 	 */
    public int getNumeroCompras(int mes) throws InvalidMonthException {
        if (mes < 0 || mes > 11)
            throw new InvalidMonthException("Mês inválido");

        return fat.getNumCompras(mes);
    }

	/**
 	 * Obtém o número de produtos distintos que foram comprados
 	 * @return número de produtos comprados
 	 */
    public int getProdutosComprados() {
        return fat.getProdutosComprados();
    }

	/**
 	 * Obtém o número de clientes distintos que compraram
 	 * @return número de clientes que compraram
 	 */
    public int getClientesCompraram() {
        CatalogSet<String> clientes = new CatalogSet<>(LETRAS);

        for (int i = 0; i < filiais.length; i++) {
            CatalogSet<String> filial = filiais[i].getClientesCompraram();

            for (int j = 0; j < LETRAS; j++) {
                final int letra = j;
                filial.get(j).forEach( e -> { clientes.add(letra, e); });
            }
        }

        return clientes.size();
    }

	/**
 	 * Obtém número de clientes distintos que compraram num dado mês
 	 * @param mes mes a ser pesquisado
 	 * @return número de clientes que compraram no mês dado
 	 * @throws InvalidMonthException
 	 */
    public int getClientesCompraramMes(int mes) throws InvalidMonthException {
        if (mes < 0 || mes > 11)
            throw new InvalidMonthException("Mês inválido");

        CatalogSet<String> clientes = new CatalogSet<>(LETRAS);

        for (int i = 0; i < filiais.length; i++) {
            CatalogSet<String> filial = filiais[i].getClientesCompraramMes(mes);

            for (int j = 0; j < LETRAS; j++) {
                final int letra = j;
                filial.get(j).forEach( e -> { clientes.add(letra, e); });
            }
        }

        return clientes.size();
    }

	/**
 	 * Obtém o número de clientes que não compraram
 	 * @return número de clientes que não compraram
 	 */
    public int getClientesNaoCompraram() {
        return clientes.size() - getClientesCompraram();
    }

	/**
 	 * Calcula lista de produtos não comprados
 	 * @return lista produtos não comprados
 	 */
    public List<String> getListaNaoComprados() {
        return fat.getListaNaoComprados();
    }

	/**
 	 * Obter número de transações em que não foi movido dinheiro
 	 * @return número de transações em que não foi movido dinheiro
 	 */
    public int getVendasZero() {
        return fat.getVendasZero();
    }

	/**
 	 * Obtém o total faturado
 	 * @return total faturado
 	 */
    public double getFaturacaoTotal() {
        return fat.getFaturacaoTotal();
    }

	/**
 	 * Obtém o montante faturado num dado mês
 	 * @param mes mes a ser pesquisado
 	 * @return montante faturado no mês dado
 	 * @throws InvalidMonthException
 	 */
    public double getFaturacaoMes(int mes) throws InvalidMonthException {
        return fat.getFaturacaoMes(mes);
    }

	/**
 	 * Obtém o montante faturado durante o mês dado, na filial dada
 	 * @param mes mês a ser pesquisado
 	 * @param filial filial a ser pesquisada
 	 * @return montante faturado
 	 * @throws InvalidMonthException
 	 * @throws InvalidBranchException
 	 */
    public double getFaturacao(int mes, int filial) throws InvalidMonthException,
	                                                       InvalidBranchException {
        return fat.getFaturacao(mes, filial);
    }

	/**
 	 * Calcula lista de todos os clientes e respetivos dados
 	 * @return lista de clientes e respetivos dados
 	 */
    private CatalogMap<String, Client> getAllClientes() {

        CatalogMap<String, Client> catalogo = filiais[0].getClientes();

        for(int i = 1; i < filiais.length; i++) {
            CatalogMap<String, Client> tmp = filiais[i].getClientes();

            for(int letra = 0; letra < LETRAS; letra++) {
                final int index = letra;
                tmp.get(letra).forEach((k,v) -> { Client c = catalogo.get(index, k);
                                                  if (c == null)
                                                    catalogo.put(index, k, v.clone());
                                                  else
                                                    c.merge(v);
                                                  });
            }
        }

        return catalogo;
    }

	/**
 	 * Obtém dados de todos os produtos comprados por um cliente
 	 * @param cliente cliente a ser pesquisado
 	 * @return lista de produtos e respetivos dados
 	 * @throws ClienteNaoExisteException
 	 */
    private Map<String, ProductUnit> getAllProdutos(String cliente)
	                                   throws ClienteNaoExisteException {

        Map<String, ProductUnit> tree = new TreeMap<String, ProductUnit>();

        for(int i = 0; i < filiais.length; i++) {
            Client c = filiais[i].getCliente(cliente);

			if (c == null)
				throw new ClienteNaoExisteException("Cliente não existe");

            CatalogMap<String, ProductUnit> tmp = c.getProdutos();
            System.out.println(i + "oi" + tmp.size());
            for(int mes = 0; mes < MESES; mes++) {
                tmp.get(mes).forEach( (k,v) -> { ProductUnit pu;
                                                 if ( (pu = tree.get(k)) == null )
                                                    tree.put(k, v.clone());
                                                 else
                                                    pu.add(v);
                                               });
            }
        }

        return tree;
    }

	/**
 	 * Obtém dados de todos os clientes que compraram o produto
 	 * @param produto produto a ser pesquisado
 	 * @return lista de clientes e respetivos dados
 	 * @throws ProdutoNaoExisteException
 	 */
    private Map<String, ClientUnit> getAllClientes(String produto)
	                                 throws ProdutoNaoExisteException {

        Map<String, ClientUnit> tree = new TreeMap<String, ClientUnit>();

        for(int i = 0; i < filiais.length; i++) {
            Product p = filiais[i].getProduct(produto);

			if (p == null)
				throw new ProdutoNaoExisteException("Produto não existe");

            CatalogMap<String, ClientUnit> tmp = p.getClientes();

            for(int mes = 0; mes < MESES; mes++) {
                tmp.get(mes).forEach( (k,v) -> { ClientUnit clu;
                                                 if ( (clu = tree.get(k)) == null)
                                                    tree.put(k, v.clone());
                                                 else
                                                    clu.add(v);
                                               });
            }
        }

        return tree;
    }

	/**
 	 * Obtém dados sobre o cliente dado, nomeadamente o número
 	 * de produtos distintos comprados, o número de compras realizadas
 	 * e o total gasto
 	 * @param cliente cliente a ser pesquisado
 	 * @return dados do cliente
 	 * @throws ClienteNaoExisteException
 	 */
    public ArraysIntIntDouble getClientData(String cliente)
				throws ClienteNaoExisteException{

        Client[] clients = new Client[filiais.length];
        List<CatalogMap<String, ProductUnit>> produtos = new ArrayList<>(3);

        int[] comprasRealizadas = new int[MESES];
        int[] produtosComprados = new int[MESES];
        double[] faturado = new double[MESES];

        for(int i = 0; i < filiais.length; i++) {
            clients[i] = filiais[i].getCliente(cliente);

            if (clients[i] == null)
                throw new ClienteNaoExisteException("Cliente não existe");

            produtos.add(clients[i].getProdutos());
        }

        for(int j = 0; j < MESES; j++) {
            Set<String> p = new HashSet<>(256);

            for (int i = 0; i < filiais.length; i++) {
                produtos.get(i).get(j).forEach((k,v) -> { p.add(k); });
                comprasRealizadas[j] += clients[i].getCompras(j);
                faturado[j] += clients[i].getGastos(j);
            }

            produtosComprados[j] = p.size();
        }

        return new ArraysIntIntDouble(comprasRealizadas, produtosComprados, faturado);
    }

	/**
 	 * Obtém dados sobre o produto dado, nomeadamente o número
 	 * de clientes distintos que o compraram, o número de vezes
 	 * que foi comprado e total faturado
 	 * @param produto produto a ser pesquisado
 	 * @return dados do produto
 	 * @throws ProdutoNaoExisteException
 	 */
    public ArraysIntIntDouble getProductData(String produto) throws ProdutoNaoExisteException {
        Product[] products =  new Product[filiais.length];
        List<CatalogMap<String, ClientUnit>> clientes =
                      new ArrayList<CatalogMap<String, ClientUnit>>(3);

        int[] vezesComprado = new int[MESES];
        int[] clientesCompraram = new int[MESES];
        double[] faturado = new double[MESES];

        for(int i = 0; i < filiais.length; i++) {
            products[i] = filiais[i].getProduct(produto);

            if (products[i] == null)
                throw new ProdutoNaoExisteException("Produto não existe");

            clientes.add(products[i].getClientes());
        }

        for(int j = 0; j < MESES; j++) {
            Set<String> c = new HashSet<>(256);

            for (int i = 0; i < filiais.length; i++) {
                clientes.get(i).get(j).forEach((k,v) -> { c.add(k); });
                vezesComprado[j] += products[i].getVendas(j);
                faturado[j] += products[i].getFaturado(j);
            }

            clientesCompraram[j] = c.size();
        }

        return new ArraysIntIntDouble(vezesComprado, clientesCompraram, faturado);
    }

	/**
 	 * Calcula uma lista de produtos, ordenados decrescentemente pelo número de
 	 * clientes que o comprou
 	 * @return lista de produtos e respetivo número de clientes que o compraram
 	 */
    public Set<TriploStringIntInt> getTopProdutos() {
        CatalogMap<String, Product> catalog = VendasFilial.mergeProdutos(filiais);

        TreeSet<TriploStringIntInt> res =
                  new TreeSet<>(new ComparatorTriploStringIntIntBySnd());

        for (int i = 0; i < LETRAS; i++)
            catalog.get(i).forEach((k,v) -> res.add(
              new TriploStringIntInt(k, v.getUnidadesVendidas(), v.getNumeroClientes())));

        return res;
    }

	/**
 	 * Calcula uma lista de clientes, ordenada decrescentemente pelo número de
 	 * produtos distintos que cada um comprou
 	 * @return lista de clientes e respetivo número de produtos comprados
 	 */
    public Set<ParStringInt> getTopClientes() {
        CatalogMap<String, Client> catalog = getAllClientes();
        TreeSet<ParStringInt> res = new TreeSet<>(new ComparatorParStringIntByInt());

        for (int i = 0; i < LETRAS; i++)
            catalog.get(i).forEach((k,v) ->
                                 res.add(new ParStringInt(k, v.getNumeroProdutos())));

        return res;
    }


	/**
 	 * Calcula todos os produtos comprados por um dado cliente, ordenados decrescentemente
 	 * pela quantidade comprada
 	 * @param cliente cliente a ser pesquisado
 	 * @return lista de produtos e respetiva quantidade comprada
 	 * @throws ClienteNaoExisteException
 	 */
	public Set<ParStringInt> getProdutos(String cliente) throws ClienteNaoExisteException {
		Map<String, ProductUnit> tree = getAllProdutos(cliente);

		TreeSet<ParStringInt> res = new TreeSet<>( new ComparatorParStringIntByInt() );
		tree.forEach((k,v) -> res.add(new ParStringInt(k,v.getQuantidade())));

		return res;
	}

	/**
 	 * Calcula todos os clientes que compraram um dado produto, ordenados decrescentemente
 	 * pelos respetivos gastos
 	 * @param produto produto a ser pesquisado
 	 * @return lista de clientes e respetivos gastos
 	 * @throws ProdutoNaoExisteException
 	 */
	public Set<ParStringDouble> getClientes(String produto) throws ProdutoNaoExisteException {
		Map<String, ClientUnit> tree = getAllClientes(produto);

		TreeSet<ParStringDouble> res = new TreeSet<>( new ComparatorParStringDoubleByDouble() );
		tree.forEach((k,v) -> res.add(new ParStringDouble(k,v.getFaturado())));

		return res;
	}

	/**
 	 * Calcula o número de clientes distintos que comprou em cada mês
 	 * @return número de clientes que compraram, mês a mês
 	 */
	public int[] clientesPorMes() {
		CatalogSet<String> lista = new CatalogSet<>(MESES);

		for(int i = 0; i < filiais.length; i++) {
			CatalogSet<String> tmp = filiais[i].getClientesCompraramMes();

			for(int j = 0; j < MESES; j++)
				lista.get(j).addAll( tmp.get(j) );
		}

		int[] res = new int[MESES];
		for(int i = 0; i < MESES; i++)
			res[i] = lista.get(i).size();

		return res;
	}

	/**
 	 * Remove todos os dados guardados
 	 */
    public void clear() {
        produtos.clear();
        clientes.clear();
		fat.clear();

        for (int i = 0; i < filiais.length; i++)
            filiais[i].clear();
    }

	/**
 	 * Carrega vendas a partir do ficheiro dado
 	 * @param fich ficheiro com os dados ads vendas
 	 * @throws IOException
 	 * @throws NullPointerException
 	 * @throws NumberFormatException
 	 * @throws InvalidMonthException
 	 */
    public int carregarVendas(String fich)
	     throws IOException, NullPointerException, NumberFormatException,
				InvalidMonthException {

        BufferedReader inStream = null;
        String linha = null;
        int valid = 0;

        inStream = new BufferedReader(new FileReader(fich));

        while ( (linha = inStream.readLine()) != null) {
           Venda v = Venda.parse(linha);
           if (v.isValid(produtos, clientes)) {
              valid++;
              fat.addSale(v);
              filiais[v.getFilial()].add(v);
          }
        }

        return valid;
    }

	/**
 	 * Carrega produtos a partir do ficheiro dado
 	 * @param fich ficheiro com os dados dos produtos
 	 * @throws IOException
 	 */
    public void carregarProdutos(String fich) throws IOException {
        BufferedReader inStream = null;
        String produto = null;
        inStream = new BufferedReader(new FileReader(fich));

        while( (produto = inStream.readLine()) != null ) {
            produtos.add(produto.charAt(0) - 'A', produto);
            fat.addProduto(produto);

            for (int i = 0; i < filiais.length; i++)
                filiais[i].addProduto(produto);
        }
    }

	/**
	 * Carrega clientes a partir do ficheiro dado
	 * @param fich ficheiro com dados dos clientes
	 * @throws IOException
	 */
    public void carregarClientes(String fich) throws IOException {
        BufferedReader inStream = null;
        String cliente = null;

        inStream = new BufferedReader(new FileReader(fich));

        while( (cliente = inStream.readLine()) != null ) {
            clientes.add(cliente.charAt(0) - 'A', cliente);

            for (int i = 0; i < filiais.length; i++)
                filiais[i].addCliente(cliente);
        }
    }

	/**
 	 * Guarda os dados do hipermercado num ficheiro
 	 * @param path caminho para o ficheiro
 	 * @throws IOException
 	 */
    public void guardarDados(String path) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));

        oos.writeObject(this);
        oos.flush();

        oos.close();
    }

	/**
	 * Carrega para memória dados de um Hipermercado a partir do ficheiro dado
	 * @param path caminho para o ficheiro a ser carregado
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
    public static Hipermercado carregarDados(String path) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));

        Hipermercado hm = (Hipermercado) ois.readObject();

        ois.close();
        return hm;
    }

	/**
 	 * Calcula uma lista com os três clientes que mais compraram de cada filial
 	 * @return lista dos top 3 clientes para cada filial
 	 */
	public List<ParStringDouble> getTop3Clientes() {
		List<ParStringDouble> res = new ArrayList<>(3*filiais.length);

		for(int f = 0; f < filiais.length; f++) {
			Iterator<ParStringDouble> it = filiais[f].getClientesByFaturado().iterator();

			for(int i = 0; i < 3 && it.hasNext(); i++)
				res.add(it.next());
		}

		return res;
	}

    /**
     * Devolve o path do ficheiro de produtos
     * @return o path do ficheiro de produtos
     */
    public String getFichProdutos() {
        return produtosF;
    }

    /**
     * Devolve o path do ficheiro de clientes
     * @return o path do ficheiro de clientes
     */
    public String getFichClientes() {
        return clientesF;
    }

    /**
     * Devolve o path do ficheiro de vendas
     * @return o path do ficheiro de vendas
     */
    public String getFichVendas() {
        return vendasF;
    }

    /**
     * Devolve o número de filiais deste Hipermercado
     * @return o número de filiais deste Hipermercado
     */
     public int getNumFiliais() {
         return filiais.length;
     }
}
