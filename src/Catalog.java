/**
 * 
 */

import java.util.Set;
import java.util.List;
import java.util.TreeSet;
import java.util.ArrayList;

public class Catalog<E> {
	private List<TreeSet<E>> cat;
	int size;

	/**
 	 * Constructs an empty catalog with the specified capacity.
 	 * @param tamanho
 	 */
	public Catalog(int size) {
		size = 0;
		cat = new ArrayList<TreeSet<E>>(tamanho);

		for(TreeSet<E> tree: cat)
			tree = new TreeSet<E>();
	}

	/**
 	 * Constructs an empty catalog with capacity ten.
 	 */
	public Catalog() {
		size = 0;
		cat = new ArrayList<TreeSet<E>>();
		
		for(TreeSet<E> tree: cat)
			tree = new TreeSet<E>();
	}

	/**
 	 * Construtor por cópia
	 */

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
	public void indexes() {
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
}
