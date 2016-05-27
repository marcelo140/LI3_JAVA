
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
	public void addVenda(Venda v) {
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

	//TODO implementar compares
}
