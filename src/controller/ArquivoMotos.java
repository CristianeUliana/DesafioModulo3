package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import entidades.Cliente;
import entidades.Moto;
import entidades.Vendedor;

public class ArquivoMotos {

	private BufferedWriter buffWrite;
	private BufferedWriter buffWriteReset;
	private BufferedReader buffRead;
	
	public ArquivoMotos(String path) throws IOException {
		buffWrite = new BufferedWriter(new FileWriter(path, true));
		buffWriteReset = new BufferedWriter(new FileWriter(path, true));
		buffRead = new BufferedReader(new FileReader(path));
	}
	
	@SuppressWarnings("resource")
	public Moto escreverArquivo(ArrayList<Vendedor> vendedores,ArrayList<Moto> motos) {
		Scanner scanner = new Scanner(System.in);
		Integer id;
		if (motos == null) {
			id = 1;
		} else {
			id = motos.size()+1;
		}
		System.out.println("Digite o modelo da moto: ");
		String modelo = scanner.nextLine();
		System.out.println("Digite a marca da moto: ");
		String marca = scanner.nextLine();
		System.out.println("Digite o ano da moto: ");
		Integer ano = Integer.parseInt(scanner.nextLine());
		System.out.println("Digite o valor da moto: ");
		Double valor = Double.parseDouble(scanner.nextLine());
		System.out.println("Digite a matrícula do vendedor: ");
		Integer matriculaVendedor = Integer.parseInt(scanner.nextLine());
		Optional<Vendedor> selected = vendedores.stream().filter(vendedor -> vendedor.getMatricula().equals(matriculaVendedor)).findFirst();
		Moto moto = new Moto(id, modelo, marca, ano, valor, selected.get());
		
		try {
			buffWrite.append(modelo + ";" + marca + ";" +  ano + ";" + valor + ";" + selected.get().getMatricula());
			buffWrite.newLine();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				buffWrite.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}						
		}
		return moto;
	}

	public void fecharLeitor() {
		try {
			buffWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void atualizarArquivo(ArrayList<Moto> motos) {
		try {
			for (int i=0; i<motos.size(); i++) {
				StringBuilder novoRegistro = new StringBuilder();
				Integer id = motos.get(i).getId();
				String modelo = motos.get(i).getModelo();
				String marca = motos.get(i).getMarca();
				Integer ano = motos.get(i).getAno();
				Double valor = motos.get(i).getValor();
				Vendedor vendedorResponsavel = motos.get(i).getVendedorResponsavel();
				novoRegistro.append(id).append(";");
				novoRegistro.append(modelo).append(";");
				novoRegistro.append(marca).append(";");
				novoRegistro.append(ano).append(";");
				novoRegistro.append(valor).append(";");
				novoRegistro.append(vendedorResponsavel.getMatricula()).append(";");
				
				Cliente cliente = motos.get(i).getCliente();
				if (cliente != null) {
					novoRegistro.append(cliente.getCpf());
				}
				buffWriteReset.append(novoRegistro.toString());
				buffWriteReset.newLine();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				buffWrite.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Moto> lerArquivo(ArrayList<Vendedor> vendedores, ArrayList<Cliente> clientes) {
		String[] linha;
		String dados = "";
		ArrayList<Moto> motos = new ArrayList<>();
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
						Moto moto = new Moto(id, modelo, marca, ano, valor, selected.get());
						if (linha.length == 7) {
							String cpfCliente = linha[6];
							Optional<Cliente> clientSelected = clientes.stream().filter(cliente -> cliente.getCpf().equals(cpfCliente)).findFirst();
							moto.setCliente(clientSelected.get());
						}
						motos.add(moto);						
					}
				} else
					break;
				dados = buffRead.readLine();
			}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		
		return motos;
		}
	
}
