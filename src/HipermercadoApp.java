import java.io.IOException;

public class HipermercadoApp {
	private static final String produtosFilename = "../data/Produtos.txt";
	private static final String clientesFilename = "../data/Clientes.txt";

	public static void main(String[] args) {
		CatalogSet<String> produtos;
		CatalogSet<String> clientes;
		long inicio, fim;
	
		try {	
			System.out.println("A carregar catalogo de produtos...");
			inicio = System.nanoTime();
			produtos = Leitura.carregarCatalogo(produtosFilename);
			fim = System.nanoTime();
			System.out.println("Produtos carregados em " + ((double) (fim-inicio) / 1000000000) + " segundos");
			
			System.out.println("A carregar catalogo de clientes...");
			inicio = System.nanoTime();
			clientes = Leitura.carregarCatalogo(clientesFilename);
			fim = System.nanoTime();
			System.out.println("Clientes carregados em " + ((double) (fim-inicio) / 1000000000) + " segundos");
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
