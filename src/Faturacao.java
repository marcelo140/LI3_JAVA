import java.util.*;
import java.io.Serializable;

/**
 * A Faturação relaciona os produtos e as vendas, permitindo obter eficientemente
 * dados como quantidades vendidas, números de vendas, produtos vendidos, etc
 */
public class Faturacao implements Serializable {
	private static final int MESES = 12;
	private static final int LETRAS = 26;

  	// Catálogo onde cada produto possui uma marca, identificando se este foi, ou não,
  	// comprado
    private CatalogMap<String, Marked> produtos;
 	
	// Número de produtos distintos que foram comprados
    private int produtosComprados;

	// Número de filiais
    private int filiais;

	// Número de transações em que o produto foi vendido a 0.0
    private int vendasZero;
   
	// Número de transações efetuadas em cada mês 
    private int[] numeroCompras;

	// Montante faturado em cada mês
    private double[][] fat;

	// Quantidade de produtos vendidos em cada mês
    private int[][] quant;
   
	/**
 	 * Cria uma nova instância de Faturacao com o número de filiais dado
 	 * @param nFiliais Número de filias
 	 */
    public Faturacao(int nFiliais){
		produtos = new CatalogMap<>(LETRAS);
        
		numeroCompras = new int[MESES];
        quant         = new int[MESES][nFiliais];    
        fat           = new double[MESES][nFiliais];

		vendasZero = 0;
		produtosComprados = 0;
        filiais = nFiliais;
    }
   
	/**
 	 * Cria uma nova instância de Faturacao a partir da faturação dada
 	 * @param f Faturação a ser copiada
 	 */
    public Faturacao(Faturacao f){
        produtos = f.getProdutos();

        numeroCompras = f.getNumCompras();
        quant         = f.getQuant();
        fat           = f.getFat();
        
        vendasZero = f.getVendasZero();
		produtosComprados = f.getProdutosComprados();
        filiais = f.getFiliais();
    }

	/**
 	 * Obtém uma cópia do mapeamento de produtos
 	 * @return cópia do mapeamento dos produtos
 	 */
    private CatalogMap<String, Marked> getProdutos() {
		CatalogMap<String, Marked> catalog = new CatalogMap<>(LETRAS);

		for(int i = 0; i < LETRAS; i++) {
			final int index = i;
			produtos.get(i).forEach( (k,v) -> { catalog.put(index, k, v.clone()); } );
		}

        return catalog;
    }

	/**
 	 * Obtém número de produtos comprados
 	 * @return número de produtos comprados
 	 */	
    public int getProdutosComprados() {
        return produtosComprados;   
    }
   
	/**
 	 * Obtém número de filiais
 	 * @return número de filiais
 	 */ 
    public int getFiliais() {
        return filiais;   
    }
    
	/**
 	 * Obtém número de transações em que o produto foi vendido a 0.0
 	 * @return número de transações
 	 */
    public int getVendasZero() {
        return vendasZero;
    }
   
	/**
 	 * Obtém uma cópia do número de transações efetuadas em cada mês
 	 * @return cópia do número de transações
 	 */ 
    private int[] getNumCompras() {
        return numeroCompras.clone();   
    }
   
	/**
 	 * Obtém cópia dos montantes faturados ao longo do ano, filial a filial
 	 * @return cópia dos montantes faturados
 	 */ 
    private double[][] getFat() {
		if (fat == null)
			return null;

		double[][] res = new double[fat.length][];
		for(int i = 0; i < fat.length; i++)
			res[i] = fat[i].clone();

        return res;
    }
   
	/**
 	 * Obtém cópia das quantidades vendidas ao longo do ano, filial a filial
 	 * @return cópia das quantidades vendidas
 	 */
    private int[][] getQuant(){
		if (quant == null)
			return null;

		int[][] res = new int[quant.length][];
		for(int i = 0; i < fat.length; i++)
			res[i] = quant[i].clone();

        return res;
    }
   
	/**
 	 * Obtém número de compras efetuadas num dado mês 
 	 * @param mes mês em que as compras foram efetuadas
 	 * @return número de compras
 	 */
    public int getNumCompras(int mes) {
        return numeroCompras[mes];
    }
   
