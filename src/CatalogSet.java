import java.util.*;
import java.io.Serializable;

public class CatalogSet<E> implements Serializable {
	private List<Set<E>> cat;
	int size;

	/**
 	 * Constrói um catálogo com o tamanho especificado
 	 * @param length número de indíces do catálogo
 	 */
	public CatalogSet(int length) {
		size = 0;
		cat = new ArrayList<Set<E>>(length);

		for(int i = 0; i < length; i++)
			cat.add(new TreeSet<E>());
	}

	/**
 	 * Constrói um catálogo com capacidade 10
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
	 * @param index Índice
	 * @return set do índice pedido
	 * @throws IndexOutOfBoundsException Se o índice não está dentro do catálogo
	 */
	public Set<E> get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= cat.size()) 
			throw new IndexOutOfBoundsException(); 

		return new cat.get(index);
	}

	/**
 *	 * Insere o elemento na posição dada do catálogo
 	 * @param index posição em que o elemento será inserido
 	 * @param element elemento a ser inserido
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
 	 * Retorna o número de índices do catálogo
 	 * @return número de índices do catálogo
 	 */
	public int indexes() {
		return cat.size();
	}

	/**
 	 * Retorna o número de elementos no catálogo
 	 * @return número de elementos do catálogo
 	 */
 	public int size() {
		return size;
	}

	/**
 	 * Retorna o número de elementos no índice dado do catálogo
 	 * @param index - índice cujo tamanho vai ser retornado
 	 * @return número de elementos no índice
	 * @throws IndexOutOfBoundsException se o índice não está dentro do catálogo
 	 */
	public int sizeOfIndex(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= cat.size()) 
			throw new IndexOutOfBoundsException();

		return cat.get(index).size();
	}

	/**
 	 * Retorna true se o catálogo estiver vazio
 	 * @return true se o catálogo não tiver elementos
 	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
 	 * Remove todos os dados guardados
 	 */
	public void clear() {
		cat.forEach(s -> s.clear());
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
 	 * @return true se o elemento existe
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
