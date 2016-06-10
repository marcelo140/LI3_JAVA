import java.io.IOException;
import java.util.*;

public class HipermercadoApp {
	private static final String produtosFilename = "../data/Produtos.txt";
	private static final String clientesFilename = "../data/Clientes.txt";
	private static final String vendas1mFilename = "../data/Vendas_1M.txt";
	private static Menu queries, principal;
	private static Hipermercado hm;

	public static void main(String[] args) {
 		hm = new Hipermercado(3);
		long inicio, fim;

		carregaMenu();

		try {

			System.out.println("A carregar catalogo de produtos...");
			inicio = System.nanoTime();
			hm.carregarProdutos(produtosFilename);
			fim = System.nanoTime();
			System.out.println("Produtos carregados em " + ((double) (fim-inicio) / 1000000000) + " segundos");

			System.out.println("A carregar catalogo de clientes...");
			inicio = System.nanoTime();
			hm.carregarClientes(clientesFilename);
			fim = System.nanoTime();
			System.out.println("Clientes carregados em " + ((double) (fim-inicio) / 1000000000) + " segundos");

			System.out.println("A carregar vendas...");
			inicio = System.nanoTime();
			int vendas = hm.carregarVendas(vendas1mFilename);
			fim = System.nanoTime();
			System.out.println("Vendas válidas: " + vendas);
			System.out.println("Vendas carregadas em " + ((double) (fim-inicio) / 1000000000) + " segundos");

		} catch(IOException e) {
			System.out.println(e.getMessage());
			return;
		}

		executaMenu();
	}

	private static void carregaMenu() {

		String[] prc = {"Queries", "Estatísticas"};
	    String[] qrs = {" Lista de códigos nunca comprados e total",
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

	private static void executaMenu() {
		do {
			principal.executa();
			switch(principal.getOpcao()) {
				case 1 : executaMenuQueries();
			         	break;
				case 2 : hm.estatisticas();
					 	break;
			}
		} while (principal.getOpcao() != 0);
	}

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
}