	/**
 	 * Obtém montante faturado num dado mês
 	 * @param mes mês em que o montante foi faturado
 	 * @return montante faturado
 	 */	 
    public double getFaturacaoMes(int mes) {
        double soma = 0;
        for(int i = 0; i < filiais; i++)
            soma += fat[mes][i];

        return soma;
    }
   
	/**
 	 * Obtém quantidade vendida num dado mês
 	 * @param mes mês em que ocorreram as vendas
 	 * @return número de vendas
 	 * @throws InvalidMonthException mês inválido
 	 */ 
    public int getQuantidadeMes(int mes) throws InvalidMonthException {
        if (mes < 0 || mes > 11)
			 throw new InvalidMonthException ("Mês inválido");

        int soma = 0;
        for(int i = 0; i < filiais; i++)
           soma += quant[mes][i];
 
        return soma;
    }
   
	/**
 	 * Obter o montante faturado numa dada filial
 	 * @param filial filial em que ocorreram as vendas
 	 * @return montante faturado
 	 * @throws InvalidBranchException filial inválida
 	 */ 
    public double getFaturacaoFilial(int filial) throws InvalidBranchException {
        if (filial < 0 || filial >= filiais) 
			throw new InvalidBranchException ("Filial inválida");

        double soma = 0;
        for (int m = 0; m < MESES; m++)
            soma += fat[m][filial];

        return soma;
    }
   
	/**
 	 * Obtém quantidades vendidas numa dada filial
 	 * @param filial que ocorreram as transações
 	 * @return quantidade vendida
 	 * @throws InvalidBranchException filial inválida
 	 */ 
    public int getQuantidadeFilial(int filial) throws InvalidBranchException {
		if (filial < 0 || filial >= filiais)
			throw new InvalidBranchException ("Filial inválida");

        int soma = 0;
        for(int m = 0; m < MESES; m++)
            soma += quant[m][filial];
        
        return soma;
    }
   
	/**
 	 * Obtém montante faturado durante um dado mês, numa dada filial
 	 * @param mes mês em que ocorreram as transações
 	 * @param filial filial em que ocorreram as transações
 	 * @return montante faturado
 	 * @throws InvalidMonthException mês inválido
 	 * @throws InvalidBranchException filial inválida
 	 */ 
    public double getFaturacao(int mes, int filial) 
							throws InvalidMonthException, InvalidBranchException{

        if (mes < 0 || mes > 11) 
			throw new InvalidMonthException ("Mês inválido");

        if (filial < 0 || filial >= filiais) 
			throw new InvalidBranchException ("Filial inválida");

        return fat[mes][filial];
    }
    
	/**
 	 * Obter quantidades vendidas durante um dado mês, numa dada filial
 	 * @param mes mês em que foram efetuadas as transações
 	 * @param filial filial em que foram efetuadas as transações
 	 * @return quantidades vendidas
 	 * @throws InvalidMonthException mês inválido
 	 * @throws InvalidBranchException filial inválida
 	 */
    public int getQuantidade(int mes,int filial) 
							throws InvalidMonthException, InvalidBranchException{

        if (mes < 0 || mes > 11) 
			throw new InvalidMonthException ("Mês inválido");

        if (filial < 0 || filial > filiais) 
			throw new InvalidBranchException ("Filial inválida");

        return quant[mes][filial];
    }
      
	/**
 	 * Obtém o total faturado
 	 * @return montante faturado
 	 */  
	public double getFaturacaoTotal(){
        double soma= 0;

        for (int m = 0 ; m < 12 ; m++) 
        	for(int f=0 ; f < filiais ; f++){
                soma += fat[m][f];
        }

		return soma;
    }
   
	/**
 	 * Obtém as quantidades vendidas
 	 * @return quantidades vendidas
 	 */ 
    public int getQuantidadeTotal(){
        int soma= 0;
        
		for(int f = 0 ; f < filiais ; f++){
            for (int m = 0 ; m < 12 ; m++) 
                soma += quant [m][f];
        }

        return soma;
    }
      
