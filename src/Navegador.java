import java.util.*;
import java.io.*;

public class Navegador {

    private int linhasPorPagina;
    private int paginas;
    private int pagina;
    private String ultimoCmd;
    private List<String> linhas;
    private String header;

    /**
     * Construtor por parametros
     * @param linhasPorPagina linhas a apresentar em cada página
     * @param linhas linhas a apresentar
     */
    public Navegador(int linhasPorPagina, List<String> linhas) {
        this.linhasPorPagina = linhasPorPagina;
        this.linhas = new ArrayList<String>(linhas);
        paginas = linhas.size() / linhasPorPagina + ((linhas.size() % linhasPorPagina == 0) ? 0 : 1);
        ultimoCmd = new String();
        header = null;
    }

    /**
     * Construtor por parametros com header
     * @param linhasPorPagina linhas a apresentar em cada página
     * @param linhas linhas a apresentar
     */
    public Navegador(int linhasPorPagina, String header, List<String> linhas) {
        this.linhasPorPagina = linhasPorPagina;
        this.linhas = new ArrayList<String>(linhas);
        paginas = linhas.size() / linhasPorPagina + ((linhas.size() % linhasPorPagina == 0) ? 0 : 1);
        ultimoCmd = new String();
        this.header = header;
    }

    /**
     * Apresenta o Navegador a partir do início
     */
    public void show() {
        try {
            show(0);
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

        if (page < 0 || page >= paginas)
            throw new InvalidPageException("Página inválida!");

        pagina = page;
        st = pagina * linhasPorPagina;
        ed = st + linhasPorPagina;
        ed = (ed > linhas.size()) ? linhas.size()  : ed;

        System.out.printf("================== %d / %d ==================\n", pagina+1, paginas);

        if (header != null)
            System.out.println("\t" + header + "\n");

        for (String str : linhas.subList(st, ed))
            System.out.printf("\t%s\n", str);

        System.out.printf("=============================================\n");

        nextOperation();
    }

    /**
     * Lê a operação a efetuar
     */
    public void nextOperation() {
        String op;
        int nextPage = pagina;

        System.out.printf("b: Anterior\tn: Seguinte\th: Ajuda\n");
        System.out.printf("g: Saltar Página\tq: Sair\n");
        System.out.printf("\t>>");

        op = Input.lerString();

        if (op.isEmpty()) op = ultimoCmd;

        while(op.isEmpty())
            op = Input.lerString();

        ultimoCmd = op;
        switch(op.charAt(0)) {
            case 'b' :  if (op.length() > 1)
                            nextPage = pagina - Integer.parseInt(op.substring(1));
                        else nextPage = pagina-1;
                        break;
            case 'n' : if (op.length() > 1)
                            nextPage = pagina + Integer.parseInt(op.substring(1));
                        else nextPage = pagina +1;
                        break;
            case 'g' : if (op.length() > 1)
                            nextPage = Integer.parseInt(op.substring(1)) - 1;
                        break;
            case 'h' : printHelp();
                       try { System.in.read();  }
                       catch (IOException e) { break; }
                       break;
            case 'q' : return;
        }

        if (nextPage < 0) nextPage = 0;
        else if (nextPage >= paginas) nextPage = paginas-1;

        try {
            show(nextPage);
        } catch (InvalidPageException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printHelp() {
        System.out.println("\tb<num>  Retrocede <num> páginas.");
        System.out.println("\tn<num>  Avança <num> páginas.");
        System.out.println("\tg<num>  Salta para a página <num>.");
        System.out.println("\t<enter> Utiliza o último comando.");
    }
}
