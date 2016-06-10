import java.io.*;
import java.util.*;

public class Hipermercado {
	private static final int NUML = 20;

	private CatalogSet<String> produtos;
	private CatalogSet<String> clientes;
	private Faturacao fat;
	private VendasFilial[] filiais;

	public Hipermercado(int nFiliais) {
		produtos = new CatalogSet<>(26);
		clientes = new CatalogSet<>(26);
		fat = new Faturacao(nFiliais);

		filiais = new VendasFilial[nFiliais];
		for(int i = 0; i < nFiliais; i++)
			filiais[i] = new VendasFilial();
	}

	public Hipermercado(CatalogSet<String> produtos, CatalogSet<String> clientes,
					    Faturacao fat, VendasFilial[] filais) {

		this.produtos = produtos.clone();
		this.clientes = clientes.clone();
		this.fat = fat.clone();
		this.filiais = filiais.clone();
	}

	public int getProdutos() {
		return produtos.size();
	}

	public int getProdutosComprados() {
		return fat.getProdutosComprados();
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
