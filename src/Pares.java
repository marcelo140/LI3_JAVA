import java.lang.*;
import java.util.*;
import java.util.Set;
import java.util.List;
import java.util.TreeSet;
import java.util.ArrayList;

public abstract class Pares<K,V> implements Serializable{
    private TreeMap<K,V> pares;

    /**
     * Constructs an empty catalog.
     * @param tamanho
     */
    public Pares() {
        pares = new TreeMap<K,V>());
    }


    /**
     * 
     */
    public Pares(TreeMap<K,V> pares) {
        this.pares = new TreeMap<>(pares);
    }

    public Pares(Pares p) {
        pares = p.getPares();
    }

    public TreeMap<K,V> getPares(){
        return new TreeMap<K,V>(pares)
    }

    /**
     * 
     */
    public abstract Pares clone();


    /**
     * Função que adiciona uma consulta à lista de consultas
     */
    public void addPar(Object key , Object value){
        pares.put(key,value);
    }
    
    
    /**
     * Função que permite verificar se dois objetos são iguais.
     */
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Pares o = (Pares) obj;
        return o.getPares().equals(pares);
    }
    
 
     public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pares: '").append(pares).append("'\n");
        
        return sb.toString();
    }

}
