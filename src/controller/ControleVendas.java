package controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
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
	private ArquivoVendedores arquivoVendedores;
	private ArquivoClientes arquivoClientes;
	private ArquivoCarros arquivoCarros;
	private ArquivoMotos arquivoMotos;
	
	public ControleVendas() {
		try {
			this.arquivoVendedores = new ArquivoVendedores("D:\\BOOTCAMP_IGTI\\modulo3\\Projetos\\DesafioModulo3\\arquivoVendedores.txt");		
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		try {
			this.arquivoClientes = new ArquivoClientes("D:\\BOOTCAMP_IGTI\\modulo3\\Projetos\\DesafioModulo3\\arquivoClientes.txt");		
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		try {
			this.arquivoCarros = new ArquivoCarros("D:\\BOOTCAMP_IGTI\\modulo3\\Projetos\\DesafioModulo3\\arquivoCarros.txt");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		try {
			this.arquivoMotos = new ArquivoMotos("D:\\BOOTCAMP_IGTI\\modulo3\\Projetos\\DesafioModulo3\\arquivoMotos.txt");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
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
		vendedores.addAll(arquivoVendedores.lerArquivo());
		clientes.addAll(arquivoClientes.lerArquivo());
		carros.addAll(arquivoCarros.lerArquivo(vendedores,clientes));
		motos.addAll(arquivoMotos.lerArquivo(vendedores,clientes));
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
				Carro novoCarro = arquivoCarros.escreverArquivo(vendedores,carros);
				if (novoCarro != null) {
					carros.add(novoCarro);
				}
				opcao = mostrarMenu();
				break;
			case "6":
				Moto novaMoto = arquivoMotos.escreverArquivo(vendedores,motos);
				if (novaMoto != null) {
					motos.add(novaMoto);
				}
				opcao = mostrarMenu();
				break;
			case "7":
				Cliente novoCliente = arquivoClientes.escreverArquivo();
				if (novoCliente != null) {
					clientes.add(novoCliente);
				}
				opcao = mostrarMenu();
				break;
			case "8":
				Vendedor novoVendedor = arquivoVendedores.escreverArquivo();
				if (novoVendedor != null) {
					vendedores.add(novoVendedor);
				}
				opcao = mostrarMenu();
				break;
			case "9":
				System.out.println("Digite o id do carro: ");
				Integer idCarro = Integer.parseInt(scanner.nextLine());
				Optional<Carro> selectedCarroId = carros.stream().filter(carro -> carro.getId().equals(idCarro)).findFirst();
				Integer indiceCarro = carros.indexOf(selectedCarroId.get());
				System.out.println("Digite o cpf do cliente: ");
				String cpfClienteCarro = scanner.nextLine();
				Optional<Cliente> selectedCarroCliente = clientes.stream().filter(cliente -> cliente.getCpf().equals(cpfClienteCarro)).findFirst();
				carros.get(indiceCarro).setCliente(selectedCarroCliente.get());
				arquivoCarros.atualizarArquivo(carros);
				opcao = mostrarMenu();
				break;
			case "10":
				System.out.println("Digite o id da moto: ");
				Integer idMoto = Integer.parseInt(scanner.nextLine());
				Optional<Moto> selectedMotoId = motos.stream().filter(moto -> moto.getId().equals(idMoto)).findFirst();
				Integer indiceMoto = motos.indexOf(selectedMotoId.get());
				System.out.println("Digite o cpf do cliente: ");
				String cpfClienteMoto = scanner.nextLine();
				Optional<Cliente> selectedMotoCliente = clientes.stream().filter(cliente -> cliente.getCpf().equals(cpfClienteMoto)).findFirst();
				motos.get(indiceMoto).setCliente(selectedMotoCliente.get());
				arquivoMotos.atualizarArquivo(motos);
				opcao = mostrarMenu();
				break;
			}
		}
		if (opcao.toUpperCase().equals("S")) {
			arquivoVendedores.fecharLeitor();
			arquivoClientes.fecharLeitor();
			arquivoCarros.fecharLeitor();
			arquivoMotos.fecharLeitor();
			System.out.println("Fim da execução");
		}
	}
}
	
	
	
	
	
	

