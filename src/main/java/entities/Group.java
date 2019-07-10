package entities;

public enum Group {
CLIENTE("Cliente"), FUNCIONARIO("Funcionario"), ADMIN("Admin");
	
	private String nome;
	
	private Group(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
