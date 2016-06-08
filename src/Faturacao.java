import java.lang.String;
import java.lang.Object;
import java.util.*;
import java.lang.*;
import java.io.Serializable;


/**
 * Write a description of class Faturacao here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
        this.produtos = c.getProdutos();
        this.produtosComprados=c.getProdutosComprados();
        this.filiais=c.getFiliais();
        this.vendasZero=c.getVendasZero();
        this.numeroCompras=c.getNumCompras();
        this.fat=c.getFat();
        this.quant=c.getQuant();
    }
    
    public CatalogMap<String, Boolean> getProdutos() {
        return null;
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
        fat[v.getMes()][v.getFilial()] = v.getPreco() * v.getUnidades();
        quant[v.getMes()][v.getFilial()] += v.getUnidades();
        numeroCompras[v.getMes()] += v.getUnidades();
        produtosComprados++;
        produtos.put((v.getProduto().charAt(0)) - 'A', v.getProduto() , true);
    }
    
    public int getNumCompras(int mes) {
        return numeroCompras[mes];
    }
    
    
    public double getFaturacaoMes(int mes) {
        double soma= 0;

        for(int i = 0; i < filiais; i++)
            soma += fat[mes][i];

        return soma;
        
    }
    
    public int getQuantidadeMes(int mes) {
        int soma = 0;

        for(int i = 0; i < filiais; i++)
           soma += quant[mes][i];
 
        return soma;
        
    }
    
    public double getFaturacaoFilial(int filial) {
        double soma = 0;

        for (int m = 0; m < 12; m++)
            soma += fat[m][filial];

        return soma;
    }
    
    public int getQuantidadeFilial(int filial) {
        int soma = 0;

        for(int m = 0; m < 12; m++)
            soma += quant[m][filial];
        
        return soma;
    }
    
    public double getFaturacao(int mes, int filial){
        return fat[mes][filial];
    }
    
    
    public int getQuantidade(int mes, int filial){
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
        
        
        /**
         * public CatalogMap<String, Boolean> getListaComprados() {}
         */
    
    
    
    public void addProduto(String nome){
        produtos.put(nome.charAt(0)-'A' , nome , false);
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
    
    
    /**public int hashCode() {
        int hash = 7;

        hash = 31*hash + this.produtos.hashCode();
        hash = 31*hash + this.produtosComprados.hashCode();
        hash = 31*hash + this.filiais.hashCode();
        hash = 31*hash + this.vendasZero.hashCode();
        hash = 31*hash + this.produtos.hashCode();

        return hash;
    }*/
}





