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
        this.produtos=new CatalogMap<String, Boolean> ();
        this.produtosComprados=0;
        this.filiais=1;
        this.vendasZero=0;
        this.numeroCompras=new int[12];
        this.fat= new double[12][3];
        this.quant= new int[12][3];    
    }
    
    public Faturacao(CatalogMap<String, Boolean> prod,int filiais){
        this.produtos=prod;
        this.filiais=filiais;
        
    }
    
    
    public Faturacao(CatalogMap<String, Boolean> prod,int filiais, int prodsComprados, int vendasZero, int[] numCompras, double[][]fat, int[][]quant){
        this.produtos=prod;
        this.filiais=filiais;
        this.produtosComprados= prodsComprados;
        this.vendasZero= vendasZero;
        this.numeroCompras=numCompras;
        this.fat=fat;
        this.quant=quant;
        
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
        return this.produtosComprados;   
    }
    
    public int getFiliais() {
        return this.filiais;   
    }
    
    public int getVendasZero() {
        return this.vendasZero;
    }
    
    public int[] getNumCompras() {
        return this.numeroCompras;   
    }
    
    public double[][] getFat() {
        return this.fat;
    }
    
    public int[][] getQuant(){
        return this.quant;
    }
    
    
    public void addSale(Venda v){
        this.fat[v.getMes()][v.getFilial()] = (v.getPreco()) * (v.getUnidades());
        this.quant[v.getMes()][v.getFilial()] += v.getUnidades();
        this.numeroCompras[v.getMes()] += v.getUnidades();
        this.produtosComprados ++;
        this.produtos.put((v.getProduto().charAt(0)) - 'A', v.getProduto() , true);
     
    }
    
    public int getNumCompras(int mes) {
        return numeroCompras[mes];
    }
    
    
    public double getFaturacaoMes(int mes) {
        double soma= 0;
        for(int i = 0; i < this.filiais; i++) {
            soma += fat[mes][i];
        }
        return soma;
        
    }
    
    public int getQuantidadeMes(int mes) {
        int soma = 0;
        for(int i=0; i<this.filiais;i++) {
           soma += quant[mes][i];
        }
        return soma;
        
    }
    
    public double getFaturacaoFilial(int filial) {
        double soma = 0;
        for (int m = 0; m < 12; m++) {
            soma += fat[m][filial];
        }
        return soma;
    }
    
    public int getQuantidadeFilial(int filial) {
        int soma = 0 ;
        for(int m=0; m<12; m++) {
            soma += quant[m][filial];
        }
        
        return soma;
    }
    
    public double getFaturacao(int mes, int filial){
        return fat[mes][filial];
    }
    
    
    public int getQuantidade(int mes,int filial){
        return quant[mes][filial];
    }
    
    public double getFaturacaoTotal(){
        double soma= 0;
        for(int f = 0 ; f < filiais ; f++){
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
        this.produtos.put(nome.charAt(0)-'A' , nome , false);
    }
    
    public Faturacao clone() {
        return new Faturacao(this);
    }
    
    public boolean equals(Object o) {
        if(o==this) {
            return true;
        }
        if(o==null || o.getClass() != this.getClass()) {
            return false;
        }
        Faturacao f = (Faturacao) o;
        return f.getProdutos().equals(produtos) 
                && f.getProdutosComprados()== this.produtosComprados
                && f.getFiliais() == this.filiais
                && f.getVendasZero() == this.vendasZero
                && f.getNumCompras().equals(numeroCompras)
                && f.getFat().equals(fat)
                && f.getQuant().equals(quant);
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





