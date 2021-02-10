package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import entidades.Carro;
import entidades.Cliente;
import entidades.Vendedor;


public class ArquivoCarros {

	private String filePath;
	private BufferedWriter buffWrite;
	private BufferedReader buffRead;
	
	public ArquivoCarros(String path) {
		this.filePath = path;
	}
	
	private void generateFileManagement(boolean keepFile) {
		try {
			buffWrite = new BufferedWriter(new FileWriter(filePath, keepFile));
			buffRead = new BufferedReader(new FileReader(filePath));			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void closeFileManagement() {
		try {
			buffWrite.close();
			buffRead.close();			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@SuppressWarnings("resource")
	public Carro escreverArquivo(ArrayList<Vendedor> vendedores, ArrayList<Carro> carros) {
		Scanner scanner = new Scanner(System.in);
		Integer id;
		if (carros == null) {
			id = 1;
		} else {
			id = carros.size()+1;
		}
		System.out.println("Digite o modelo do carro: ");
		String modelo = scanner.nextLine();
		System.out.println("Digite a marca do carro: ");
		String marca = scanner.nextLine();
		System.out.println("Digite o ano do carro: ");
		Integer ano = Integer.parseInt(scanner.nextLine());
		System.out.println("Digite o valor do carro: ");
		Double valor = Double.parseDouble(scanner.nextLine());
		System.out.println("Digite a matrícula do vendedor: ");
		Integer matriculaVendedor = Integer.parseInt(scanner.nextLine());
		Optional<Vendedor> selected = vendedores.stream().filter(vendedor -> vendedor.getMatricula().equals(matriculaVendedor)).findFirst();
		Carro carro = new Carro(id, modelo, marca, ano, valor, selected.get());
		
		generateFileManagement(true);
		try {
			buffWrite.append(id + ";" + modelo + ";" + marca + ";" +  ano + ";" + valor + ";" + selected.get().getMatricula());
			buffWrite.newLine();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			closeFileManagement();					
		}
		return carro;
	}

	
	
	
	public void atualizarArquivo(ArrayList<Carro> carros) {
		generateFileManagement(false);
		try {
			for (int i=0; i<carros.size(); i++) {
				StringBuilder novoRegistro = new StringBuilder();
				Integer id = carros.get(i).getId();
				String modelo = carros.get(i).getModelo();
				String marca = carros.get(i).getMarca();
				Integer ano = carros.get(i).getAno();
				Double valor = carros.get(i).getValor();
				Vendedor vendedorResponsavel = carros.get(i).getVendedorResponsavel();
				novoRegistro.append(id).append(";");
				novoRegistro.append(modelo).append(";");
				novoRegistro.append(marca).append(";");
				novoRegistro.append(ano).append(";");
				novoRegistro.append(valor).append(";");
				novoRegistro.append(vendedorResponsavel.getMatricula());
				
				Cliente cliente = carros.get(i).getCliente();
				if (cliente != null) {
					novoRegistro.append(";").append(cliente.getCpf());
				}
				buffWrite.append(novoRegistro.toString());
				buffWrite.newLine();
			}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			closeFileManagement();
		}
	}
	
	
	public ArrayList<Carro> lerArquivo(ArrayList<Vendedor> vendedores, ArrayList<Cliente> clientes) {
		String[] linha;
		String dados = "";
		ArrayList<Carro> carros = new ArrayList<>();
		generateFileManagement(true);
		try { 
			while (true) {
				if (dados != null) {
					linha = dados.split(";");
					if(dados.length() > 1) {
						Integer id = Integer.parseInt(linha[0]);
						String modelo = linha[1];
						String marca = linha[2];
						Integer ano = Integer.parseInt(linha[3]);
						Double valor = Double.parseDouble(linha[4]);
						Integer matriculaVendedor = Integer.parseInt(linha[5]);
						Optional<Vendedor> selected = vendedores.stream().filter(vendedor -> vendedor.getMatricula().equals(matriculaVendedor)).findFirst();
						Carro carro = new Carro(id, modelo, marca, ano, valor, selected.get());
						
						if (linha.length == 7) {
							String cpfCliente = linha[6];
							Optional<Cliente> clientSelected = clientes.stream().filter(cliente -> cliente.getCpf().equals(cpfCliente)).findFirst();
							carro.setCliente(clientSelected.get());
						}
						carros.add(carro);						
					}
				} else
					break;
				dados = buffRead.readLine();
			}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} finally {
				closeFileManagement();
			}
			
		
		return carros;
		}
}
