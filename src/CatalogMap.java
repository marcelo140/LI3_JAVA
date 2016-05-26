/**
 * 
 */

import java.util.*;
import java.util.Set;
import java.util.List;
import java.util.TreeSet;
import java.util.ArrayList;

public class CatalogMap<K,V> {
	private List<HashMap<K,V>> cat;
	int size;

	/**
 	 * Constructs an empty catalog with the specified capacity.
 	 * @param tamanho
 	 */
	public CatalogMap(int size) {
		size = 0;
		cat = new ArrayList<HashMap<K,V>>(size);

		for(HashMap<K, V> map : cat)
			map = new HashMap<K, V>();
	}

	/**
 	 * Constructs an empty catalog with capacity ten.
 	 */
	public CatalogMap() {
		size = 0;
		cat = new ArrayList<HashMap<K,V>>();
		
		for(HashMap<K,V> map : cat)
			map = new HashMap<K,V>();
	}

	/**
 	 * Construtor por cópia
	 */
	public CatalogMap(CatalogMap<K,V> c) {
		int i = 0;
		size = c.size();
		cat = new ArrayList<HashMap<K,V>>(size);

		for (HashMap<K,V> map : cat) {
			map = new HashMap<K,V>(c.getMap(i));	
			i++;
		}
	}

	/**
	 * Devolve um map de um dado índice do catálogo
	 * @throws IndexOutOfBoundsException Se o índice não está dentro do catálogo
	 * @param index Índice
	 */
	public Map<K,V> getMap(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) 
			throw new IndexOutOfBoundsException(); 

		return new HashMap<K,V> (cat.get(index));
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
	 * Devolve o valor associado à chave dado no mapeamento indicado pelo índice
	 * @param index índice do mapeamento
	 * @param key Chave cujo valor associado deve ser retornado
	 * @return O valor para o qual a chave dada é mapeada pelo mapeamento do índice
	 */
	public V get(int index, K key) {
		return cat.get(index).get(key);
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
	 * Retorna true se e só se a chave dada estiver a ser mapeada 
	 * no mapeamento do índice dado.
	 * (Só pode existir, no máximo, uma destas chaves)
	 * @param index índice do mapeamento a procurar
	 * @param key Chave cuja presença será testada
	 * @return true se a chave dada é mapeada neste mapeamento
	 * @throws IndexOutOfBoundsException Caso o índice não se encontre no catálogo
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

}
