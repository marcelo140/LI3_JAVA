/**
 * Write a description of class ClientUnit here.
 * 
 */
public class ClientUnit {
	
	private final int VAZIO    = 0;
	private final int NORMAL   = 1;
	private final int PROMOCAO = 2;
	private final int NP       = 3;

	private int tipo;	

    /**
     * Construtor 
     */
    public ClientUnit() {
    	tipo = VAZIO;
	}

	/**
	 * Construtor por cópia
	 */
	public ClientUnit(ClientUnit c) {
		tipo = c.tipo;
	}

	/**
	 * Devolve o tipo de venda do cliente
	 * 0 - Vazio
	 * 1 - Normal
	 * 2 - Promoção
	 * 3 - Normal e Promoção
	 * @return o tipo de venda do cliente
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * Adiciona uma venda a este ClientUnit
	 * @param venda Venda a adicionar a este ClientUnit
	 */
	public void add(Venda v) {
		int tipo;

		if (v.getPromocao()) tipo = PROMOCAO;
		else tipo = NORMAL;

		if (this.tipo == VAZIO) {
			this.tipo = tipo;
		} else if (this.tipo == NORMAL) {
			if (tipo == PROMOCAO) this.tipo = NP;
		} else if (this.tipo == PROMOCAO) {
			if (tipo == NORMAL) this.tipo = NP;
		}
	}

	/**
	 * Retorna uma copia desta instancia de ClientUnit.
	 * @return um clone desta instancia de ClientUnit
	 */
	public ClientUnit clone() {
		return new ClientUnit(this);
	}
}
