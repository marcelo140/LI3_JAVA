import java.io.*;
import java.util.*;

public class HipermercadoApp {
	private static final int NUML = 20;
    private static final String produtosFilename = "../data/Produtos.txt";
    private static final String clientesFilename = "../data/Clientes.txt";
    private static final String vendas1mFilename = "../data/Vendas_1M.txt";
    private static final String defaultDataPath  = "../data/hipermercado.dat";


    private static Menu queries, principal;
    private static Hipermercado hm;
    private static String produtosF, clientesF, vendasF;

	private HipermercadoApp() {}

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

        String[] prc = { "Carregar ficheiros", "Restaurar dados", "Guardar dados" ,"Queries", "Estatísticas"};
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
				case 2 : carregaDados();
				         break;
				case 3 : salvaDados();
				         break;
                case 4 : executaMenuQueries();
                        break;
                case 5 : estatisticas();
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
                case 1 : query1();
                         break;
				case 2 : query2();
					  	 break;
				case 3 : query3();
						 break;
				case 4 : query4();
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

	private static void salvaDados() {
		System.out.print("Ficheiro onde guardar: ");
		String file = Input.lerString();

		if (file.isEmpty())
			file = defaultDataPath;

		try {
			hm.guardarDados(file);
		} catch(IOException e) {
			System.out.println("Impossível salvar ficheiro " + file);
		}
	}

	/**
	 * Pede ao utilizador o ficheiro onde gravar os dados e grava.
	 * Caso o utilizador não defina ficheiro, o default será ../data/hipermercado.dat
	 */
	private static void carregaDados() {
		System.out.print("Ficheiro de onde carregar: ");
		String file = Input.lerString();

		if (file.isEmpty())
			file = defaultDataPath;

		try {
			hm = Hipermercado.carregarDados(file);
		} catch(IOException | ClassNotFoundException e) {
			System.out.println("Impossível carregar ficheiro " + file);
		}
	}

	public static void query1() {
        long inicio, fim;

        inicio = System.nanoTime();
        ArrayList<String> lista = new ArrayList<> (hm.getListaNaoComprados());
        lista.sort((s1, s2) -> s1.compareTo(s2));
        fim = System.nanoTime();

        System.out.println("\nCalculado em " + (double) (fim-inicio) / 1.0E9 + "s\n");
        System.out.print("Pressa <Enter> para continuar...");
        Input.lerString();

        Navegador nav = new Navegador(NUML, lista);
        nav.show();
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

		System.out.println("\nCalculado em " + (double) (fim-inicio) / 1.0E9 + "s\n");
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

		System.out.println("\nCalculado em " + (double) (fim-inicio) / 1.0E9 + "s\n");
		System.out.println("Pressa <Enter> para continuar...");

		Input.lerString();

		Navegador nav = new Navegador(20, "MÊS\tCOMPRAS\tPRODUTOS\tGASTO", data.toListString());
		nav.show();
	}

	private static void query4() {
		long inicio, fim;

		System.out.print("Produto a pesquisar: ");
		String produto = Input.lerString();

		inicio = System.nanoTime();
		ArraysIntIntDouble data = hm.getProductData(produto);
		fim = System.nanoTime();

		System.out.println("\nCalculado em " + (double) (fim-inicio) / 1.0E9 + "s\n");
		System.out.println("Pressa <Enter> para continuar...");

		Input.lerString();

		Navegador nav = new Navegador(20, "MÊS\tCOMPRAS\tCLIENTES\tGASTO", data.toListString());
		nav.show();
	}

