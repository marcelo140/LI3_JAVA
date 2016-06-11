import java.util.*;
import java.io.Serializable;

public class CatalogSet<E> implements Serializable {
	private List<Set<E>> cat;
	int size;

	/**
 	 * Constructs an empty catalog with the specified capacity.
 	 * @param length
 	 */
	public CatalogSet(int length) {
		size = 0;
		cat = new ArrayList<Set<E>>(length);

		for(int i = 0; i < length; i++)
			cat.add(new TreeSet<E>());
	}

	/**
 	 * Constructs an empty catalog with capacity ten.
 	 */
	public CatalogSet() {
		size = 0;
		cat = new ArrayList<Set<E>>();
		
		for(int i = 0; i < cat.size() ; i++)
			cat.add(new TreeSet<E>());
	}

	/**
 	 * Construtor por cópia
	 */
	private CatalogSet(CatalogSet<E> c) {
		size = c.size();
		cat = new ArrayList<Set<E>>(size);

		for (int i = 0; i < c.indexes(); i++)
			cat.add(new TreeSet<E>(c.get(i)));
	}

	/**
	 * Devolve o set de um dado índice do catálogo
	 * @throws IndexOutOfBoundsException Se o índice não está dentro do catálogo
	 * @param index Índice
	 */
	public TreeSet<E> get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= cat.size()) 
			throw new IndexOutOfBoundsException(); 

		return new TreeSet<E>(cat.get(index));
	}

	/**
 	 * Inserts the specified element at the specified position in catalog.
 	 * @param index - index at which the specified element is to be inserted
 	 * @param element - element to be inserted
	 * @throws IndexOutOfBoundsException se o índice não está dentro do catálogo
 	 */
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if (index < 0 || index >= cat.size()) 
			throw new IndexOutOfBoundsException();

		Set<E> tree = cat.get(index);

		if (tree.add(element))
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
 	 * Remove todos os dados guardados
 	 */
	public void clear() {
		cat.forEach(s -> s.clear());
	}

	/**
 	 * Returns the number of elements on the specified index of the catalog.
 	 * @param index - specified index which size is to be returned
 	 * @return the number of elements on the specified index
	 * @throws IndexOutOfBoundsException Se o índice não está dentro do catálogo
 	 */
	public int sizeOfIndex(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= cat.size()) 
			throw new IndexOutOfBoundsException();

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
		boolean r = false;
		int length = cat.size();

		for(int i = 0; !r && i < length; i++)
			r = cat.get(i).contains(element);

		return r;
	}

	public int hashCode() {
		return Arrays.hashCode( new Object[] { cat, size });
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		for(Set<E> s: cat)
			sb.append(s.toString()).append("\n");

		return sb.toString();
	}

}
