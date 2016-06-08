import java.util.*;
/**
 * Classe que representa Client.
 * Contém o total quantidade de todos os meses, bem como
 * a quantidade total e faturado de cada mes para cada produto 
 */
public class Client {

	private final int MESES = 12;
	private int[] quantidade;
	private HashMap<String, ProductUnit> produtos;
	private boolean comprou;
	//private double gastosTotal ???
	private int[] comprasRealizadas;
	private double[] gastos;

    
	/**
     * Construtor padrão 
     */
    public Client() {
		quantidade = new int[MESES];
		produtos = new HashMap<>();
		comprou = false;
		comprasRealizadas = new int[MESES] ();
		gastos = new double[MESES];
    }

	/**
	 * Construtor por parametros
	 */
	public Client(int[] quantidade, Map<String, ProductUnit> produtos, boolean comprou, int[] comprasRealizadas, double[] gastos) {
		this.quantidade = quantidade;
		this.produtos = new HashMap<String, ProductUnit>(produtos);
		this.comprou = comprou;
		this.comprasRealizadas = comprasRealizadas;
		this.gastos= gastos;

	}

	/**
	 * Construtor por cópia
	 */
	public Client(Client c) {
		quantidade = c.getFaturado();
		produtos = c.getProdutos();
		comprou = c.comprou();
		comprasRealizadas = c.getComprasRealizadas();
		gastos = c.getGastos();	
	}

	/**
	 * Retorna as quantidades compradas de todos os meses deste cliente
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
	public boolean comprou() {
		return comprou;
	}

	/**
	 * Retorna o total de compras realizadas cada mês deste cliente
	 */
	public int[] getComprasRealizadas() {
		return comprasRealizadas;
	}

	/**
	 * Retorna o total de gastos em cada mês deste cliente
	 */
	public double[] getGastos() {
		return gastos;
	}


	public int getNumeroProdutos(){
		return produtos.size();
	}

	public double getGastos(int mes){
		return gastos[mes];
	}

	public int getCompras (int mes) {
		return comprasRealizadas[mes];
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
		if (pu == null) pu = new ProductUnit();
		pu.add(v);
		produtos.put(produto, pu);
		
	}

	public Cliente clone(){
		return new Cliente(this);
	}

	public boolean equals(Object o) {
		if (o == this)
			return true;
		
		if (o == null || o.getClass() != this.getClass())
			return false;

		Cliente c = (Cliente) o;
		return c.getFaturado().equals(quantidade) &&
               c.getProdutos().equals(produtos) &&
			   c.comprou().equals(comprou) &&
               c.getComprasRealizadas().equals(comprasRealizadas) &&
			   c.getGastos().equals(gastos);
	}

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Quantidades compradas por mês: ").append(quantidades).append("\n");
        sb.append("Produtos comprados pelo cliente: ").append(produtos).append("\n");
        sb.append("Comprou? ").append(comprou).append("\n");
        sb.append("Número de compras realizadas cada mês: ").append(comprasRealizadas).append("\n");
        sb.append("Gastos em cada mês ").append(gastos).append("\n");
        return sb.toString();
    }

	public int hashCode() {
        int hash = 7;

        hash = 31*hash + quantidade.hashCode();
        hash = 31*hash + produtos.hashCode();
        hash = 31*hash + comprou.hashCode();
        hash = 31*hash + comprasRealizadas.hashCode();
        hash = 31*hash + gastos.hashCode();
		return hash;
    }
	
}
