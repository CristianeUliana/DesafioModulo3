package entidades;

public class Vendedor extends Pessoa {
	private Integer matricula;
	
	public Vendedor(String nome, String cpf, Integer matricula) {
		this.nome = nome;
		this.cpf = cpf;
		this.matricula = matricula;
	}
	
	public String toString() {
		return String.format("\nVendedor: %s - CPF: %s - Matrícula: %d",nome,cpf,matricula);
	}
	
	public Integer getMatricula() {
		return matricula;
	}
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
}
