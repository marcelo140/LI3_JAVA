import java.io.*;
import java.util.List;

public class Hipermercado {
	private static final int filiais = 3;

	CatalogSet<String> produtos;
	CatalogSet<String> clientes;
	Faturacao fat;

	public Hipermercado() {
		produtos = new CatalogSet<>(26);
		clientes = new CatalogSet<>(26);
		fat = new Faturacao(filiais);
	}

	public Hipermercado(CatalogSet<String> produtos, CatalogSet<String> clientes,
					    Faturacao fat) {
		this.produtos = produtos.clone();
		this.clientes = clientes.clone();
		this.fat = fat.clone();
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

	public int getVendasZero() {
		return fat.getVendasZero();
	}

	public double getFaturacaoTotal() {
		return fat.getFaturacaoTotal();
	}

	public void clear() {
		produtos = new CatalogSet<>();
		clientes = new CatalogSet<>();
		fat = new Faturacao(filiais);
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
				}
			}
		}
		catch (VendaParseException e) {
			System.out.println("String could not be parsed");
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
		}
	}

    public void carregarClientes(String fich) throws IOException {
		BufferedReader inStream = null;
		String cliente = null;

		inStream = new BufferedReader(new FileReader(fich));

		while( (cliente = inStream.readLine()) != null )
			clientes.add(cliente.charAt(0) - 'A', cliente);
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
}
