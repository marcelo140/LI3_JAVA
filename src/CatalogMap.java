 /**
 *
 */

import java.lang.*;
import java.util.*;
import java.util.Set;
import java.util.List;
import java.util.TreeSet;
import java.util.ArrayList;

public class CatalogMap<K,V> implements Map<K,V> {
    private List<TreeMap<K,V>> cat;
    private int size;

    /**
     * Constructs an empty catalog with the specified capacity.
     * @param tamanho
     */
    public CatalogMap(int size) {
        this.size = size;
        cat = new ArrayList<TreeMap<K,V>>(size);

        for (int i = 0 ; i < size; i++)
            cat.add(new TreeMap<K,V>());

    }

    /**
     * Constructs an empty catalog with capacity ten.
     */
    public CatalogMap() {
        size = 1;
        cat = new ArrayList<TreeMap<K,V>>();

        for (int i = 0 ; i < size; i++)
            cat.add(new TreeMap<K,V>());
    }

    /**
     * Construtor por cópia
     * TODO isto não funciona
     */
    public CatalogMap(CatalogMap<K,V> c) {
        size = c.size();
        cat = new ArrayList<TreeMap<K,V>>(size);

        for (int i = 0 ; i < size; i++)
            cat.add(new TreeMap<K,V>(c.get(i)));
    }

    /**
     * Devolve um map de um dado índice do catálogo
     * @param index Índice
     * @throws IndexOutOfBoundsException Se o índice não está dentro do catálogo
     */
    public Map<K,V> get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        return new TreeMap<K,V> (cat.get(index));
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
        TreeMap<K,V> map = cat.get(index);
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
        for (TreeMap<K,V> tm : cat)
            tm.clear();
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

        for (TreeMap<K,V> tm : cat) {
            aux = tm.entrySet();

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

     /**
      * Retorna o código de hash para este mapa
      * @return código de hash para este mapa
      */
     public int hashCode() {
        int hash = 7;

        hash = 31*hash + size;
        hash = 31*hash + cat.hashCode();

        return hash;
    }

    /**
     * Retorna um Set com todas as chaves deste mapa
     * @return Set com todas as chaves deste mapa
     */
    public Set<K> keySet() {
        Set<K> ret = new TreeSet<>();
        Set<K> aux;

        for (TreeMap<K,V> tm : cat) {
            aux = tm.keySet();

            for (K m : aux)
                ret.add(m);
        }

        return ret;
    }

    /**
     * Verifica se o objeto dado é Chave deste mapeamento
     * @param key Chave cuja presença neste mapeamento será testada0
     * @return true caso este mapeamento contem a chave dada
     * @throws ClassCastException Caso o tipo da chave não seja
     * apropriada para este mapeamento
     * @throws NullPointerException Caso a chave especificada for null e
     * este mapeamento não permitir chaves a null
     */
    public boolean containsKey(Object key)
                    throws ClassCastException, NullPointerException {
        for (int i = 0 ; i < size; i++)
            if (cat.get(i).containsKey(key)) return true;

        return false;
    }

    /**
     * Retorna true caso este mapa contenha um mapeamento de uma
     * ou mais chaves para o valor dado.
     * @param value Valor cuja presença neste mapeamento será testada
     * @return true caso este mapa contiver um mapeamento de
     * uma ou mais chaves para o valor dado
     * @throws ClassCastException Caso o valor dado não seja apropriado
     * para este mapeamento
     * @throws NullPointerException caso o valor dado seja null e este
     * mapeamento não permitir valores a null
     */
    public boolean containsValue(Object value)
                    throws ClassCastException, NullPointerException {
        for (int i = 0; i < size; i++) {
            if (cat.get(i).containsValue(value)) return true;
        }

        return false;
    }

    /**
     * Retorna o valor que está mapeado à chave dada, ou null caso
     * tal mapeamento não existir.
     * @param key Chave cujo valor associado será retornado
     * @return o valor que está mapeado à chave dada, ou null caso
     * tal mapeamento não existir
     * @throws ClassCastException Caso a chave não seja do tipo apropriado
     * para este mapeamento
     * @throws NullPointerException Caso a chave especificada for null e
     * este mapeamento não permitir chaves a null
     */
    public V get(Object key) throws ClassCastException, NullPointerException {
        V ret = null;

        K k = (K) key;

        for (TreeMap<K,V> tm : cat)
            if ((ret = tm.get(k)) != null) return ret;

        return ret;
    }

    /**
     * Esta função não é suportada por este mapa!
     * Associa neste mapeamento valor dado à chave dada.
     * Se já existir um mapeamento desta chave, o valor antigo será
     * substituido pelo valor dado.
     * @param key chave que será associada ao valor dado
     * @param value valor que será associado à chave dada
     * @return o valor anteriormente associado a esta chave,
     * ou null caso não exista um mapeamento anterior desta chave
     * @throws UnsupportedOperationException este mapeamento não suporta
     * esta operação, por isso é garantido que será atirada esta exceção
     */
    public V put(K key, V value) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("put não é suportado");
    }

    /**
     * Atira a exceção UnsupportedOperationException
     * @throws UnsupportedOperationException
     */
    public void putAll(Map<? extends K, ? extends V> m)
                    throws UnsupportedOperationException{

        throw new UnsupportedOperationException("putAll não suportado.");
    }

    /**
     * Remove <b>todos</b> os mapeamentos desta chave.
     * Retorna o último valor que removeu associado a esta chave.
     * @param key chave cujos mapeamentos serão todos removidos
     * @return o último valor que removeu associado a esta chave
     * @throws ClassCastException caso o tipo da chave não seja apropriado
     * a este mapa
     * @throws NullPointerException caso a chave seja null e este mapa não
     * permitir chaves a null
     */
    public V remove(Object key) throws ClassCastException,
                                       NullPointerException {
        K chave = (K) key;
        Object r = null;

        for (TreeMap<K,V> tm : cat)
            r = tm.remove(chave);

        return (V) r;
    }

    /**
     * Retorna uma coleção com todos os valores mapeados neste mapa.
     * @return uma coleção com todos os valores mapeados neste mapa
     */
    public Collection<V> values() {
        Collection<V> cols = new TreeSet<>();
        Collection<V> aux;

        for(TreeMap<K,V> tm : cat) {
            aux = tm.values();

            for (V val : aux)
                cols.add(val);
        }

        return cols;
    }
}
