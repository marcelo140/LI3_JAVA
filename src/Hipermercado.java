import java.io.*;
import java.util.*;

public class Hipermercado {
	private final int LETRAS = 26;
	private final int MESES = 12;

	private CatalogSet<String> produtos;
	private CatalogSet<String> clientes;
	private Faturacao fat;
	private VendasFilial[] filiais;

	public Hipermercado(int nFiliais) {
		produtos = new CatalogSet<>(LETRAS);
		clientes = new CatalogSet<>(LETRAS);
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
}
