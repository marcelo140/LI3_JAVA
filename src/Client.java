import java.util.*;
import java.io.Serializable;

/**
 * Classe que representa um cliente.
 */

public class Client implements Serializable {
	private final int MESES = 12;

	private boolean comprou;
	private List<Map<String, ProductUnit>> produtos;
	private int[] comprasRealizadas;
	private double[] gastos;


	/**
     * Construtor padrão
     */
    public Client() {
		comprou = false;
		comprasRealizadas = new int[MESES];
		gastos = new double[MESES];
		
		produtos = new ArrayList<Map<String, ProductUnit>>(MESES);
		for (int i = 0; i < MESES; i++)
			produtos.add( new TreeMap<>() );
    }

	/**
	 * Construtor por parâmetros
	 */
	public Client(List<Map<String, ProductUnit>> produtos, 
	              boolean comprou, int[] comprasRealizadas, double[] gastos) {

		setProdutos(produtos);
		setComprou(comprou);
		setComprasRealizadas(comprasRealizadas);
		setGastos(gastos);
	}

	/**
	 * Construtor por cópia
	 */
	public Client(Client c) {
		produtos = c.getProdutos();
		comprou = c.comprou();
		comprasRealizadas = c.getComprasRealizadas();
		gastos = c.getGastos();
	}


	/**
	 * Retorna uma mapeamento de Produto com ProductUnit (que contém
	 * a quantidade total e o total quantidade de cada mes desse produto)
	 * @return mapeamento
	 */
	public List<Map<String, ProductUnit>> getProdutos() {
		List<Map<String, ProductUnit>> produtos = new ArrayList<Map<String, ProductUnit>>(MESES);

		for(int i = 0; i < MESES; i++) {
			Map<String, ProductUnit> mes  = new TreeMap<>();

			this.produtos.get(i).forEach( (k,v) -> { mes.put(k, v.clone()); } );
			produtos.add(mes);
		}

		return produtos;
	}

	/**
	 * Retorna true se e só se o cliente comprou algo
	 * @return true se o cliente comprou
	 */
	public boolean comprou() {
		return comprou;
	}


	/**
	 * Retorna true se e só se o cliente comprou algo no mês dado.
	 * @param mes mês para o qual vai verificar se comprou
	 * @return true se e só se o cliente comprou algo no mês dado
	 */
	public boolean comprou(int mes) {
		return comprasRealizadas[mes] == 0;
	}

	/**
	 * Retorna o total de compras realizadas cada mês deste cliente
	 * @return compras realizadas mês a mês
	 */
	public int[] getComprasRealizadas() {
		return comprasRealizadas.clone();
	}

	/**
	 * Retorna o total de gastos em cada mês deste cliente
	 * @return gastos mês a mês
	 */
	public double[] getGastos() {
		return gastos;
	}

	/**
 	 * Retorna o número de produtos comprados
 	 * @return número de produtos comprados
 	 */
	public int getNumeroProdutos(){
		return produtos.size();
	}
	
	/**
 	 * Obtém os gastos num dado mês
 	 * @param mes mês em que ocorreram os gastos
 	 * @return gastos durante o mês
 	 */
	public double getGastos(int mes) {
		return gastos[mes];
	}

	/**
 	 * Obtém o número de compras num dado mês
 	 * @param mes mês em que ocorreram as transações
 	 * @return número de compras
 	 * @throws InvalidMonthException mês inválido
 	 */
	public int getCompras (int mes) {
		return comprasRealizadas[mes];
	}

	/**
 	 * Define se o cliente comprou
 	 * @param comprou
 	 */
	private void setComprou(boolean comprou) {
		this.comprou = comprou;
	}

	/**
 	 * Define os gastos do cliente, mês a mês
 	 * @param gastos
 	 */
	private void setGastos(double[] gastos) {
		this.gastos = gastos.clone();
	}

	/**
 	 * Define as compras realizadas pelo cliente, mês a mês
 	 * @param comprasRealizadas
 	 */
	private void setComprasRealizadas(int[] comprasRealizadas) {
		this.comprasRealizadas = comprasRealizadas.clone();
	}

	/**
 	 * Define os produtos comprados pelo cliente
 	 * @param produtos
 	 */
	private void setProdutos(List<Map<String, ProductUnit>> produtos) {
		this.produtos = new ArrayList<Map<String, ProductUnit>>(MESES);

		for(int i = 0; i < MESES; i++) {
			Map<String, ProductUnit> mes  = new TreeMap<>();

			produtos.get(i).forEach( (k,v) -> { mes.put(k, v.clone()); } );
			this.produtos.add(mes);
		}
	}

	/**
	 * Adiciona os dados de uma venda a este Cliente
	 * @param v venda a adicionar
	 */
	public void add(Venda v) {
		String produto = v.getProduto();
		int mes = v.getMes();
		int unidades = v.getUnidades();
		double faturado = unidades * v.getPreco();

		comprou = true;
		gastos[mes] += faturado;
		comprasRealizadas[mes]++;

		ProductUnit pu = produtos.get(mes).get(produto);

		if (pu != null)
			pu.add(unidades, faturado);
		else {
			pu = new ProductUnit(unidades, faturado);
			produtos.get(mes).put(produto, pu);
		}
	}

	/**
 	 * Retorna um cópia deste Client
 	 * @return cópia
 	 */
	public Client clone(){
		return new Client(this);
	}

	/**
 	 * Compara este cliente com o objeto dado. Returna true casa o objeto seja um Client
 	 * que represente a mesma informação que este cliente.
 	 * @return true se representarem a mesma informação
 	 */
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (o == null || o.getClass() != this.getClass())
			return false;

		Client c = (Client) o;
		return c.getProdutos().equals(produtos) &&
			   c.comprou() == this.comprou() &&
               c.getComprasRealizadas().equals(comprasRealizadas) &&
			   c.getGastos().equals(gastos);
	}

	/**
 	 * Retorna um string que descreve este cliente
 	 * @return String que descreve o cliente
 	 */
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Produtos comprados pelo cliente: ").append(produtos).append("\n");
        sb.append("Comprou? ").append(comprou).append("\n");
        sb.append("Número de compras realizadas cada mês: ").append(comprasRealizadas).append("\n");
        sb.append("Gastos em cada mês ").append(gastos).append("\n");
        return sb.toString();
    }

	/**
 	 * Retorna uma hash para este Cliente
 	 * @return hash
 	 */
	public int hashCode() {
		return Arrays.hashCode( new Object[] {produtos, comprou, comprasRealizadas,
		                                      gastos });
    }

}
