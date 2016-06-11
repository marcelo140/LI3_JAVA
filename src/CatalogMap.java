import java.lang.*;
import java.util.*;
import java.io.Serializable;

public class CatalogMap<K,V> implements Serializable {
    private List<Map<K,V>> cat;
    private int size;

    /**
 	 * Constrói um catálogo vazio com a capacidade dada
     * @param size número de índices do catálogo do catálogo
     */
    public CatalogMap(int size) {
        this.size = 0;
        cat = new ArrayList<Map<K,V>>(size);

        for (int i = 0 ; i < size; i++)
            cat.add(new HashMap<K,V>());

    }

    /**
 	 * Constrói um catálogo vazio com capacidade 10
     */
    public CatalogMap() {
        size = 0;
        cat = new ArrayList<Map<K,V>>();
        cat.add(new HashMap<K,V>());
    }

    /**
     * Devolve um map de um dado índice do catálogo
     * @param index Índice
     * @return mapeamento dos elementos do índice dado
     * @throws IndexOutOfBoundsException Se o índice não está dentro do catálogo
     */
    public Map<K,V> get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= cat.size())
            throw new IndexOutOfBoundsException();

        return cat.get(index);
    }

    /**
     * Devolve o valor associado à chave dado no mapeamento indicado pelo índice
     * @param index índice do mapeamento
     * @param key Chave cujo valor associado deve ser retornado
     * @return O valor para o qual a chave dada é mapeada pelo mapeamento do índice
     * @throws IndexOutOfBoundsException Se o índice não está dentro do catálogo
     */
    public V get(int index, K key) throws IndexOutOfBoundsException {
        if (index < 0 || index >= cat.size())
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
        Map<K,V> map = cat.get(index);

        map.put(key, value);

        size++;
    }

    /**
 	 * Retorna o número de índices do catálogo
     * @return número de índices do catálogo
     */
    public int length() {
        return cat.size();
    }

    /**
     * Retorna o número de elementos no catálogo
     * @return número de elementos no catálogo
     */
    public int size() {
        return size;
    }

    /**
 	 * Retorna o número de elementos no índice especificado
     * @param index - índice especificado
     * @return número de elementos no índice especificado
     */
    public int sizeOfIndex(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= cat.size())
			throw new IndexOutOfBoundsException();

        return cat.get(index).size();
    }

    /**
     * Verifica se o catálogo está, ou não, vazio
     * @return true se o catálogo estiver vazio
     */
    public boolean isEmpty() {
        return (size == 0);
    }


    /**
     * Remove todos os mapeamentos deste mapa
     */
    public void clear() {
		cat.forEach(m -> m.clear());	
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
        if (index < 0 || index >= cat.size())
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
        if (index < 0 || index >= cat.size())
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

        for (Map<K,V> tm : cat) {
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

        if (cat.size() != this.size() || cat.length() != this.length())
            return false;

        for (int i = 0; i < length(); i++)
            if (!this.get(i).equals(cat.get(i))) return false;

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

        for (Map<K,V> tm : cat) {
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

        for (Map<K,V> tm : cat)
            if ((ret = tm.get(k)) != null) return ret;

        return ret;
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

        for (Map<K,V> tm : cat)
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

        for(Map<K,V> tm : cat) {
            aux = tm.values();

            for (V val : aux)
                cols.add(val);
        }

        return cols;
    }
}
