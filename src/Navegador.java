import java.util.*;

public class Navegador {

    private int linhasPorPagina;
    private int paginas;
    private int pagina;
    private List<String> linhas;

    /**
     * Construtor por parametros
     * @param linhasPorPagina linhas a apresentar em cada página
     * @param linhas linhas a apresentar
     */
    public Navegador(int linhasPorPagina, List<String> linhas) {
        this.linhasPorPagina = linhasPorPagina;
        this.linhas = new ArrayList<String>(linhas);
        paginas = linhas.size() / linhasPorPagina + ((linhas.size() % linhasPorPagina == 0) ? 0 : 1);
    }

    /**
     * Apresenta o Navegador a partir do início
     */
    public void show() {
        try {
            show(1);
        } catch (InvalidPageException e) {
            System.out.println("Não foi possível apresentar.");
        }
     }

    /**
     * Apresenta a página pedida
     * @pagina pagina a apresentar
     * @throws InvalidPageException caso a página não exista
     */
    public void show(int page) throws InvalidPageException {
        int st, ed;

        if (page < 0 || page > paginas)
            throw new InvalidPageException("Página inválida!");

        pagina = page-1;
        st = pagina * linhasPorPagina;
        ed = st + linhasPorPagina;
        ed = (ed >= paginas) ? paginas-1 : ed;

        System.out.printf("================== %d / %d ==================", pagina, paginas);

        for (String str : linhas.subList(st, ed))
            System.out.printf("\t%s\n", str);

        System.out.printf("=============================================");

        nextOperation();
    }

    /**
     * Lê a operação a efetuar
     */
    public void nextOperation() {
        Scanner is = new Scanner(System.in);
        String op;
        int nextPage = pagina;

        System.out.printf("b: Anterior\tn: Seguinte\th: Ajuda\n");
        System.out.printf("g: Saltar Página\tq: Sair\n");
        System.out.printf("\t>>");

        op = is.next();

        switch(op.charAt(0)) {
            case 'b' :  if (op.length() > 1)
                            nextPage = pagina - Integer.parseInt(op.substring(1));
                        else nextPage = pagina-1;
            case 'n' : if (op.length() > 1)
                            nextPage = pagina + Integer.parseInt(op.substring(1));
                        else nextPage = pagina +1;
            case 'g' : if (op.length() > 1)
                            nextPage = Integer.parseInt(op.substring(1));
            case 'q' : return;
        }

        if (nextPage <= 0) nextPage = 1;
        else if (nextPage >= paginas) nextPage = paginas-1;

        try {
            show(nextPage);
        } catch (InvalidPageException e) {
            System.out.println(e.getMessage());
        }
    }
}
