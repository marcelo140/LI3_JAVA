
/**
 * Cada produto terá a quantidade vendida e o total faturado em cada mês.
 * 
 */
public class ProductUnit {
    private final int MESES = 12; 
	
	private int[] quantidade; //Quantidade total de cada mes
	private double[] faturado; //Total faturado de cada mes

    /**
     * Construtor padrão 
     */
    public ProductUnit() {
		quantidade = new int[MESES];
		faturado   = new double[MESES];
    }

	/**
	 * Construtor por parametros
	 */
	public ProductUnit(int[] quantidade, double[] faturado) {
		this.quantidade = quantidade;
		this.faturado = faturado;
	}

	/**
	 * Construtor por cópia
	 */
	public ProductUnit(ProductUnit p) {
		quantidade = p.getQuantidade();
		faturado = p.getFaturado();
	}

	/**
	 * Retorna a quantidade total vendida em cada mes
	 * @return Quantidade total vendida em cada mes
	 */
	public int[] getQuantidade() {
		return quantidade;
	}

	/**
	 * Retorna o total faturado em cada mes
	 * @return Total faturado em cada mes
	 */
	public double[] getFaturado() {
		return faturado;
	}

	/**
	 * Adiciona uma venda ao ProductUnit
	 * @param v Venda a adicionar
	 */
	public void add(Venda v) {
		int mes = v.getMes();
		
		faturado[mes] += v.getUnidades() * v.getPreco();	
		quantidade[mes] += v.getUnidades();
	} 

	/**
	 * Retorna uma copia desta instancia de ProductUnit.
	 * @return um clone desta instancia de ProductUnit
	 */
	public ProductUnit clone() {
		return new ProductUnit(this);
	}

	/**
	 * Cria uma string de acordo com as variáveis deste objeto
	 * @return String que representa esta instancia de ProductUnit
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append("ProductUnit:\n");

		str.append("Quantidade:\n");
		for (int i = 0; i < 12 ; i++)
			str.append("\tMês " + i + ": " + quantidade[i] + "\n");
		
		str.append("Faturado:\n");
		for (int i = 0; i < 12 ; i++)
			str.append("\tMês " + i + ": " + faturado[i] + "\n");
		
		return str.toString();
	}

	/**
	 * Compara um dado objeto com esta instancia de ProductUnit.
	 * @param o Objeto a ser comparado com este ProductUnit
	 * @return True caso o objeto dado seja igual a este, 
	 * false caso contrário
	 */
	public boolean equals(Object o) {
		if (o == this) return true;
		if ( o == null || o.getClass() != this.getClass() ) return false;

		ProductUnit pu = (ProductUnit) o;
		return (pu.getQuantidade() == this.getQuantidade() &&
				pu.getFaturado() == this.getFaturado());
	}

	/**
	 * Devolve um hash único para esta instancia de ProductUnit
	 * @return hash único para esta instancia
	 */
	public int hashCode() {
		int hash = 7;

		hash = 31*hash + quantidade.hashCode();
		hash = 31*hash + faturado.hashCode();

		return hash;
	}
}
