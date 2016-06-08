 /**
 *
 */

import java.util.*;
import java.util.Set;
import java.util.List;
import java.util.TreeSet;
import java.util.ArrayList;

public class CatalogMap<K,V> implements Map<K,V> {
    private List<HashMap<K,V>> cat;
    private int size;

    /**
     * Constructs an empty catalog with the specified capacity.
     * @param tamanho
     */
    public CatalogMap(int size) {
        this.size = size;
        cat = new ArrayList<HashMap<K,V>>(size);

        for (int i = 0 ; i < size; i++)
            cat.add(new HashMap<K,V>());

    }

    /**
     * Constructs an empty catalog with capacity ten.
     */
    public CatalogMap() {
        size = 0;
        cat = new ArrayList<HashMap<K,V>>();

        for (int i = 0 ; i < size; i++)
            cat.add(new HashMap<K,V>());
    }

    /**
     * Construtor por cópia
     */
    public CatalogMap(CatalogMap<K,V> c) {
        size = c.size();
        cat = new ArrayList<HashMap<K,V>>(size);

        for (int i = 0 ; i < size; i++)
            cat.add(new HashMap<K,V>(c.get(i)));
    }

    /**
     * Devolve um map de um dado índice do catálogo
     * @param index Índice
     * @throws IndexOutOfBoundsException Se o índice não está dentro do catálogo
     */
    public Map<K,V> get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        return new HashMap<K,V> (cat.get(index));
    }

    /**
     * Devolve o valor associado à chave dado no mapeamento indicado pelo índice
     * @param index índice do mapeamento
     * @param key Chave cujo valor associado deve ser retornado
     * @return O valor para o qual a chave dada é mapeada pelo mapeamento do índice
     * @throws IndexOutOfBoundsException Se o índice não está dentro do catálogo
     */
    public V get(int index, K key) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        return cat.get(index).get(key);
    }

    /**
     * Associa um valor especificado a uma chave neste mapeamento.
     * Se a chave indicida já estava mapeada, o valor anterior será
     * substituido pelo valor especificado.
     * @param index - index at which the specified element is to be inserted
     * @param key Chave com o valor que será associado
     * @param value Valor que será associado à chave dada.
     */
    public void put(int index, K key, V value) {
        HashMap<K,V> map = cat.get(index);
        map.put(key, value);
        size++;
    }

    /**
     * Returns the number of indexes of this catalog
     * @return the number of indexes in this catalog
     */
    public int indexes() {
        return cat.size();
    }

    /**
     * Returns the number of elements in this catalog
     * @return the number of elements in this catalog
     */
    public int size() {
        return size;
    }

    /**
     * Returns the number of elements on the specified index of the catalog.
     * @param index - specified index which size is to be returned
     * @return the number of elements on the specified index
     */
    public int sizeOfIndex(int index) {
        return cat.get(index).size();
    }

    /**
     * Returns true if this set contains no elements.
     * @return true if this set contains no elements
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Retorna uma copia desta instancia de Catalog.
     * @return um clone desta instancia de Catalog
     */
    public CatalogMap<K,V> clone() {
        return new CatalogMap<K,V>(this);
    }

    /**
     * Devolve um set com todos os mapeamentos deste objeto
     * @return um Set com todos os mapeamentos deste objeto
     */
     public Set<Map<K,V>> getMaps() {
         return new TreeSet<Map<K,V>>(cat);
     }

    /**
     * Remove todos os mapeamentos deste mapa
     */
    public void clear() {
        for (HashMap hm : cat)
            hm.clear();
    }

    /**
     * Retorna true se e só se a chave dada estiver a ser mapeada
     * no mapeamento do índice dado.
     * (Só pode existir, no máximo, uma destas chaves)
     * @param index índice do mapeamento a procurar
     * @param key Chave cuja presença será testada
     * @return true se a chave dada é mapeada neste mapeamento
     */
    public boolean containsKey(int index, K key) throws IndexOutOfBoundsException{
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        return cat.get(index).containsKey(key);
    }

    /**
     * Retorna true se e só se o valor dado estiver a ser mapeada,
     * pelo menos uma vez, no mapeamento do índice dado.
     * @param index índice
     * @param value Chave cuja presença será testada
     * @return true se a chave dada é mapeada neste mapeamento
     * @throws IndexOutOfBoundsException Caso o índice não se encontre no catálogo
     */
    public boolean containsValue(int index, V value) throws IndexOutOfBoundsException{
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        return cat.get(index).containsValue(value);
    }

    /**
     * Retorna um Set com todos os mapeamentos deste mapa
     * @return um Set com todos os mapeamentos deste mapa
     */
    public Set<Map.Entry<K,V>> entrySet() {
        Set<Map.Entry<K,V>> set = new TreeSet<>();
        Set<Map.Entry<K,V>> aux;

        for (HashMap hm : cat) {
            aux = hm.entrySet();

            for (Map.Entry<K,V> m : aux)
                set.add(m);
        }

        return set;
    }

    /**
     * Compara um objeto especificado com este mapa
     * @return true caso os objetos sejam iguais,
     * false caso contrário
     */
     public boolean equals(Object o) {
        if (this == o) return true;
        if ( o == null || o.getClass() != this.getClass()) return false;

        CatalogMap<K,V> cat = (CatalogMap<K,V>) o;

        if (cat.size() != this.size()) return false;

        for (int i = 0; i < size; i++)
            if (this.get(i) != cat.get(i)) return false;

        return true;
     }

    public int hashCode() {
        int hash = 7;

        hash = 31*hash + size;
        hash = 31*hash + cat.hashCode();

        return hash;
    }

    public Set<K> keySet() {
        Set<K> ret = new TreeSet<>();
        Set<K> aux;

        for (HashMap hm : cat) {
            aux = hm.keySet();

            for (K m : aux)
                ret.add(m);
        }

        return ret;
    }

    public boolean containsKey(Object key) {

        for (int i = 0 ; i < size; i++)
            if (cat.get(i).containsKey(key)) return true;

        return false;
    }

    public boolean containsValue(Object val) {

        for (int i = 0; i < size; i++)
            if (cat.get(i).containsValue(val)) return true;

        return false;
    }

    public V get(Object k) {
        return null;
    }

    public V put(K key, V value) {
        return null;
    }

    public void putAll(Map<? extends K, ? extends V> m) {

    }

    public V remove(Object key) {
        K chave = (K) key;
        Object r = null;

        for (HashMap hm : cat)
            r = hm.remove(chave);

        return (V) r;
    }

    public Collection<V> values() {
        Collection<V> cols = new TreeSet<>();
        Collection<V> aux;

        for(HashMap hm : cat) {
            aux = hm.values();

            for (V val : aux)
                cols.add(val);
        }

        return cols;
    }
}
