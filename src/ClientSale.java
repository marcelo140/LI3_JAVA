import java.util.*;
/**
 * Classe que representa ClientSale.
 * Contém o total quantidade de todos os meses, bem como
 * a quantidade total e faturado de cada mes para cada produto 
 */
public class ClientSale {

	private final int MESES = 12;
	private int[] quantidade;
	private HashMap<String, ProductUnit> produtos;
    
	/**
     * Construtor padrão 
     */
    public ClientSale() {
		quantidade = new int[MESES];
		produtos = new HashMap<>();
    }

	/**
	 * Construtor por parametros
	 */
	public ClientSale(int[] quantidade, Map<String, ProductUnit> produtos) {
		this.quantidade = quantidade;
		this.produtos = new HashMap<String, ProductUnit>(produtos);

	}

	/**
	 * Construtor por cópia
	 */
	public ClientSale(ClientSale c) {
		quantidade = c.getFaturado();
		produtos = new HashMap<String, ProductUnit>(c.getProdutos());	
	}

	/**
	 * Retorna o total quantidade de todos os meses deste cliente
	 */
	public int[] getFaturado() {
		return quantidade;
	}	

	/**
	 * Retorna uma mapeamento de Produto com ProductUnit (que contém
	 * a quantidade total e o total quantidade de cada mes desse produto)
	 */
	public Map<String, ProductUnit> getProdutos() {
		return new HashMap<String, ProductUnit> (produtos);
	}

	/**
	 * Retorna true se e só se o cliente comprou algo
	 */
	public boolean bought() {
	
		for (int i : quantidade)
			if (i != 0) return true;

		return false;
	}

	/**
	 * Adiciona uma nova venda a este Cliente
	 * @param venda Venda a adicionar
	 */
	public void add(Venda v) {
		String produto = v.getProduto();
		int quantidade = v.getUnidades();
		int mes = v.getMes();
		ProductUnit pu;

		this.quantidade[mes] += quantidade;

		pu = produtos.get(produto);
		pu.add(v);
		produtos.put(produto, pu);
		
	}
	
}