	/**
 	 * Obtém uma lista de todos os produtos que foram comprados
 	 * @return lista dos produtos comprados
 	 */  
    public List<String> getListaComprados() {
        List<String> lista = new ArrayList<String>();
		int i = 0;

		for (i = 0; i < LETRAS; i++) {
			produtos.get(i)
			        .forEach( (k, v) -> { if (v.isMarked()) lista.add(k); } );
		}

        return lista;
    }

	/**
 	 * Obtém uma lista de todos os produtos que não foram comprados
 	 * @return lista dos produtos não comprados
 	 */
    public List<String> getListaNaoComprados() {
        List<String > lista = new ArrayList<String>();
 
        for (int i = 0; i < LETRAS; i++) {
			produtos.get(i)
			        .forEach( (k,v) -> { if (!v.isMarked()) lista.add(k); } );
		}

        return lista;
   }
    
	/**
 	 * Adiciona um produto ao catálogo
 	 * @param produto produto a ser adicionado
 	 */
    public void addProduto(String produto){
        produtos.put(produto.charAt(0)-'A', produto, new Marked(false));
    }
   
	/**
 	 * Adiciona todos os dados de uma transação à faturação
 	 * @param v dados da transação
 	 */ 
    public void addSale(Venda v){
        int mes = v.getMes();
        int filial = v.getFilial();
   		String produto = v.getProduto();
 
	    fat[mes][filial] += v.getPreco() * v.getUnidades();
        quant[mes][filial] += v.getUnidades();
        numeroCompras[mes]++;

		Marked m = produtos.get(produto.charAt(0) - 'A', produto);
        if (!m.isMarked()) {
            produtosComprados++;
			m.negate();
        }

        if (v.getPreco() == 0.0)
			 vendasZero ++;
    }

	/**
 	 * Remove todos os dados guardados
 	 */
	public void clear() {
		produtos.clear();
		produtosComprados = 0;
		vendasZero = 0;
		Arrays.fill(numeroCompras, 0);

		for(int i = 0; i < MESES; i++) {
			Arrays.fill(fat[i], 0.0);
			Arrays.fill(quant[i], 0);
		}
	}

	/**
 	 * Cria uma cópia desta instância
 	 * @return cópia
 	 */
    public Faturacao clone() {
        return new Faturacao(this);
    }
    
	/**
 	 * Compara esta instância com o objeto dado
 	 * @param o objeto a ser comprado
 	 * @return true se os objetos tiverem a mesma informação
 	 */
    public boolean equals(Object o) {
        if(o==this)
            return true;

        if(o==null || o.getClass() != this.getClass())
            return false;

        Faturacao f = (Faturacao) o;

        return f.getProdutos().equals(produtos) &&
               f.getProdutosComprados() == produtosComprados &&
               f.getFiliais() == filiais &&
               f.getVendasZero() == vendasZero &&
               f.getNumCompras().equals(numeroCompras) &&
               f.getFat().equals(fat) &&
               f.getQuant().equals(quant);
    }
    
	/**
 	 * Converte todos os dados da Faturação para uma String
 	 * @return string que descreve a Faturação
 	 */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de todos os produtos do hipermercado ").append(produtos).append("\n");
        sb.append("Número de produtos comprados: ").append(produtosComprados).append("\n");
        sb.append("Número de filiais: ").append(filiais).append("\n");
        sb.append("Número de vendas a preço zero: ").append(vendasZero).append("\n");
        sb.append("Número de compras por mês: ").append(numeroCompras).append("\n");
        sb.append("Faturação por mês e filial: ").append(fat).append("\n");
        sb.append("Quantidades por mês e filial: ").append(quant).append("\n");
        return sb.toString();
    }
    
	/**
 	 * Obtém um código hash para esta Faturação
 	 * @return hash
 	 */
    public int hashCode() {
        int hash = 7;

        hash = 31*hash + produtos.hashCode();
        hash = 31*hash + produtosComprados;
        hash = 31*hash + filiais;
        hash = 31*hash + vendasZero;
        hash = 31*hash + numeroCompras.hashCode();
        hash = 31*hash + fat.hashCode();
        hash = 31*hash + quant.hashCode();

        return hash;
    }
}





