import java.util.*;
/**
 * Classe productSale que guardará a quantidade total vedida e o total
 * faturado de um certo produto, bem como uma lista de os clientes que 
 * o comprou em N, P, ou N+P 
 * 
 */
public class ProductSale {
	private int quantidade;
	private double faturacao;
	private HashMap<String, ClientUnit> clientes;

    /**
     * Construtor padrão; 
     */
    public ProductSale() {
		quantidade = 0;
		faturacao  = 0;
		clientes   = new HashMap<>();
    }

	/**
	 * Adiciona uma nova venda a este ProductSale
	 * @param v Venda a adicionar
	 */
	public void add(Venda v) {
		String cliente = v.getCliente();
		ClientUnit c;
		quantidade += v.getUnidades();
		faturacao  += v.getUnidades() * v.getPreco();
		
		c = clientes.get(cliente);
		c.add(v);
		clientes.put(cliente, c);
	}

	/**
	 * Devolve a quantidade total vendida deste produto
	 */
	public int getQuantidade() {
		return quantidade;
	}

	/**
	 * Devolve o total faturado por este produto
	 */
	public double getFaturado() {
		return faturacao;
	}

}
