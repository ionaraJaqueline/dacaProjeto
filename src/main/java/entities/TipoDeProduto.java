package entities;

public enum TipoDeProduto {
	BEBIDA("Bebida"), SOBREMESA("Sobremesa"), LANCHE("Lanche");

	private String nome;

	private TipoDeProduto(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
