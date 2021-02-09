package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import entidades.Vendedor;

public class ArquivoVendedores {
	
	private BufferedWriter buffWrite;
	private BufferedReader buffRead;
	
	public ArquivoVendedores(String path) throws IOException {
		buffWrite = new BufferedWriter(new FileWriter(path, true));
		buffRead = new BufferedReader(new FileReader(path));
	}
	
	@SuppressWarnings("resource")
	public Vendedor escreverArquivo() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o nome do vendedor: ");
		String nomeVendedor = scanner.nextLine();
		System.out.println("Digite o cpf do vendedor: ");
		String cpfVendedor = scanner.nextLine();
		System.out.println("Digite a matricula do vendedor: ");
		Integer matriculaVendedor = Integer.parseInt(scanner.nextLine());
		Vendedor vendedor = new Vendedor(nomeVendedor, cpfVendedor, matriculaVendedor);
				
		try {
			buffWrite.append(nomeVendedor + ";" + cpfVendedor + ";" +  matriculaVendedor);
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
		return vendedor;
	}

	public void fecharLeitor() {
		try {
			buffWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Vendedor> lerArquivo() {
		String[] linha;
		String dados = "";
		ArrayList<Vendedor> vendedores = new ArrayList<>();
		try { 
			while (true) {
				if (dados != null) {
					linha = dados.split(";");
					if(dados.length() > 1) {
						String nomeVendedor = linha[0];
						String cpfVendedor = linha[1];
						Integer matriculaVendedor = Integer.parseInt(linha[2]);
						Vendedor vendedor = new Vendedor(nomeVendedor, cpfVendedor, matriculaVendedor);
						vendedores.add(vendedor);						
					}
				} else
					break;
				dados = buffRead.readLine();
			}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		
		return vendedores;
		}
	}

	
