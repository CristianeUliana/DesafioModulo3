package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import entidades.Cliente;

public class ArquivoClientes {
	
	private BufferedWriter buffWrite;
	private BufferedReader buffRead;
	
	public ArquivoClientes(String path) throws IOException {
		buffWrite = new BufferedWriter(new FileWriter(path, true));
		buffRead = new BufferedReader(new FileReader(path));
	}
	
	@SuppressWarnings("resource")
	public Cliente escreverArquivo() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o nome do cliente: ");
		String nomeCliente = scanner.nextLine();
		System.out.println("Digite o cpf do cliente: ");
		String cpfCliente = scanner.nextLine();
		System.out.println("Digite o endereco do cliente: ");
		String enderecoCliente = scanner.nextLine();
		Cliente cliente = new Cliente(nomeCliente, cpfCliente, enderecoCliente);
				
		try {
			buffWrite.append(nomeCliente + ";" + cpfCliente + ";" +  enderecoCliente);
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
		return cliente;
	}

	public void fecharLeitor() {
		try {
			buffWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Cliente> lerArquivo() {
		String[] linha;
		String dados = "";
		ArrayList<Cliente> clientes = new ArrayList<>();
		try { 
			while (true) {
				if (dados != null) {
					linha = dados.split(";");
					if(dados.length() > 1) {
						String nomeCliente = linha[0];
						String cpfCliente = linha[1];
						String enderecoCliente = linha[2];
						Cliente cliente = new Cliente(nomeCliente, cpfCliente, enderecoCliente);
						clientes.add(cliente);						
					}
				} else
					break;
				dados = buffRead.readLine();
			}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		
		return clientes;
		}
	}
