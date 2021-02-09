package entidades;

public class Moto extends Veiculos {

	public Moto(Integer id, String modelo, String marca, Integer ano, Double valor, Vendedor vendedorResponsavel) {
		this.id = id;
		this.modelo = modelo;
		this.marca = marca;
		this.ano = ano;
		this.valor = valor;
		this.vendedorResponsavel = vendedorResponsavel;
		this.cliente = null;
	}
	public String toString() {
		if (cliente == null) {
			return String.format("\nID: %s - Modelo: %s - Marca: %s - Ano: %d - Valor: R$ %.2f - Vendedor Responsável: %s",id,modelo,marca,ano,valor,vendedorResponsavel.getNome());
		} else {
			return String.format("\nID: %s - Modelo: %s - Marca: %s - Ano: %d - Valor: R$ %.2f - Vendedor Responsável: %s - Comprado por: %s",id,modelo,marca,ano,valor,vendedorResponsavel.getNome(),cliente.getNome());
		}
	}
}
