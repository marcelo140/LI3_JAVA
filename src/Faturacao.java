import java.util.*;
import java.io.Serializable;

/**
 * Write a description of class Faturacao here.
 * 
 * @author (Grupo 85) 
 * @version (junho 2016)
 */
public class Faturacao implements Serializable
{
	private static final int meses = 12;
	private static final int letras = 26;

    /**
     * String = produto, Marked = comprado? Ordenado por ordem alfabetica
     */
    private CatalogMap<String, Marked> produtos;

    private int produtosComprados;

    private int filiais;

    private int vendasZero;
    
    private int[] numeroCompras;

    private double[][] fat;

    private int[][] quant;
    
    public Faturacao(int filiais){
        this.produtos = new CatalogMap<String, Marked>();
        this.filiais = filiais;
        
		numeroCompras = new int[meses];
        quant         = new int[meses][filiais];    
        fat           = new double[meses][filiais];

		produtosComprados = 0;
		vendasZero = 0;
    }
    
    public Faturacao(Faturacao c){
        produtos = c.getProdutos();
        filiais = c.getFiliais();

        numeroCompras = c.getNumCompras();
        quant         = c.getQuant();
        fat           = c.getFat();
        
		produtosComprados = c.getProdutosComprados();
        vendasZero = c.getVendasZero();
    }
    
    private CatalogMap<String, Marked> getProdutos() {
        return produtos;
    }
    
    public int getProdutosComprados() {
        return produtosComprados;   
    }
    
    public int getFiliais() {
        return filiais;   
    }
    
    public int getVendasZero() {
        return vendasZero;
    }
    
    private int[] getNumCompras() {
        return numeroCompras.clone();   
    }
    
    private double[][] getFat() {
        return fat.clone();
    }
    
    private int[][] getQuant(){
        return quant.clone();
    }
    
    public int getNumCompras(int mes) throws InvalidMonthException {
        if (mes < 0 || mes > 11) 
			throw new InvalidMonthException ("Mês inválido");

        return numeroCompras[mes];
    }
    
    public double getFaturacaoMes(int mes) throws InvalidMonthException{
        if (mes < 0 || mes > 11) 
			throw new InvalidMonthException ("Mês inválido");

        double soma = 0;
        for(int i = 0; i < filiais; i++)
            soma += fat[mes][i];

        return soma;
    }
    
    public int getQuantidadeMes(int mes) throws InvalidMonthException {
        if (mes < 0 || mes > 11)
			 throw new InvalidMonthException ("Mês inválido");

        int soma = 0;
        for(int i = 0; i < filiais; i++)
           soma += quant[mes][i];
 
        return soma;
    }
    
    public double getFaturacaoFilial(int filial) throws InvalidBranchException {
        if (filial < 0 || filial >= filiais) 
			throw new InvalidBranchException ("Filial inválida");

        double soma = 0;
        for (int m = 0; m < meses; m++)
            soma += fat[m][filial];

        return soma;
    }
    
    public int getQuantidadeFilial(int filial) throws InvalidBranchException {
		if (filial < 0 || filial >= filiais)
			throw new InvalidBranchException ("Filial inválida");

        int soma = 0;
        for(int m = 0; m < meses; m++)
            soma += quant[m][filial];
        
        return soma;
    }
    
    public double getFaturacao(int mes, int filial) throws InvalidMonthException, InvalidBranchException{
        if (mes < 0 || mes > 11) 
			throw new InvalidMonthException ("Mês inválido");

        if (filial < 0 || filial >= filiais) 
			throw new InvalidBranchException ("Filial inválida");

        return fat[mes][filial];
    }
    
    
    public int getQuantidade(int mes,int filial) throws InvalidMonthException, InvalidBranchException{
        if (mes < 0 || mes > 11) 
			throw new InvalidMonthException ("Mês inválido");

        if (filial < 0 || filial > filiais) 
			throw new InvalidBranchException ("Filial inválida");

        return quant[mes][filial];
    }
    
    public double getFaturacaoTotal(){
        double soma= 0;

        for(int f=0 ; f < filiais ; f++){
            for (int m = 0 ; m < meses ; m++) 
                soma += fat [m][f];
        }

		return soma;
    }
    
    public int getQuantidadeTotal(){
        int soma= 0;
        
		for(int f = 0 ; f < filiais ; f++){
            for (int m = 0 ; m < meses ; m++) 
                soma += quant [m][f];
        }

        return soma;
    }
        
    public List<String> getListaComprados() {
        List<String> lista = new ArrayList<String>();
		int i = 0;

		for (i = 0; i < letras; i++) {
			Map<String, Marked> tree = produtos.get(i);

			tree.forEach( (k, v) -> { if (v.isMarked()) lista.add(k); });
		}

        return lista;
    }

    public List<String> getListaNaoComprados() {
        List<String > lista = new ArrayList<String>();
 
        for (int i = 0; i < letras; i++) {
			Map<String, Marked> tree = produtos.get(i);

			tree.forEach( (k,v) -> { if (v.isMarked()) lista.add(k); });
		}

        return lista;
   }
    

    public void addProduto(String produto){
        produtos.put(produto.charAt(0)-'A', produto, new Marked(false));
    }
    
    public void addSale(Venda v){
        int mes = v.getMes();
        int filial = v.getFilial();
   		String produto = v.getProduto();
 
	    fat[mes][filial] = v.getPreco() * v.getUnidades();
        quant[mes][filial] += v.getUnidades();
        numeroCompras[mes]++;

		Marked m = produtos.get(produto.charAt(0) - 'A', produto);
        if (!m.isMarked()) {
            produtosComprados ++;
			m.negate();
        }

        if (v.getPreco() == 0.0)
			 vendasZero ++;
    }


    public Faturacao clone() {
        return new Faturacao(this);
    }
    

    public boolean equals(Object o) {
        if(o==this)
            return true;

        if(o==null || o.getClass() != this.getClass())
            return false;

        Faturacao f = (Faturacao) o;

        return f.getProdutos().equals(produtos) &&
               f.getProdutosComprados() == this.produtosComprados &&
               f.getFiliais() == this.filiais &&
               f.getVendasZero() == this.vendasZero &&
               f.getNumCompras().equals(numeroCompras) &&
               f.getFat().equals(fat) &&
               f.getQuant().equals(quant);
    }
    

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
    

    public int hashCode() {
        int hash = 7;

        hash = 31*hash + produtos.hashCode();
        hash = 31*hash + produtosComprados;
        hash = 31*hash + filiais;
        hash = 31*hash + vendasZero;
        hash = 31*hash + this.numeroCompras.hashCode();
        hash = 31*hash + this.fat.hashCode();
        hash = 31*hash + this.quant.hashCode();

        return hash;
    }
}





