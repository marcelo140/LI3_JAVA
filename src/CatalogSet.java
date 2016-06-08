/**
 * 
 */

import java.util.*;
import java.util.Set;
import java.util.List;
import java.util.TreeSet;
import java.util.ArrayList;

public class CatalogSet<E> {
	private ArrayList<TreeSet<E>> cat;
	int size;

	/**
 	 * Constructs an empty catalog with the specified capacity.
 	 * @param tamanho
 	 */
	public CatalogSet(int tam) {
		size = 0;
		cat = new ArrayList<TreeSet<E>>(tam);

		for(int i = 0; i < tam; i++)
			cat.add(new TreeSet<E>());
	}

	/**
 	 * Constructs an empty catalog with capacity ten.
 	 */
	public CatalogSet() {
		size = 0;
		cat = new ArrayList<TreeSet<E>>();
		
		for(TreeSet<E> tree: cat)
			tree = new TreeSet<E>();
	}

	/**
 	 * Construtor por cópia
	 */
	public CatalogSet(CatalogSet<E> c) {
		int i = 0;
		size = c.size();
		cat = new ArrayList<TreeSet<E>>(size);

		for (TreeSet<E> tree : cat) {
			tree = new TreeSet<E>(c.get(i));	
			i++;
		}
	}

	/**
	 * Devolve o set de um dado índice do catálogo
	 * @throws IndexOutOfBoundsException Se o índice não está dentro do catálogo
	 * @param index Índice
	 */
	public Set<E> get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= cat.size()) 
			throw new IndexOutOfBoundsException(); 

		return new TreeSet<E> (cat.get(index));
	}

	/**
 	 * Inserts the specified element at the specified position in catalog.
 	 * @param index - index at which the specified element is to be inserted
 	 * @param element - element to be inserted
 	 */
	public void add(int index, E element) {
		TreeSet<E> tree = cat.get(index);
		tree.add(element);
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
	 * Retorna uma copia desta instancia de CatalogSet.
	 * @return um clone desta instancia de CatalogSet
	 */
	public CatalogSet<E> clone() {
		return new CatalogSet<E>(this);
	}

	/**
	 * Verifica se o elemento E já existe no catálogo no índice index.
	 * @param index índice
	 * @param element Elemento a procurar
	 * @throws IndexOutOfBoundsException Caso o índice não se encontre no catálogo
	 * @return true se o elemento existe, false caso contrário
	 */
	public boolean contains(int index, E element) throws IndexOutOfBoundsException{
		if (index < 0 || index >= size) 
			throw new IndexOutOfBoundsException(); 
	
		return cat.get(index).contains(element); 
	}

	/**
 	 * Verifica se o elemento E existe no catálogo
 	 * @param element Elemento a encontrar
 	 * return true se o elemento existe
 	 */
	public boolean contains(E element) {
		boolean r = true;
		int size = cat.size();

		for(int i = 0; r && i < size; i++)
			r = cat.get(i).contains(element);

		return r;
	}

	public CatalogMap<E, Object> toMap() {
		CatalogMap<E, Object> map = new CatalogMap<>();

		for (int i = 0; i < size; i++) {
			for (E element : cat.get(i)) 
				map.put(i, element, null);
		}

		return map;
	}
}
