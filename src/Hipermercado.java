import java.io.*;
import java.util.*;

public class Hipermercado {
	private final int LETRAS = 26;
	private final int MESES = 12;
	private static final int NUML = 20;

	private String produtosF, clientesF, vendasF;
	private CatalogSet<String> produtos;
	private CatalogSet<String> clientes;
	private Faturacao fat;
	private VendasFilial[] filiais;

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

	public Hipermercado(CatalogSet<String> produtos, CatalogSet<String> clientes,
	                    Faturacao fat, VendasFilial[] filiais, String produtosF,
	                    String clientesF, String vendasF) {

		this.produtos = produtos.clone();
		this.clientes = clientes.clone();
		this.fat = fat.clone();
		this.filiais = filiais.clone();
		this.produtosF = produtosF;
		this.clientesF = clientesF;
		this.vendasF = vendasF;
	}

	public int getProdutos() {
		return produtos.size();
	}

	public int getNumeroCompras(int mes) throws InvalidMonthException {
		if (mes < 0 || mes > 11)
			throw new InvalidMonthException("Mês inválido");

		return fat.getNumCompras(mes);
	}

	public int getProdutosComprados() {
		return fat.getProdutosComprados();
	}

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

	public int getClientesNaoCompraram() {
		return clientes.size() - getClientesCompraram();
	}

	public List<String> getListaNaoComprados() {
		return fat.getListaNaoComprados();
	}

	public int getClientes() {
		return clientes.size();
	}

	public int getVendasZero() {
		return fat.getVendasZero();
	}

	public double getFaturacaoTotal() {
		return fat.getFaturacaoTotal();
	}

	public double getFaturacaoMes(int mes) throws InvalidMonthException {
		if (mes < 0 || mes > 11)
			throw new InvalidMonthException("Mês inválido");

		return fat.getFaturacaoMes(mes);
	}

//	private CatalogMap<String, Product> getAllProdutos() {
//		CatalogMap<String, Product> catalogo = filiais[0].getProdutos();
//
//		for(int i = 1; i < filiais.length; i++) {
//			CatalogMap<String, Product> tmp = filiais[i].getProdutos();
//
//			for(int letra = 0; letra < LETRAS; letra++) {
//				final int index = letra;
//				tmp.get(letra).forEach((k,v) -> { Product p = catalogo.get(index, k);
//				                                  if (p == null)
//				                                  	catalogo.put(index, k, v.clone());
//				                                  else
//				                                  	p.merge(v);
//				                                });
//			}
//		}
//
//		return catalogo;
//	}

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

