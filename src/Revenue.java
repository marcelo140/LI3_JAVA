public class Revenue
{

	private double[][][] faturacao;
	private int[][][] quantidade;


	public Revenue(){
		this.faturacao = new double [12][3][2];
		this.quantidade = new int [12][3][2];

	}

	public Revenue(double[][][] faturacao, int[][][] quantidade) {

		this.faturacao = faturacao;
		this.quantidade = quantidade;
	}

	public Revenue(Revenue r){
		this.faturacao = r.getFaturacao();
		this.quantidade = r.getQuantidade();
	}

	public double[][][] getFaturacao(){
		return faturacao;
	}

	public int[][][] getQuantidade(){
		return quantidade;
	}

	public void setFaturacao(double[][][] faturacao){
		this.faturacao = faturacao;
	}

	public void setQuantidade(int[][][] quantidade){
		this.quantidade = quantidade;
	}

	public boolean equals(Object o) {
        if(o==this) {
            return true;
        }
        if(o==null || o.getClass() != this.getClass()) {
            return false;
        }
       	Revenue r = (Revenue) o;
        return r.getFaturacao().equals(faturacao) 
                && r.getQuantidade().equals(quantidade);  
    }

    public Revenue clone(){
    	return new Revenue(this);
    }

    public boolean isEmpty(Revenue r) {
    	for(int mes=0; mes<12; mes ++){
    		for(int filial=0 ; filial<3 ; filial ++){
    			for(int modo=0 ; modo<2 ; modo++)
    				if (faturacao [mes][filial][modo] != 0) return false;
    		}
    	}
    	return true;
    }

    public void addSale(Venda v){
    	/* int i;
    	if (v.getPromocao() == true) i = 1;
    	else i = 0;
    	faturacao [v.getMes()][v.getFilial()][i] = (v.getPreco()) * (v.getUnidades());
    	quantidade [v.getMes()][v.getFilial()][i] += v.getUnidades();   */
    }


}