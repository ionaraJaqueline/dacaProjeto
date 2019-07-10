package entities;

public enum TipoDePagamento {
	DINHEIRO("Dinheiro"), CARTAO_DEBITO("Cartao Debito"),  CARTAO_CREDITO("Cartao Credito") ;
	
	private String nome;
	
	private TipoDePagamento(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
