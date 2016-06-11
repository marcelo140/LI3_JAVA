
/**
 * Classe implementa um menu.
 *
 */
import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Menu {
    private List<String> opcoes;
    private int op;

    /**
     * Constructor for objects of class Menu
     * @param opcoes lista de opçoes do menu
     */
    public Menu(String[] opcoes) {
        this.opcoes = new ArrayList<String>();
        for (String op : opcoes)
            this.opcoes.add(op);
        this.op = 0;
    }

    /**
     * Método para apresentar o menu e ler uma opção.
     */
    public void executa() {
        do {
            showMenu();
            this.op = lerOpcao();
        } while (this.op == -1);
    }

    /** Apresentar o menu */
    private void showMenu() {
        System.out.println("\n       ======== Menu ======== ");
        int numOpcoes = opcoes.size();

        for (int i=0; i< numOpcoes; i++) {
            System.out.print("  ");
            System.out.print(i+1);
            System.out.print(" • ");
            System.out.println(this.opcoes.get(i));
        }

        if (!opcoes.get(numOpcoes-1).equals("Fechar sessão"))
            System.out.println("\n  0 • Sair");
    }

    /** Ler uma opção válida */
    private int lerOpcao() {
        int op;

        System.out.print("  >> ");
        
		op = Input.lerInt();
        
		if (op<0 || op>this.opcoes.size()) {
            System.out.println("Opção Inválida!!");
            op = -1;
        }

        return op;
    }

    /**
     * Método para obter a última opção lida
     * @return opcao
     */
    public int getOpcao() {
        return this.op;
    }
}
