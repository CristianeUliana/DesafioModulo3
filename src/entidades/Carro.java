package entidades;

public class Carro extends Veiculos {

	public Carro(String modelo, String marca, Integer ano, Double valor, Vendedor vendedorResponsavel) {
		this.modelo = modelo;
		this.marca = marca;
		this.ano = ano;
		this.valor = valor;
		this.vendedorResponsavel = vendedorResponsavel;
		this.cliente = null;
	}
	
	public String toString() {
		return modelo + " - " + marca + " - " + ano + " - " + valor + " - " + vendedorResponsavel + " - " + cliente;
	}
}
