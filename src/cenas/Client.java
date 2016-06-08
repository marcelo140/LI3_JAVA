/**
 * Class that describes a client
 */

public class Client {
	private String code;
	private Valid<Client> validator;

    /**
     * Constructs a new client with the given code.
     * @param code code that identifies the client 
     */
    public Client(String code) {
        this.code = code;
		this.validator = new ClientValid();
    }

    /**
     * Constructs a new client with the given code and a validator that checks if it follows
     * a set of rules
     * @param code code that identifies the client
     * @param validator the validator that will be used to check if the code is valid
     */
    public Client(String code, Valid<Client> validator) {
        this.code = code;
		this.validator = validator;
    }

    /**
     * Constructs a new client which will represent the same client that is given as an
     * argument. Use of this constructor is unnecessary since Clients are immutable.
     * @param c a client
     */
    public Client(Client c) {
        this.code = c.getCode();
		this.validator = c.getValidator();
    }

    /**
     * Returns the code that identifies the client.
     * @return the code that identifies the client 
     */
    public String getCode() {
        return code;    
    }

	/**
 	 * Returns the validator used to check if the code is valid.
 	 * @return the validator used to check if the code is valid
 	 */
	public Valid<Client> getValidator() {
		return validator;
	}

    /**
     * Compares this Client to the specified client. The result is true if and only if
     * the argument is not null and is a client that represents the same client as this 
     * object.
     * @param obj the object to compare this Client against
     * @return true if the given object represents a Client equivalent to this client 
     */
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Client c = (Client) obj;
        return code.equals(c.code) && validator.equals(c.validator);
    }

    /**
     * Creates a copy of this client.
     * @return a clone of this client 
     */
    public Client clone() {
        return new Client(this);
    }

    /**
     * Returns a string representation of this client.
     * @return a string representation of this client
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Código do cliente: ").append(code).append("\n");

        return sb.toString();
    }

    /**
     * Returns a hash code for this client.
     * @return a hash code for this client
     */
    public int hashCode() {
        int hash = 7;

        hash = 31*hash + code.hashCode();
		hash = 31*hash + validator.hashCode();

        return hash;
    }

	/**
 	 * Returns true if the client's code is valid. 
 	 * @return true if the client's code is valid
 	 */
	public boolean isValid() {
		return validator.isValid(this);
	}
}
