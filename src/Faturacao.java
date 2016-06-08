import java.lang.String;
import java.lang.Object;
import java.util.*;
import java.lang.*;


/**
 * Write a description of class Faturacao here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Faturacao
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
    
    public double [] getFaturacaoMes(int mes) {
        return null;
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


