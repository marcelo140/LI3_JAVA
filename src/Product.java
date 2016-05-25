/**
 * Class that describes a product
 */

public class Product {
    private String code;
	private Valid<Product> validator;

    /**
     * Constructs a new product with the given code.
     * @param code code that identifies the product
     */
    public Product(String code) {
        this.code = code;
		this.validator = new ProductValid();
    }

    /**
     * Constructs a new product with the given code and a validator that checks if it
     * follows a set of rules.
     * @param code code that identifies the product
     * @param validator the validator that will be used to check if the code is valid
     */
    public Product(String code, Valid<Product> validator) {
        this.code = code;
		this.validator = validator;
    }

    /**
     * Constructs a new product which will represent the same product that is given as an
     * argument. Use of this constructor is unnecessary since Products are immutable.
     * @param p a product
     */
    public Product(Product p) {
        this.code = p.getCode();
		this.validator = p.getValidator();
    }

    /**
     * Returns the code that identifies the product.
     * @return the code that identifies the product
     */
    public String getCode() {
        return code;    
    }

	/**
 	 * Returns the validator used to check if the code is valid.
 	 * @return the validator used to check if the code is valid
 	 */
	public Valid<Product> getValidator() {
		return validator;
	}

    /**
     * Compares this Product to the specified product. The result is true if and only if
     * the argument is not null and is a product that represents the same product as this 
     * object.
     * @param obj the object to compare this Product against
     * @return true if the given object represents a Product equivalent to this product
     */
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Product p = (Product) obj;
        return code.equals(p.code) && validator.equals(p.validator);
    }

    /**
     * Creates a copy of this product.
     * @return a clone of this product 
     */
    public Product clone() {
        return new Product(this);
    }

    /**
     * Returns a string representation of this product.
     * @return a string representation of this product
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Código do produto: ").append(code).append("\n");

        return sb.toString();
    }

    /**
     * Returns a hash code for this product.
     * @return a hash code for this product
     */
    public int hashCode() {
        int hash = 7;

        hash = 31*hash + code.hashCode();
		hash = 31*hash + validator.hashCode();

        return hash;
    }

	/**
 	 * Returns true if the product's code is valid. 
 	 * @return true if the product's code is valid
 	 */
	public boolean isValid() {
		return validator.isValid(this);
	}
}
