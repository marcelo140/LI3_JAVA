import java.lang.String;
import java.lang.Object;
import java.util.*;
import java.lang.*;
import java.io.Serializable;


/**
 * Write a description of class Faturacao here.
 * 
 * @author (Grupo 85) 
 * @version (junho 2016)
 */
public class Faturacao implements Serializable
{
    /**
     * String = produto, Boolean = comprado? Ordenado por ordem alfabetica
     */
    private CatalogMap<String, Boolean> produtos;

    private int produtosComprados;

    private int filiais;

    private int vendasZero;
    
    private int[] numeroCompras;

    private double[][] fat;

    private int[][] quant;
    
    
    public Faturacao() {
        produtos = new CatalogMap<String, Boolean> ();
        filiais = 1;
        
		numeroCompras = new int[12];
        fat = new double[12][filiais];
        quant = new int[12][filiais];    
        
		produtosComprados = 0;
        vendasZero = 0;
    }
    
    public Faturacao(CatalogMap<String, Boolean> produtos, int filiais){
        this.produtos = produtos.clone();
        this.filiais = filiais;
        
		numeroCompras = new int[12];
        quant         = new int[12][filiais];    
        fat           = new double[12][filiais];

		produtosComprados = 0;
		vendasZero = 0;
    }
    
    
    public Faturacao(CatalogMap<String, Boolean> produtos, int filiais, int prodsComprados, int vendasZero, int[] numCompras, double[][]fat, int[][]quant){
        this.produtos = produtos.clone();
        this.filiais = filiais;
        this.produtosComprados = prodsComprados;
        this.vendasZero = vendasZero;
        this.numeroCompras = numCompras;
        this.fat = fat;
        this.quant = quant;
        
    }
    
    public Faturacao(Faturacao c){
        produtos = c.getProdutos();
        produtosComprados=c.getProdutosComprados();
        filiais=c.getFiliais();
        vendasZero=c.getVendasZero();
        numeroCompras=c.getNumCompras();
        fat=c.getFat();
        quant=c.getQuant();
    }
    
    public CatalogMap<String, Boolean> getProdutos() {
        return produtos.clone();
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
    
    public int[] getNumCompras() {
        return numeroCompras;   
    }
    
    public double[][] getFat() {
        return fat;
    }
    
    public int[][] getQuant(){
        return quant;
    }
    
    public void addSale(Venda v){
		int mes = v.getMes();
		int filial = v.getFilial();
		int unidades = v.getUnidades();

        fat[mes][filial] = v.getPreco() * unidades;
        quant[mes][filial] += unidades;

        numeroCompras[mes]++;
        produtosComprados += v.getUnidades();

        produtos.put((v.getProduto().charAt(0)) - 'A', v.getProduto() , TRUE);
    }

    
    public int getNumCompras(int mes) throws InvalidMonthException{
        if (mes<0 || mes>11) throw new InvalidMonthException ("Mês inválido.");
        return numeroCompras[mes];
    }
    
    
<<<<<<< HEAD
    public double getFaturacaoMes(int mes) throws InvalidMonthException{
        if (mes<0 || mes>11) throw new InvalidMonthException ("Mês inválido.");
        double soma = 0;
        for(int i = 0; i < filiais; i++) {
=======
    public double getFaturacaoMes(int mes) {
        double soma= 0;

        for(int i = 0; i < filiais; i++)
>>>>>>> b1095723422dbc7046593378d943b190920e0949
            soma += fat[mes][i];

        return soma;
    }
    
    public int getQuantidadeMes(int mes) throws InvalidMonthException {
        if (mes<0 || mes>11) throw new InvalidMonthException ("Mês inválido.");
        int soma = 0;

        for(int i = 0; i < filiais; i++)
           soma += quant[mes][i];
 
        return soma;
    }
    
    public double getFaturacaoFilial(int filial) throws InvalidBranchException {
        if (filial < 0 || filial >= filiais) throw new InvalidBranchException ("Filial inválida.");
        double soma = 0;

        for (int m = 0; m < 12; m++)
            soma += fat[m][filial];

        return soma;
    }
    
<<<<<<< HEAD
    public int getQuantidadeFilial(int filial) throws InvalidBranchException{
        if (filial < 0 || filial >= filiais) throw new InvalidBranchException ("Filial inválida.");
        int soma = 0 ;
        for(int m=0; m<12; m++) {
=======
    public int getQuantidadeFilial(int filial) {
        int soma = 0;

        for(int m = 0; m < 12; m++)
>>>>>>> b1095723422dbc7046593378d943b190920e0949
            soma += quant[m][filial];
        
        return soma;
    }
    
    public double getFaturacao(int mes, int filial) throws InvalidMonthException, InvalidBranchException{
        if (mes < 0 || mes > 11) throw new InvalidMonthException ("Mês inválido.");
        if (filial < 0 || filial >= filiais) throw new InvalidBranchException ("Filial inválida.");
        return fat[mes][filial];
    }
    
    
<<<<<<< HEAD
    public int getQuantidade(int mes,int filial) throws InvalidMonthException, InvalidBranchException{
        if (mes < 0 || mes > 11) throw new InvalidMonthException ("Mês inválido.");
        if (filial < 0 || filial >= filiais) throw new InvalidBranchException ("Filial inválida.");
=======
    public int getQuantidade(int mes, int filial){
>>>>>>> b1095723422dbc7046593378d943b190920e0949
        return quant[mes][filial];
    }
    
    public double getFaturacaoTotal(){
        double soma= 0;

        for(int f=0 ; f < filiais ; f++){
            for (int m = 0 ; m < 12 ; m++) 
                soma += fat [m][f];
        }

		return soma;
    }
    
    public int getQuantidadeTotal(){
        int soma= 0;
        
		for(int f = 0 ; f < filiais ; f++){
            for (int m = 0 ; m < 12 ; m++) 
                soma += quant [m][f];
        }

        return soma;
    }
        
        
        
    public List<String> getListaComprados() {
        List<String > lista = new ArrayList<String>();

        for (TreeMap t : produtos.getMaps()) {
            for(String prod : t.keySet())
                if(t.get(prod) == TRUE) lista.add(prod);
         }
         return lista;
    }

    public List<String> getListaNaoComprados() {
        List<String > lista = new ArrayList<String>();

        for (TreeMap t : this.produtos.getMaps()) {
            for(String prod : t.keySet())
                if(t.get(prod) == FALSE) lista.add(prod);
         }
         return lista;
        }
    

    public void addProduto(String nome){
        produtos.put(nome.charAt(0)-'A' , nome , FALSE);
    }
    
<<<<<<< HEAD
    public void addSale(Venda v){
        int mes = v.getMes();
        int filial = v.getFilial();
        fat[mes][filial] = (v.getPreco()) * (v.getUnidades());
        quant[mes][filial] += v.getUnidades();
        numeroCompras[mes] ++;
        if (produtos.get(v.getProduto().charAt(0) - 'A', v.getProduto()) == FALSE) {
            produtosComprados ++;
            produtos.put((v.getProduto().charAt(0)) - 'A', v.getProduto() , TRUE);
        } 
        if (v.getPreco() == 0.0) vendasZero ++;
    }


=======
>>>>>>> b1095723422dbc7046593378d943b190920e0949
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





