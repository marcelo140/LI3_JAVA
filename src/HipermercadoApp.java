import java.io.*;
import java.util.*;

public class HipermercadoApp {
	private static final int NUML = 20;
    private static final String produtosFilename = "../data/Produtos.txt";
    private static final String clientesFilename = "../data/Clientes.txt";
    private static final String vendas1mFilename = "../data/Vendas_1M.txt";

    private static Menu queries, principal;
    private static Hipermercado hm;
    private static String produtosF, clientesF, vendasF;

    public static void main(String[] args) {

        carregaMenu();

        try {
            executaMenu();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            return;
        }


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
    private static void executaMenu() throws IOException{
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
				case 2 : query2();
					  	 break;
				case 3 : query3();
						 break;
				case 5 : query5();
						 break;
				case 7 : query7();
				         break;
				case 6 : query6();
						 break;
				case 8 : query8();
						 break;
				case 9 : query9();
						 break;
                    /* queries */
            }
        } while(queries.getOpcao() != 0);
    }

	private static void query2() {
		int mes, vendas, clientes;
		long inicio, fim;

		System.out.print("\tMês: ");
		mes = Input.lerInt() - 1;

		try {
			inicio = System.nanoTime();
			vendas = hm.getNumeroCompras(mes);
			clientes = hm.getClientesCompraramMes(mes);
			fim = System.nanoTime();
		}catch(InvalidMonthException e) {
			System.out.println(e.getMessage());
			return;
		}

		System.out.println("Número de vendas: " + vendas);
		System.out.println("Número de clientes: " + clientes);

		System.out.println("\nCalculado em " + (double) (fim-inicio) / 1000000000 + "s\n");
		System.out.print("Pressa <Enter> para continuar...");

		Input.lerString();
	}

	private static void query3() {
		long inicio, fim;

		System.out.print("Cliente a pesquisar: ");
		String cliente = Input.lerString();

		inicio = System.nanoTime();
		ArraysIntIntDouble data = hm.getClientData(cliente);
		fim = System.nanoTime();

		//imprimir merdas

		System.out.println("\nCalculado em " + (double) (fim-inicio) / 1.0E9 + "s\n");
		System.out.println("Pressa <Enter> para continuar...");

		Input.lerString();
	}

	private static void query5() {
		Navegador nav;
		List<String> lista = new ArrayList<>();
		long inicio, fim;

		System.out.print("Cliente a pesquisar: ");
		String cliente = Input.lerString();

		inicio = System.nanoTime();
		TreeSet<ParStringInt> produtos = hm.getProdutos(cliente);

		Iterator<ParStringInt> it = produtos.iterator();
		while (it.hasNext()) {
			ParStringInt p = it.next();
			lista.add("\t" + p.first() + "\t" + Integer.toString(p.second()));
		}
		fim = System.nanoTime();

		nav = new Navegador(NUML, lista);
		nav.show();
	}

	private static void query6() {
		Navegador nav;
		List<String> lista = new ArrayList<>();

		long inicio, fim;

		System.out.print("Número de produtos: ");
		int n = Input.lerInt();

		inicio = System.nanoTime();
		TreeSet<TriploStringIntInt> produtos = hm.getTopProdutos();

		Iterator<TriploStringIntInt> it = produtos.iterator();
		for(int i = 0; i < n && it.hasNext(); i++) {
			TriploStringIntInt t = it.next();
			lista.add("\t"+t.first()+"\t"+t.second()+"\t"+t.third());
		}
		fim = System.nanoTime();

		nav = new Navegador(NUML, lista);
		nav.show();
	}

	private static void query7() {
		Navegador nav;
		List<String> lista = new ArrayList<>();

		long inicio, fim;

		inicio = System.nanoTime();
		List<ParStringDouble> clientes = hm.getTop3Clientes();
		fim = System.nanoTime();

		Iterator<ParStringDouble> it = clientes.iterator();
		for(int i = 1; it.hasNext(); i++) {
			ParStringDouble p = it.next();
			lista.add("\t"+p.first()+"\t"+p.second());

			if (i % 3 == 0)
				lista.add("");
		}

		System.out.println("Calculado em: " + (double) (fim-inicio)/1.0E9);
		
		nav = new Navegador(NUML, lista);
		nav.show();
	}

	private static void query8() {
		Navegador nav;
		List<String> lista = new ArrayList<>();

		long inicio, fim;

		System.out.print("Número de clientes: ");
		int n = Input.lerInt();

		inicio = System.nanoTime();
		TreeSet<ParStringInt> clientes = hm.getTopClientes();

		Iterator<ParStringInt> it = clientes.iterator();
		for(int i = 0; i < n && it.hasNext(); i++) {
			ParStringInt p = it.next();
			lista.add("\t"+p.first()+"\t"+p.second());
		}
		fim = System.nanoTime();

		nav = new Navegador(NUML, lista);
		nav.show();
	}

	private static void query9() {
		Navegador nav;
		List<String> lista = new ArrayList<>();
		long inicio, fim;

		System.out.print("Produto a pesquisar: ");
		String produto = Input.lerString();

		System.out.print("Número de clientes: ");
		int n = Input.lerInt();

		inicio = System.nanoTime();
		TreeSet<ParStringDouble> clientes = hm.getClientes(produto);

		Iterator<ParStringDouble> it = clientes.iterator();
		for(int i = 0; i < n && it.hasNext(); i++) {
			ParStringDouble p = it.next();
			lista.add("\t" + p.first() + "\t" + Double.toString(p.second()));
		}
		fim = System.nanoTime();

		nav = new Navegador(NUML, lista);
		nav.show();
	}

    private static void carregaFicheiros() throws IOException {
        Scanner is = new Scanner(System.in);
		long inicio, fim;

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
        int vendas = hm.carregarVendas(vendasF);
        fim = System.nanoTime();
        System.out.println("Vendas válidas: " + vendas);
        System.out.println("Vendas carregadas em " + ((double) (fim-inicio) / 1000000000) + " segundos");

    }
}
