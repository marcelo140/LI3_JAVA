import java.util.*;
/**
 * Classe que representa Client.
 * Contém o total quantidade de todos os meses, bem como
 * a quantidade total e faturado de cada mes para cada produto
 */
public class Client {

	private final int MESES = 12;
	private HashMap<String, ProductUnit> produtos;
	private boolean comprou;
	//private double gastosTotal ???
	private int[] comprasRealizadas;
	private double[] gastos;


	/**
     * Construtor padrão
     */
    public Client() {
		produtos = new HashMap<>();
		comprou = false;
		comprasRealizadas = new int[MESES];
		gastos = new double[MESES];
    }

	/**
	 * Construtor por parametros
	 */
	public Client(Map<String, ProductUnit> produtos, boolean comprou, int[] comprasRealizadas, double[] gastos) {
		this.produtos = new HashMap<String, ProductUnit>(produtos);
		this.comprou = comprou;
		this.comprasRealizadas = comprasRealizadas;
		this.gastos= gastos;

	}

	/**
	 * Construtor por cópia
	 */
	public Client(Client c) {
		produtos = new HashMap(c.getProdutos());
		comprou = c.comprou();
		comprasRealizadas = c.getComprasRealizadas();
		gastos = c.getGastos();
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
	 * Retorna true se e só se o cliente comprou algo no mês dado.
	 * @param mes mês para o qual vai verificar se comprou
	 * @return true se e só se o cliente comprou algo no mês dado
	 * @throws InvalidMonthException caso o mês dado não seja válido
	 */
	public boolean comprou(int mes) throws InvalidMonthException {
		if (mes < 0 || mes > 11)
			throw new InvalidMonthException("Mês inválido!");

		return comprasRealizadas[mes] == 0;
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

	public double getGastos(int mes) throws InvalidMonthException{
		if (mes<0 || mes>= MESES) throw new InvalidMonthException ("Mês inválido.");
		return gastos[mes];
	}

	public int getCompras (int mes) throws InvalidMonthException{
		if (mes<0 || mes>= MESES) throw new InvalidMonthException ("Mês inválido.");
		return comprasRealizadas[mes];
	}

	/**
	 * Adiciona uma nova venda a este Cliente
	 * @param venda Venda a adicionar
	 */
	public void add(Venda v) {
		String produto = v.getProduto();
		int mes = v.getMes();
		ProductUnit pu;

		this.comprasRealizadas[mes] += v.getUnidades();

		pu = produtos.get(produto);
		if (pu == null) pu = new ProductUnit();
		pu.add(v);
		produtos.put(produto, pu);

	}

	public Client clone(){
		return new Client(this);
	}

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

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Produtos comprados pelo cliente: ").append(produtos).append("\n");
        sb.append("Comprou? ").append(comprou).append("\n");
        sb.append("Número de compras realizadas cada mês: ").append(comprasRealizadas).append("\n");
        sb.append("Gastos em cada mês ").append(gastos).append("\n");
        return sb.toString();
    }

	public int hashCode() {
	    ArrayList<Object> lista = new ArrayList<>();

	    lista.add(produtos);
	    lista.add(comprou);
	    lista.add(comprasRealizadas);
	    lista.add(gastos);

	    return lista.hashCode();
    }

}
