package controller;


import java.util.ArrayList;
import java.util.Scanner;
import entidades.Carro;
import entidades.Cliente;
import entidades.Moto;
import entidades.Vendedor;


public class ControleVendas {
	private Scanner scanner = new Scanner(System.in);
	private ArrayList<Cliente> clientes = new ArrayList<>();
	private ArrayList<Vendedor> vendedores = new ArrayList<>();
	private ArrayList<Carro> carros = new ArrayList<>();
	private ArrayList<Moto> motos = new ArrayList<>();
	
	
	
	private String mostrarMenu() {
		StringBuilder sb = new StringBuilder();
		sb.append("Digite o comando desejado: \n");
		sb.append("1 - Consultar carros \n");
		sb.append("2 - Consultar motos \n");
		sb.append("3 - Consultar clientes \n");
		sb.append("4 - Consultar vendedores \n");
		sb.append("5 - Cadastrar carro \n");
		sb.append("6 - Cadastrar moto \n");
		sb.append("7 - Cadastrar cliente \n");
		sb.append("8 - Cadastrar vendedor \n");
		sb.append("9 - Vender carro \n");
		sb.append("10 - Vender moto \n");
		sb.append("S - Sair \n");
		
		System.out.println(sb.toString());
		return scanner.nextLine();
	}
	
	
	public void iniciar() {
		String opcao = mostrarMenu();
		while (!opcao.toUpperCase().equals("S")) {
			switch (opcao) {
			case "1":
				System.out.println(carros);
				opcao = mostrarMenu();
				break;
			case "2":
				System.out.println(motos);
				opcao = mostrarMenu();
				break;
			case "3":
				System.out.println(clientes);
				opcao = mostrarMenu();
				break;
			case "4":
				System.out.println(vendedores);
				opcao = mostrarMenu();
				break;
			case "5":
				System.out.println("Digite o modelo do carro: ");
				String modelo = scanner.nextLine();
				System.out.println("Digite a marca do carro: ");
				String marca = scanner.nextLine();
				System.out.println("Digite o ano do carro: ");
				Integer ano = Integer.parseInt(scanner.nextLine());
				System.out.println("Digite o valor do carro: ");
				Double valor = Double.parseDouble(scanner.nextLine());
				System.out.println("Digite o índice do vendedor: ");
				Integer indiceVendedor = Integer.parseInt(scanner.nextLine());
				Carro carro = new Carro(modelo, marca, ano, valor, vendedores.get(indiceVendedor));
				carros.add(carro);
				opcao = mostrarMenu();
				break;
			case "6":
				System.out.println("Digite o modelo da moto: ");
				String modeloMoto = scanner.nextLine();
				System.out.println("Digite a marca da moto: ");
				String marcaMoto = scanner.nextLine();
				System.out.println("Digite o ano da moto: ");
				Integer anoMoto = Integer.parseInt(scanner.nextLine());
				System.out.println("Digite o valor da moto: ");
				Double valorMoto = Double.parseDouble(scanner.nextLine());
				System.out.println("Digite o índice do vendedor: ");
				Integer indiceVendedorMoto = Integer.parseInt(scanner.nextLine());
				Moto moto = new Moto(modeloMoto, marcaMoto, anoMoto, valorMoto, vendedores.get(indiceVendedorMoto));
				motos.add(moto);
				opcao = mostrarMenu();
				break;
			case "7":
				System.out.println("Digite o nome do cliente: ");
				String nomeCliente = scanner.nextLine();
				System.out.println("Digite o cpf do cliente: ");
				String cpfCliente = scanner.nextLine();
				System.out.println("Digite o endereco do cliente: ");
				String enderecoCliente = scanner.nextLine();
				Cliente cliente = new Cliente(nomeCliente, cpfCliente, enderecoCliente);
				clientes.add(cliente);
				opcao = mostrarMenu();
				break;
			case "8":
				System.out.println("Digite o nome do vendedor: ");
				String nomeVendedor = scanner.nextLine();
				System.out.println("Digite o cpf do vendedor: ");
				String cpfVendedor = scanner.nextLine();
				System.out.println("Digite a matricula do vendedor: ");
				Integer matriculaVendedor = Integer.parseInt(scanner.nextLine());
				Vendedor vendedor = new Vendedor(nomeVendedor, cpfVendedor, matriculaVendedor);
				vendedores.add(vendedor);
				opcao = mostrarMenu();
				break;
			case "9":
				System.out.println("Digite o índice do carro: ");
				Integer indiceCarro = Integer.parseInt(scanner.nextLine());
				System.out.println("Digite o índice do cliente: ");
				Integer indiceClienteCarro = Integer.parseInt(scanner.nextLine());
				carros.get(indiceCarro).setCliente(clientes.get(indiceClienteCarro));
				opcao = mostrarMenu();
				break;
			case "10":
				System.out.println("Digite o índice da moto: ");
				Integer indiceMoto = Integer.parseInt(scanner.nextLine());
				System.out.println("Digite o índice do cliente: ");
				Integer indiceClienteMoto = Integer.parseInt(scanner.nextLine());
				motos.get(indiceMoto).setCliente(clientes.get(indiceClienteMoto));
				opcao = mostrarMenu();
				break;
			}
		}
		if (opcao.toUpperCase().equals("S")) {
			System.out.println("Fim da execução");
		}
	}
}
	
	
	
	
	
	

