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
	 * Devolve o tipo de compra do cliente pedido.
	 * 1 - Normal
	 * 2 - Promoção
	 * 3 - Normal + Promoção
	 * @param cliente Cliente
	 * @throws ClienteNaoExisteException Caso o cliente pedido não comprou este produto
	 */
	public int getTipo(String cliente) throws ClienteNaoExistenteException {
		ClientUnit c = clientes.get(cliente);

		if (c == null) 
			throw new ClienteNaoExistenteException("O Cliente pedido não comprou este produto");
	
		return c.getTipo();
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
