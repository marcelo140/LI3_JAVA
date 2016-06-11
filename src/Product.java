import java.util.*;
import java.io.Serializable;

/**
 * Class that describes a product
 */

public class Product implements Serializable {
    private final int MESES = 12;

    private CatalogMap<String, ClientUnit> clientes;
    private double[] faturado;
    private int[] vendas;
    private int unidadesVendidas;

    /**
     * Cria um nova instância de Product
     */
    public Product() {
        unidadesVendidas = 0;
        vendas = new int[MESES];
        faturado = new double[MESES];
        clientes = new CatalogMap<>(MESES);
    }

    /**
     * Constrói um novo Product com os parâmetros dados.
     * @param unidadesVendidas unidades Vendidas do produto
     * @param vendas número de vendas em cada mês
     * @param faturado total faturado em cada mês
     * @param clientes catálogo de clientes que compraram o produto
     */
    public Product(int unidadesVendidas, int[] vendas,
                   double[] faturado, CatalogMap<String, ClientUnit> clientes) {

        setUnidadesVendidas(unidadesVendidas);
        setVendas(vendas);
        setFaturado(faturado);
        setClientes(clientes);
    }

    /**
     * Constrói um novo Product que será igual ao produto dado.
     * @param p produto a ser copiado
     */
    public Product(Product p) {
        unidadesVendidas = p.getUnidadesVendidas();
        vendas = p.getVendas();
        faturado = p.getFaturado();
        clientes = p.getClientes();
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
        return vendas.clone();
    }

    /**
     * Devolve o total faturado num array com todos os meses.
     * @return o total faturado num array com todos os meses
     */
    public double[] getFaturado() {
        return faturado.clone();
    }

    /**
     * Devolve um catálogo com informação mensal de cada cliente que comprou o produto
     * @return catálogo
     */
    public CatalogMap<String, ClientUnit> getClientes() {

        CatalogMap<String, ClientUnit> catalog = new CatalogMap<>(MESES);

        for (int i = 0; i < MESES; i++) {
            final int mes = i;
            clientes.get(i).forEach((k,v) -> { catalog.put(mes, k, v.clone()); });
        }

        return catalog;
    }

    /**
     * Define o total faturado
     * @param faturado
     */
    private void setFaturado(double[] faturado) {
        this.faturado = faturado.clone();
    }

    /**
     * Define as unidades vendidas
     * @param unidadesVendidas número de unidades vendidas
     */
    private void setUnidadesVendidas(int unidadesVendidas) {
        this.unidadesVendidas = unidadesVendidas;
    }

    /**
     * Define o número de transações em que este produto foi vendido, mês a mês
     * @param vendas número de transações mensais
     */
    private void setVendas(int[] vendas) {
        this.vendas = vendas.clone();
    }

    /**
     * Define o catálogo com informação mensal de cada cliente que comprou o produto
     * @param clientes catálogo a ser a copiado
     */
    private void setClientes(CatalogMap<String, ClientUnit> clientes) {
        this.clientes = new CatalogMap<>(MESES);

        for (int i = 0; i < MESES; i++) {
            final int mes = i;
            clientes.get(i).forEach((k,v) -> { this.clientes.put(mes, k, v.clone()); });
        }
    }

    /**
     * Devolve o número de vendas do mês dado entre 0 e 11.
     * @param mes cujo o número de vendas será retornado.
     * @return o número de venas de cada mês
     */
    public int getVendas(int mes) {
        return vendas[mes];
    }

	/**
	 * Devolve o total faturado do mês dado entre 0 e 11
	 * @param mes Mês cujo o total faturado deve ser retornado
	 * @return o total faturado de mês dado
	 */
	public double getFaturado(int mes) {
		return faturado[mes];
	}

	/**
	 * Devolve o número de clientes de um dado mês entre 0 e 11
	 * @param mes mês cujo o número de clientes será retornado
	 * @return o número de clientes de um dado mês
	 */
	public int getNumeroClientes(int mes) {
		return clientes.get(mes).size();
	}

	/**
 	 * Retorna o número total de clientes que compraram o produto
 	 * @return número de clientes
 	 */
	public int getNumeroClientes() {
		return clientes.size();
	}

	/**
	 * Adiciona uma nova venda
	 * @param venda Venda a adicionar
	 */
	public void add(Venda v) {
		int unidades = v.getUnidades();
		int mes = v.getMes();
		double faturado = unidades * v.getPreco();
		ClientUnit clu = clientes.get(mes, v.getCliente());

		if (clu != null)
			clu.add(unidades, faturado);
		else {
			clu = new ClientUnit(unidades, faturado);
			clientes.put(mes, v.getCliente(), clu);
		}

		unidadesVendidas += unidades;
		vendas[mes]++;
		this.faturado[mes] += faturado;

	}

	/**
	 * Compara este produto ao objeto dado. Retorna true se e só se o argumento
	 * não for null e o produto representar o mesmo produto que este objeto.
     * @param obj Objeto cuja igualdade será testada
     * @return true se e só se o objeto dado não for null e o produto
     * representar o mesmo produto que este objeto
     */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if ( obj == null || obj.getClass() != this.getClass())
            return false;

        Product p = (Product) obj;
        return p.getUnidadesVendidas() == this.getUnidadesVendidas() &&
               p.getVendas() == this.getVendas() &&
               p.getFaturado() == this.getFaturado() &&
               p.clientes.equals(clientes);
    }

    /**
     * Adiciona os dados do produto recebido
     * @param produto produto a adicionar
     */
    public void merge(Product p) {
        this.unidadesVendidas += p.getUnidadesVendidas();

        for (int i = 0; i < MESES; i++) {
            this.vendas[i] += p.getVendas(i);
            this.faturado[i] += p.getFaturado(i);

            final int mes = i;
            p.clientes.get(i).forEach((k,v) -> { ClientUnit clu = this.clientes.get(mes, k);
                                                 if (clu == null)
                                                   this.clientes.put(mes, k, v.clone());
                                                 else
                                                   clu.add(v);
                                                 });
            }
    }

    /**
     * Cria uma cópia oca deste produto, o que significa que não terá nenhum
     * cliente associado.
     * @return cópia do produto
     */
    public Product clone() {
        return new Product(this);
    }

    /**
     * Retorna uma string que descreve este produto
     * @return string que descreve o produto
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Unidades Vendidas: ").append(unidadesVendidas).append("\n");

        sb.append("Vendas: \n");
        for(int i = 0; i < MESES; i++)
            sb.append("\tMês " + (i+1) + ": ").append(vendas[i]).append("\n");

        sb.append("Faturado: \n");
        for(int i = 0 ; i < 12 ; i++)
            sb.append("\tMês " + (i+1) + ": ").append(faturado[i]).append("\n");

        sb.append("Clientes: \n");
        sb.append(clientes.toString());

        return sb.toString();
    }

    /**
     * Retorna uma hash para este Product.
     * @return hash
     */
    public int hashCode() {
        return Arrays.hashCode(new Object[] {unidadesVendidas, vendas, faturado, clientes});
    }
}
