import java.util.*;
/**
 * Venda é a class que descreve os elementos envolvidos numa transação
 */

import java.io.Serializable;

public class Venda implements Serializable
{
    private String produto;
    private String cliente;
    private double preco;
    private int unidades;
 //   private boolean promocao;
    private int mes;
    private int filial;

	/**
 	 * Construtor por argumentos
         * @param produto
         * @param preco
         * @param unidades
         * @param promocao
         * @param cliente
         * @param mes entre 1 e 12
         * @param filial
 	 */
	public Venda(String produto, double preco, int unidades, /** boolean promocao,**/
                 String cliente, int mes, int filial)
	{
		this.produto = produto;
		this.cliente = cliente;
		this.preco = preco;
		this.unidades = unidades;
//		this.promocao = promocao;
		this.mes = mes;
		this.filial = filial;
	}

	/**
 	 * Construtor por cópia
         * @param v
 	 */
	public Venda(Venda v) {
		produto = v.getProduto();
		cliente = v.getCliente();
		preco = v.getPreco();
		unidades = v.getUnidades();
//		promocao = v.getPromocao();
		mes = v.getMes();
		filial = v.getFilial();
	}

	/**
 	 * Obter produto
 	 * @return Produto
 	 */
	public String getProduto() {
		return produto;
	}

	/**
 	 * Obter cliente
 	 * @return Cliente
 	 */
	public String getCliente() {
		return cliente;
	}

	/**
 	 * Obter preço
 	 * @return Preço
 	 */
	public double getPreco() {
		return preco;
	}

	/**
 	 * Obter unidades
 	 * @return Unidades
 	 */
	public int getUnidades() {
		return unidades;
	}

	/**
 	 * Verifica se a venda foi efetuada em promoção
 	 * @return
 	 */
//	public boolean getPromocao() {
//		return promocao;
//	}

	/**
 	 * Obter mês
 	 * @return Mês
 	 */
	public int getMes() {
		return mes;
	}

	/**
 	 * Obter filial
 	 * @return Filial
 	 */
	public int getFilial() {
		return filial;
	}

	/**
 	 * Converte uma String numa Venda
 	 */
    public static Venda parse(String linha) throws VendaParseException {
		String[] dados;
		int unidades, mes, filial;
		double preco;
//		boolean promocao;

		dados = linha.trim().split(" ");

		try {
			preco    = Double.parseDouble(dados[1].trim());
			unidades = Integer.parseInt(dados[2].trim());
			mes      = Integer.parseInt(dados[5].trim()) - 1;
			filial   = Integer.parseInt(dados[6].trim()) - 1;
		}catch(NullPointerException | NumberFormatException e) {
			throw new VendaParseException(e.getMessage());
		}

//        promocao = !dados[3].trim().equals("N");

        return new Venda(dados[0], preco, unidades/**, promocao**/, dados[4], mes, filial);
	}


	/**
 	 * Verifica se uma venda é válida
 	 */
	public boolean isValid(CatalogSet<String> produtos, CatalogSet<String> clientes) {
		return produtos.contains(produto.charAt(0) - 'A', produto) && 
		       clientes.contains(cliente.charAt(0) - 'A', cliente);
	}

	/**
 	 * Cria uma cópia da venda
 	 * @return Venda
 	 */
	public Venda clone() {
		return new Venda(this);
	}

	/**
 	 * Compara a venda com o objeto dado
 	 * @return
 	 */
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (o == null || o.getClass() != this.getClass())
			return false;

		Venda v = (Venda) o;
		return v.getProduto().equals(produto) &&
               v.getCliente().equals(cliente) &&
			   v.getPreco() == preco &&
               v.getUnidades() == unidades &&
//               v.getPromocao() == promocao &&
			   v.getMes() == mes &&
               v.getFilial() == filial;
	}

	/**
 	 * Converte uma instância venda numa String
 	 * @return String
 	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Produto: ").append(produto).append("\n");
		sb.append("Preço: ").append(preco).append("\n");
		sb.append("Unidades: ").append(unidades).append("\n");
//		sb.append("Promoção: ").append(promocao).append("\n");
		sb.append("Cliente: ").append(cliente).append("\n");
		sb.append("Mês: ").append(mes).append("\n");
		sb.append("Filial: ").append(filial).append("\n");

		return sb.toString();
	}
}
