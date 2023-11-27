package app.controllers;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import app.MainProgram;
import entities.user.Address;
import entities.user.Client;

public class UserController {
	private static Scanner input = new Scanner(System.in);
	private static Client client = new Client();
	private static Address address = new Address();
	public static List<Client> clientList = new ArrayList<Client>();
	
	// menu
	public static void homeMenu() {
		try {
			System.out.println("--------------------------------------------");
			System.out.println("|                BEM-VINDO                 |");
			System.out.println("--------------------------------------------");
			System.out.println("------- Crie uma conta ou faça login -------");
			System.out.println("--------------------------------------------");
			System.out.print("| Opção 1 - Cadastro                       |\r\n"
					+ "| Opção 2 - Login                          |\r\n"
					+ "| Opção 3 - Encerrar                       |\r\n"
					+ "--------------------------------------------\r\n" + ">> ");
			int option = input.nextInt();
			System.out.println("");

			switch (option) {
			case 1:
				createClient();
				break;
			case 2:
				login();
				break;
			case 3:
				System.out.println("Progrma encerrado!");
				System.exit(option);
			default:
				System.out.println("Opção Invalida!\r\n");
				MainProgram.mainMenu();
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Por favor, digite um número!");
		}
	}

	// Cadastrar cliente
	public static void createClient() {
		System.out.println("DIGITE OS DADOS DO USUÁRIO");
		System.out.print("Nome: ");
		String name = input.next();
		System.out.print("CPF: ");
		String cpf = input.next();
		System.out.print("E-mail: ");
		String email = input.next();
		System.out.print("Senha: ");
		String password = input.next();

		System.out.println("\nEdereço");
		System.out.print("CEP: ");
		String cep = input.next();
		System.out.print("Rua: ");
		String street = input.next();
		System.out.print("Número: ");
		Integer number = input.nextInt();
		System.out.print("Bairro: ");
		String neighborhood = input.next();
		System.out.print("Cidade: ");
		String city = input.next();

		address = new Address(cep, street, number, neighborhood, city);
		client = new Client(name, cpf, address, email, password);
		clientList.add(client);
		System.out.println("\nUsuário cadastrado com sucesso!\n");
		homeMenu();
	}

	// Fazer login
	public static void login() {
		System.out.println("____________________________________________");
		System.out.println("_______________ MENU LOGIN _________________\n");
		System.out.print("E-mail: ");
		String email = input.next();
		System.out.print("Senha: ");
		String password = input.next();
		System.out.println("____________________________________________\n");

		for (Client c : clientList) {
			if (c.getEmail().toUpperCase().equals(email.toUpperCase()) && c.getPassword().equals(password)) {
				System.out.println("Login realizado com sucesso!");
				System.out.println("____________________________________________\n\n\n");
				MainProgram.mainMenu();
			}
		}
		System.out.println("Email ou senha incorreto!");
		System.out.println("____________________________________________\n\n\n");
		homeMenu();
	}
}
