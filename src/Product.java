import java.util.HashMap;
/**
 * Class that describes a product
 */

public class Product {

	private final int MESES = 12;

	private int unidadesVendidas;
	private double faturadoTotal;
	private int[] vendas;
	private double[] faturado;
	private HashMap<String, ClientUnit>[] validator;

	/**
	 * Construtor padrão
	 */
	public Product() {
		unidadesVendidas = 0;
		faturadoTotal = 0;
		vendas = new int[MESES];
		faturado = new double[MESES];
	}

    /**
     * Constructs a new product with the given code.
     * @param code code that identifies the product
     */
    public Product(String code) {
    
	}

    /**
     * Constructs a new product with the given code and a validator that checks if it
     * follows a set of rules.
     * @param code code that identifies the product
     * @param validator the validator that will be used to check if the code is valid
     */
    public Product(String code, Valid<Product> validator) {
      
    }

    /**
     * Constructs a new product which will represent the same product that is given as an
     * argument. Use of this constructor is unnecessary since Products are immutable.
     * @param p a product
     */
    public Product(Product p) {
      
    }

    /**
     * Returns the code that identifies the product.
     * @return the code that identifies the product
     */
    public String getCode() {
        return null;    
    }

	/**
 	 * Returns the validator used to check if the code is valid.
 	 * @return the validator used to check if the code is valid
 	 */
	public Valid<Product> getValidator() {
		return null;
	}

    /**
     * Compares this Product to the specified product. The result is true if and only if
     * the argument is not null and is a product that represents the same product as this 
     * object.
     * @param obj the object to compare this Product against
     * @return true if the given object represents a Product equivalent to this product
     */
    public boolean equals(Object obj) {
        return false;
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

        sb.append("Código do produto: \n");

        return sb.toString();
    }

    /**
     * Returns a hash code for this product.
     * @return a hash code for this product
     */
    public int hashCode() {
        int hash = 7;

        return hash;
    }

	/**
 	 * Returns true if the product's code is valid. 
 	 * @return true if the product's code is valid
 	 */
	public boolean isValid() {
		return true;
	}
}
