/**
 * Checks if the instance that implements this interface has the indicated pre-requisites.
 */

public interface Valid<E> {
	/**
 	 * Returns true if the product's code is valid.
 	 * @return true if the code is valid
 	 */
	public boolean isValid();
}
