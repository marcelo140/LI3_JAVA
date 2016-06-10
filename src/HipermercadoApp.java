import java.io.*;
import java.util.*;

public class HipermercadoApp {
	private static final String produtosFilename = "../data/Produtos.txt";
	private static final String clientesFilename = "../data/Clientes.txt";
	private static final String vendas1mFilename = "../data/Vendas_1M.txt";
	private static Menu queries, principal;
	private static Hipermercado hm;
	private String produtosF, clientesF, vendasF;

	public static void main(String[] args) {

		long inicio, fim;

		carregaMenu();

		try {
			carregaFicheiros();
		} catch(IOException e) {
			System.out.println(e.getMessage());
			return;
		}

		executaMenu();
	}

	/**
	 * Prepara os dois menus com as suas opções
	 */
	private static void carregaMenu() {

		String[] prc = { "Carregar ficheiros", "Queries", "Estatísticas"};
	    String[] qrs = { " Lista de códigos nunca comprados e total",
		                 " Total de vendas e total de os clientes que as fizeram, por mês",
		                 " Total de compras, produtos comprados e total gasto por mês de um cliente",
		                 " Quantas vezes, e por quantos clientes o produto foi comprado e total faturado, mês a mês",
		                 " Produtos comprados do cliente",
		                 " N produtos mais vendidos em todo o ano",
		                 " Para cada filial, a lista dos três maiores compradores em termos de dinheiro",
		                 " N clientes com produtos diferentes",
		                 " N clientes que mais compraram produto dado"};


		queries = new Menu(qrs);
		principal = new Menu(prc);
	}

	/**
	 * Executa o menu principal do programa e executa a opção
	 * escolhida pelo utilizador.
	 */
	private static void executaMenu() {
		do {
			principal.executa();
			switch(principal.getOpcao()) {
				case 1 : carregaFicheiros();
						 break;
				case 2 : executaMenuQueries();
			         	break;
				case 3 : hm.estatisticas();
					 	break;
			}
		} while (principal.getOpcao() != 0);
	}

	/**
	 * Executa o menu de queries e chama a query correspondente à
	 * escolha do utilizador
	 */
	private static void executaMenuQueries() {
		do {
			queries.executa();
			switch(queries.getOpcao()) {
				case 1 : hm.query1();
				         break;
					/* queries */
			}
		} while(queries.getOpcao() != 0);
	}

	private static void carregaFicheiros() throws IOException {
		Scanner is = new Scanner(System.in);

		System.out.print("Ficheiro de Clientes: ");
		clientesF = is.nextLine();
		if (clientesF.isEmpty()) clientesF = clientesFilename;

		System.out.print("Ficheiro de Produtos: ");
		produtosF = is.nextLine();
		if (produtosF.isEmpty()) produtosF = produtosFilename;

		System.out.print("Ficheiro de Vendas: ");
		vendasF = is.nextLine();
		if (vendasF.isEmpty()) vendasF = vendas1mFilename;


		hm = new Hipermercado(3, clientesF, produtosF, vendasF);


		System.out.println("A carregar catalogo de produtos...");
		inicio = System.nanoTime();
		hm.carregarProdutos(produtosF);
		fim = System.nanoTime();
		System.out.println("Produtos carregados em " + ((double) (fim-inicio) / 1000000000) + " segundos");

		System.out.println("A carregar catalogo de clientes...");
		inicio = System.nanoTime();
		hm.carregarClientes(clientesF);
		fim = System.nanoTime();
		System.out.println("Clientes carregados em " + ((double) (fim-inicio) / 1000000000) + " segundos");

		System.out.println("A carregar vendas...");
		inicio = System.nanoTime();
		int vendas = hm.carregarVendas(vendas1mF);
		fim = System.nanoTime();
		System.out.println("Vendas válidas: " + vendas);
		System.out.println("Vendas carregadas em " + ((double) (fim-inicio) / 1000000000) + " segundos");

	}
}
