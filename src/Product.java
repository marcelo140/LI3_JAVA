import java.util.*;
/**
 * Class that describes a product
 */

public class Product {

	private final int MESES = 12;

	private int unidadesVendidas;
	private int[] vendas;
	private double[] faturado;
	private CatalogMap<String, ClientUnit> clientes;

	/**
	 * Construtor padrão
	 */
	public Product() {
		unidadesVendidas = 0;
		vendas = new int[MESES];
		faturado = new double[MESES];
		clientes = new CatalogMap<>();
	}

    /**
	 * Constroi um novo Product com os parametros passados.
	 * @param unidadesVendidas Unidades Vendidas do produto
	 * @param vendas Numero de vendas de cada mês
	 * @param faturado Total faturado de cada mês
	 * @param clientes Lista com HashMap de Clientes para ClientUnit.
     */
    public Product(int unidadesVendidas, int[] vendas,
					double[] faturado, CatalogMap<String, ClientUnit> clientes) {

		this.unidadesVendidas = unidadesVendidas;
		this.vendas = vendas;
		this.faturado = faturado;
		this.clientes = clientes;
	}

    /**
	 * Constroi um novo Product que será igual ao produto dado.
	 *
     * @param p product to copy
     */
    public Product(Product p) {

    }

	/**
	 * Devolve o número total de unidades vendidas.
	 * @return o número total de unidades vendidas
	 */
	public int getUnidadesVendidas() {
		return unidadesVendidas;
	}

	/**
	 * Devolve o número de vendas de cada mês.
	 * @return o número de venas de cada mês
	 */
	public int[] getVendas() {
		return vendas;
	}

	/**
	 * Devolve o número de vendas do mês dado entre 0 e 11.
	 * @param mes cujo o número de vendas será retornado.
	 * @return o número de venas de cada mês
	 * @throws InvalidMonthException caso o mês dado não seja válido
	 */
	public int getVendas(int mes) throws InvalidMonthException {
		if (mes < 0 || mes > 11)
			throw new InvalidMonthException("Mês inválido.");

		return vendas[mes];
	}

	/**
	 * Devolve o total faturado do mês dado entre 0 e 11
	 * @param mes Mês cujo o total faturado deve ser retornado
	 * @return o total faturado de mês dado
	 * @throws InvalidMonthException caso o mes dado não seja válido
	 */
	public double getFaturado(int mes) throws InvalidMonthException {
		if (mes < 0 || mes > 11)
		   		throw new InvalidMonthException("Mês inválido.");

		   return faturado[mes];
	   }

	/**
	 * Devolve o número de clientes de um dado mês entre 0 e 11
	 * @param mes mês cujo o número de clientes será retornado
	 * @return o número de clientes de um dado mês
	 * @throws InvalidMonthException caso o mês dado não seja válido
	 */
	public int getNumeroClientes(int mes) throws InvalidMonthException {
		if (mes < 0 || mes > 11)
			throw new InvalidMonthException("Mês inválido.");
		return clientes.get(mes).size();
	}

	/**
	 * Devolve um CatalogMap que mapeia, para cada mês,
	 * um cliente ao seu correspondente ClientUnit.
	 * @return um CatalogMap que mapeia, para cada mês,
	 * um cliente ao seu correspondente ClientUnit
	 */
	 private CatalogMap<String, ClientUnit> getClientes() {
	     return clientes;
	 }

	/**
	 * Adiciona uma nova venda a este Product
	 * @param venda Venda a adicionar
	 */
	public void add(Venda v) {
		ClientUnit clu = new ClientUnit(v.getUnidades(), v.getPreco() * v.getUnidades());

		unidadesVendidas += v.getUnidades();
		vendas[v.getMes()]++;
		faturado[v.getMes()] += v.getPreco() * v.getUnidades();
		clientes.put(v.getMes(), v.getCliente(), clu);
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
