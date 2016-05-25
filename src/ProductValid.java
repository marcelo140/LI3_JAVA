/**
 * Implementation of interface Valid for the class Product. This implementation verifies if
 * the product's code has the following pattern:
 *     - The code must hava 6 characters;
 *     - The first and second character must be a capital letter;
 *     - The last four characters must represent a number between 1000 and 1999.
 */
public class ProductValid implements Valid<Product> {
	private final int CODE_LENGTH = 6;

	public boolean isValid(Product p) {
		String code = p.getCode();
	
		if (code.length() != CODE_LENGTH)
			return false;

		return Character.isUpperCase(code.charAt(0)) &&
               Character.isUpperCase(code.charAt(1)) &&
               code.charAt(2) == '1' &&
               code.charAt(3) >= '0' && code.charAt(3) <= '9' &&
               code.charAt(4) >= '0' && code.charAt(4) <= '9' &&
               code.charAt(5) >= '0' && code.charAt(5) <= '9';
	}
}
