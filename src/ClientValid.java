/**
 * Implementation of interface Valid for the class Client. This implementation verifies if
 * the client's code has the following pattern:
 *     - The code must have 5 characters;
 *     - The first character must be a capital letter;
 *     - The last four characters must represet a number between 1000 and 5000.
 */

public class ClientValid implements Valid<Client> {
	private final int CODE_LENGTH = 5;

	public boolean isValid(Client c) {
		String code = c.getCode();

		if (code.length() != CODE_LENGTH)
			return false;

		return Character.isUpperCase(code.charAt(0)) &&

               ((code.charAt(1) == '5' && code.charAt(2) == '0' && code.charAt(3) == '0' &&
                 code.charAt(4) == '0')

                ||

                (code.charAt(1) >= '1' && code.charAt(1) < '5' && 
                 Character.isDigit(code.charAt(2)) && Character.isDigit(code.charAt(3)) &&
                 Character.isDigit(code.charAt(4)))); 
	}
}
