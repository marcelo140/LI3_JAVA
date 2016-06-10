import java.io.IOException;
import java.util.*;

public class HipermercadoApp {
	private static final String produtosFilename = "../data/Produtos.txt";
	private static final String clientesFilename = "../data/Clientes.txt";
	private static final String vendas1mFilename = "../data/Vendas_1M.txt";
	private static Menu queries;

	public static void main(String[] args) {
		Hipermercado hm = new Hipermercado();
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

		System.out.println("Todos os produtos: " + hm.getProdutos());
		System.out.println("Produtos comprados: " + hm.getProdutosComprados());
		System.out.println("Produtos não comprados: " + hm.getListaNaoComprados().toString() + "\n");

		System.out.println("Faturação total: " + hm.getFaturacaoTotal());
		System.out.println("Venda a zero: " + hm.getVendasZero());

		executaMenu();
	}

	private static void carregaMenu() {

	    String[] opcs = {" 1 • Lista de códigos nunca comprados e total",
							" 2 • Total de vendas e total de os clientes que as fizeram, por mês",
							" 3 • Total de compras, produtos comprados e total gasto por mês de um cliente",
							" 4 • Quantas vezes, e por quantos clientes o produto foi comprado e total faturado, mês a mês",
							" 5 • Produtos comprados do cliente",
							" 6 • N produtos mais vendidos em todo o ano",
							" 7 • Para cada filial, a lista dos três maiores compradores em termos de dinheiro",
							" 8 • N clientes com produtos diferentes",
							" 9 • N clientes que mais compraram produto dado", "",
							" 0 • Sair"};
	    
		queries = new Menu(opcs);
	}

	private static void executaMenu() {
		do {
			queries.executa();
			switch(queries.getOpcao()) {
					/* queries */
			}
		} while(queries.getOpcao() != 0);
	}
}
