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
	                    Faturacao fat, VendasFilial[] filais, String produtosF,
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

	public int[] clientesPorMes() {
		List<Set<String>> lista = new ArrayList<Set<String>>(MESES);

		for (int i = 0; i < MESES; i++)
			lista.add( new TreeSet() );

		for(int i = 0; i < filiais.length; i++) {
			List<Set<String>> tmp = filiais[i].getClientesCompraramMes();

			for(int j = 0; j < MESES; j++)
				lista.get(j).addAll( tmp.get(j) );
		}

		int[] res = new int[MESES];
		for(int i = 0; i < MESES; i++)
			res[i] = lista.get(i).size();

		return res;
	}

	public Par<int[], Par<int[], double[]>> getClientData(String cliente) {
		Client[] f = new Client[filiais.length];

		int[] comprasRealizadas = new int[MESES];
		int[] produtosComprados = new int[MESES];
		double[] faturado = new double[MESES];

		for(int i = 0; i < filiais.length; i++) {
			f[i] = filiais.get(cliente);

		for(int j = 0; j < MESES; j++)
			Set<String> produtos = new HashSet(128);

			for (int i = 0; i < filiais.length; i++) {
				c[i].getProdutos.get(j).forEach((k,v) -> { produtos.add(k); });
				comprasRealizadas[j] += f[i].getCompras(j);
				faturado[j] += f[i].getGastos(j);
			}

			comprasRealizadas[j] += produtos.size();
		}

		return new Par(comprasRealizadas, new Par(produtosComprados, faturado));
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
			System.out.println("fuk this");
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
