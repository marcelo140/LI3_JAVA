 

/**
 * Implementation of Valid for the class Product. This implementation verifies if the
 * product's code has the following pattern:
 *     - The code must hava 6 characters;
 *     - The first and second character must be a capital letter;
 *     - The later four characters must represent a number between 1000 and 1999.
 */
public class ProductValid implements Valid<Product> {
	private final int CODE_LENGTH = 6;

	public boolean isValid(Product p) {	    
		if (p.getCode().length() != CODE_LENGTH)
			return false;

		return Character.isUpperCase(p.getCode().charAt(0)) &&
               Character.isUpperCase(p.getCode().charAt(1)) &&
               p.getCode().charAt(2) == '1' &&
               p.getCode().charAt(3) >= '0' && p.getCode().charAt(3) <= '9' &&
               p.getCode().charAt(4) >= '0' && p.getCode().charAt(4) <= '9' &&
               p.getCode().charAt(5) >= '0' && p.getCode().charAt(5) <= '9';
	}
}
