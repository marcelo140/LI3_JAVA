/**
 * Class that describes a product
 */

public class Product {
    // Product's code length
    private static final int CODE_LENGTH = 6;

    private String code;

    /**
     * Constructs a new product with the given code.
     * @param code - code that identifies the product
     */
    public Product(String code) {
        this.code = code;
    }

    /**
     * Constructs a new product which will represent the same product that is given as an
     * argument. Use of this constructor is unnecessary since Products are immutable.
     * @param p - A product
     */
    public Product(Product p) {
        this.code = p.getCode();
    }

    /**
     * Returns the code that identifies the product.
     * @return the code that identifies the product
     */
    public String getCode() {
        return code;    
    }

    /**
     * Compares this Product to the specified product. The result is true if and only if
     * the argument is not null and is a product that represents the same product as this 
     * object.
     * @param o - The object to compare this Product against
     * @return true if the given object represents a Product equivalent to this product
     */
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (o == null || o.getClass() != this.getClass())
            return false;

        Product p = (Product) o;
        return code.equals(p.code);
    }

    /**
     * Creates a copy of this object.
     * @return a clone of this instance
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

        return hash;
    }

    /**
     * Returns true if the product's code is valid. A valid code is a code
     * constituded of six characters where the the first two are capital letters and the 
     * later four represents a number between 1000 and 1999.
     * @return true if the code is valid
     */
    public boolean isValid() {
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