	private static void query5() {
		Navegador nav;
		List<String> lista = new ArrayList<>();
		long inicio, fim;

		System.out.print("Cliente a pesquisar: ");
		String cliente = Input.lerString();

		inicio = System.nanoTime();
		Set<ParStringInt> produtos = hm.getProdutos(cliente);

		Iterator<ParStringInt> it = produtos.iterator();
		while (it.hasNext()) {
			ParStringInt p = it.next();
			lista.add("\t" + p.first() + "\t" + Integer.toString(p.second()));
		}
		fim = System.nanoTime();

		System.out.println("\nCalculado em " + (double) (fim-inicio) / 1.0E9 + "s\n");
		System.out.print("Pressa <Enter> para continuar...");
		Input.lerString();

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
		Set<TriploStringIntInt> produtos = hm.getTopProdutos();

		Iterator<TriploStringIntInt> it = produtos.iterator();
		for(int i = 0; i < n && it.hasNext(); i++) {
			TriploStringIntInt t = it.next();
			lista.add("\t"+t.first()+"\t"+t.second()+"\t"+t.third());
		}
		fim = System.nanoTime();

		System.out.println("\nCalculado em " + (double) (fim-inicio) / 1.0E9 + "s\n");
		System.out.print("Pressa <Enter> para continuar...");
		Input.lerString();

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

		System.out.println("\nCalculado em " + (double) (fim-inicio) / 1.0E9 + "s\n");
		System.out.print("Pressa <Enter> para continuar...");
		Input.lerString();

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
		Set<ParStringInt> clientes = hm.getTopClientes();

		Iterator<ParStringInt> it = clientes.iterator();
		for(int i = 0; i < n && it.hasNext(); i++) {
			ParStringInt p = it.next();
			lista.add("\t"+p.first()+"\t"+p.second());
		}
		fim = System.nanoTime();

		System.out.println("\nCalculado em " + (double) (fim-inicio) / 1.0E9 + "s\n");
		System.out.print("Pressa <Enter> para continuar...");
		Input.lerString();

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
		Set<ParStringDouble> clientes = hm.getClientes(produto);

		Iterator<ParStringDouble> it = clientes.iterator();
		for(int i = 0; i < n && it.hasNext(); i++) {
			ParStringDouble p = it.next();
			lista.add("\t" + p.first() + "\t" + Double.toString(p.second()));
		}
		fim = System.nanoTime();

		System.out.println("\nCalculado em " + (double) (fim-inicio) / 1.0E9 + "s\n");
		System.out.print("Pressa <Enter> para continuar...");
		Input.lerString();

		nav = new Navegador(NUML, lista);
		nav.show();
	}

	/* ================ ESTATÍSTICAS ===================== */

    public static void estatisticas(){
        int prods, prodnc;
        int[] f = new int[hm.getNumFiliais()];

        System.out.println("Ficheiro de Produtos: " + hm.getFichProdutos());
        System.out.println("Ficheiro de Clientes: " + hm.getFichClientes());
        System.out.println("Ficheiro de Vendas: " + hm.getFichVendas() + "\n");

        prods = hm.getProdutos();
        prodnc = hm.getProdutosComprados();
        System.out.println("Todos os produtos:      " + prods);
        System.out.println("Produtos comprados:     " + prodnc);
        System.out.println("Produtos não comprados: (" + (prods - prodnc) + ") " + hm.getListaNaoComprados().toString() + "\n");

        System.out.println("Todos os clientes: " + hm.getClientes() + "\n");


        System.out.println("Faturação total: " + hm.getFaturacaoTotal());
        System.out.println("Vendas a zero:   " + hm.getVendasZero() + "\n");

		try {
        	for (int i = 0; i < 12 ; i++)
            	System.out.printf("Mês %2d: %d compras\n", i+1, hm.getNumeroCompras(i));
        	System.out.print("\n");
		} catch (InvalidMonthException e) {
			return;
		}

		System.out.printf("FATURAÇÃO:\nMÊS\t");
		for (int i = 0; i < hm.getNumFiliais(); i++)
			System.out.printf("FILIAL %d\t\t", i+1);
		System.out.print("\n");
		for (int i = 0; i < 12; i++) {
			System.out.printf("%2d", i+1);
			try {
				for (int fil = 0 ; fil < hm.getNumFiliais(); fil++)
				System.out.printf("\t %f", hm.getFaturacao(i, fil));
			} catch(InvalidMonthException | InvalidBranchException e) {
				return;
			}
			System.out.print("\n");
		}
		System.out.print("\n");

		for (int i = 0; i < 12; i++)
			System.out.printf("Mês %2d: %d clientes\n", i+1, hm.getNumClientes(i));
		System.out.print("\n");
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
        System.out.println("Produtos carregados em " + ((double) (fim-inicio) / 1.0E9) + " segundos");

        System.out.println("A carregar catalogo de clientes...");
        inicio = System.nanoTime();
        hm.carregarClientes(clientesF);
        fim = System.nanoTime();
        System.out.println("Clientes carregados em " + ((double) (fim-inicio) / 1.0E9) + " segundos");

		try {
        System.out.println("A carregar vendas...");
        inicio = System.nanoTime();
        int vendas = hm.carregarVendas(vendasF);
        fim = System.nanoTime();
        System.out.println("Vendas válidas: " + vendas);
        System.out.println("Vendas carregadas em " + ((double) (fim-inicio) / 1.0E9) + " segundos");
		} catch (NullPointerException | IOException | NumberFormatException |
		         InvalidMonthException e) {

			System.out.println("Não foi possível carregar vendas");
			return;
		}
    }
}