	private Map<String, ProductUnit> getAllProdutos(String cliente) {
		Map<String, ProductUnit> tree = new TreeMap<String, ProductUnit>();

		for(int i = 0; i < filiais.length; i++) {
			CatalogMap<String, ProductUnit> tmp = filiais[i].getCliente(cliente)
			                                                .getProdutos();
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

	private Map<String, ClientUnit> getAllClientes(String produto) {
		Map<String, ClientUnit> tree = new TreeMap<String, ClientUnit>();

		for(int i = 0; i < filiais.length; i++) {
			CatalogMap<String, ClientUnit> tmp = filiais[i].getProduct(produto)
			                                               .getClientes();

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

	public TreeSet<ParStringInt> getProdutos(String cliente) {
		Map<String, ProductUnit> tree = getAllProdutos(cliente);

		TreeSet<ParStringInt> res = new TreeSet<>( new ComparatorParStringIntByInt() );
		tree.forEach((k,v) -> res.add(new ParStringInt(k,v.getQuantidade())));

		return res;
	}

	public TreeSet<ParStringDouble> getClientes(String produto) {
		Map<String, ClientUnit> tree = getAllClientes(produto);

		TreeSet<ParStringDouble> res = new TreeSet<>( new ComparatorParStringDoubleByDouble() );
		tree.forEach((k,v) -> res.add(new ParStringDouble(k,v.getFaturado())));

		return res;
	}

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

	public ArraysIntIntDouble getClientData(String cliente) {
		Client[] clients = new Client[filiais.length];
		List<CatalogMap<String, ProductUnit>> produtos = new ArrayList<>(3);

		int[] comprasRealizadas = new int[MESES];
		int[] produtosComprados = new int[MESES];
		double[] faturado = new double[MESES];

		for(int i = 0; i < filiais.length; i++) {
			clients[i] = filiais[i].getCliente(cliente);
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

	public ArraysIntIntDouble getProductData(String produto) {
		Product[] products =  new Product[filiais.length];
		List<CatalogMap<String, ClientUnit>> clientes =
		              new ArrayList<CatalogMap<String, ClientUnit>>(3);

		int[] vezesComprado = new int[MESES];
		int[] clientesCompraram = new int[MESES];
		double[] faturado = new double[MESES];

		for(int i = 0; i < filiais.length; i++) {
			products[i] = filiais[i].getProduct(produto);
			clientes.add(products[i].getClientes());
		}

		for(int j = 0; j < MESES; j++) {
			Set<String> c = new HashSet<>(256);

			for (int i = 0; i < filiais.length; i++) {
				clientes.get(j).forEach((k,v) -> { c.add(k); });
				vezesComprado[j] += products[i].getVendas(j);
				faturado[j] += products[i].getFaturado(j);
			}

			clientesCompraram[j] = c.size();
		}

		return new ArraysIntIntDouble(vezesComprado, clientesCompraram, faturado);
	}

	public TreeSet<TriploStringIntInt> getTopProdutos() {
		CatalogMap<String, Product> catalog = VendasFilial.mergeProducts(filiais);
		TreeSet<TriploStringIntInt> res = 
		          new TreeSet<>(new ComparatorTriploStringIntIntBySnd());

		for (int i = 0; i < LETRAS; i++)
			catalog.get(i).forEach((k,v) -> res.add(
			  new TriploStringIntInt(k, v.getUnidadesVendidas(), v.getNumeroClientes())));
			
		return res;	
	}

	public TreeSet<ParStringInt> getTopClientes() {
		CatalogMap<String, Client> catalog = getAllClientes();
		TreeSet<ParStringInt> res = new TreeSet<>(new ComparatorParStringIntByInt());

		for (int i = 0; i < LETRAS; i++)
			catalog.get(i).forEach((k,v) -> 
			                     res.add(new ParStringInt(k, v.getNumeroProdutos())));

		return res;
	}

	public List<ParStringDouble> getTop3Clientes() {
		List<ParStringDouble> res = new ArrayList<>(3*filiais.length);

		for(int f = 0; f < filiais.length; f++) {
			Iterator<ParStringDouble> it = filiais[f].getClientesByFaturado().iterator();

			for(int i = 0; i < 3 && it.hasNext(); i++) {
				res.add(it.next());
			}
		}

		return res;
	}

	public void clear() {
		produtos = new CatalogSet<>();
		clientes = new CatalogSet<>();
		fat = new Faturacao(filiais.length);

		for (int i = 0; i < filiais.length; i++)
			filiais[i] = new VendasFilial();
	}

	public int carregarVendas(String fich) throws IOException {
		BufferedReader inStream = null;
		String linha = null;
		int valid = 0;

		inStream = new BufferedReader(new FileReader(fich));

		try {
			while ( (linha = inStream.readLine()) != null) {
				Venda v = Venda.parse(linha);
				if (v.isValid(produtos, clientes)) {
					valid++;
					fat.addSale(v);
					filiais[v.getFilial()].add(v);
				}
			}
		}

		catch (VendaParseException | InvalidMonthException e) {
			System.out.println("Error ao converter linha");
		}

		return valid;
	}

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

	public void guardarDados(String path) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));

		oos.writeObject(this);
		oos.flush();

		oos.close();
	}

	public Hipermercado carregarDados(String path) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));

		Hipermercado hm = (Hipermercado) ois.readObject();

		ois.close();
		return hm;
	}

	/* ================ QUERIES ===================== */

	public void query1() {
		ArrayList<String> lista = new ArrayList<> (fat.getListaNaoComprados());
		Navegador nav;

		lista.sort((s1, s2) -> s1.compareTo(s2));
		nav = new Navegador(NUML, lista);
		nav.show();
	}

	/* ================ ESTATÍSTICAS ===================== */

	public void estatisticas(){
		System.out.println("Todos os produtos:      " + getProdutos());
		System.out.println("Produtos comprados:     " + getProdutosComprados());
		System.out.println("Produtos não comprados: " + getListaNaoComprados().toString() + "\n");

		System.out.println("Todos os clientes: " + getClientes() + "\n");

		System.out.println("Faturação total: " + getFaturacaoTotal());
		System.out.println("Vendas a zero:   " + getVendasZero());
	}
}
