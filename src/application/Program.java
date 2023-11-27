package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import entities.Cart;
import entities.Product;
import entities.user.Address;
import entities.user.Client;

public class Program {
	private static Scanner input = new Scanner(System.in);
	private static Cart cart = new Cart();
	private static Product product = new Product();
	private static Client client = new Client();
	private static Address address = new Address();
	private static List<Product> productList = new ArrayList<Product>();
	private static List<Client> clientList = new ArrayList<Client>();

	public static void main(String[] args) {
		Product p1 = new Product("Notebook", 3000.0, "Ryzen 5 5500u, 8 gb ram ddr4, ssd 256gb", 10);
		Product p2 = new Product("Memória ram", 200.0, "Ddr4 8Gb 3600MHz", 20);
		
		productList.add(p1);
		productList.add(p2);
		
		Address address = new Address("55292400", "Rua A", 50, "Boa vista", "Garahuns");
		Client c1 = new Client("Douglas", "111.111.111-11", address, "douglas", "123");
		
		clientList.add(c1);
		
		//String email = JOptionPane.showInputDialog("Email: ");
		
		mainMenu();
		//homeMenu();
	}
	
	// menu principal 
	private static void mainMenu() {
		try {
			System.out.println("--------------------------------------------");
			System.out.println("|              MENU PRINCIPAL              |");
			System.out.println("--------------------------------------------");
			System.out.println("--------- Digite uma opção abaixo ----------");
			System.out.println("--------------------------------------------");
			System.out.print("| Opção 1 - Cadastrar produto              |\r\n"+
							 "| Opção 2 - Lista de produtos              |\r\n"+
							 "| Opção 3 - Ver carrinho                   |\r\n"+
							 "| Opção 4 -                                |\r\n"+
							 "| Opção 5 - Logout                           |\r\n"+
							 "--------------------------------------------\r\n"+
					 		 ">> ");
			int option = input.nextInt();
			System.out.println("");
			
			switch (option) {
			case 1:
				createProduct();
				break;
			case 2:
				System.out.println("");
				showProducts();
				break;
			case 3:
				System.out.println("");
				showCart();
				break;
			case 4:
				break;
			case 5:
				System.out.print("Tem certeza que deseja sair (S/N): ");
				String logout = input.next();
				
				if (logout.toUpperCase().equals("S")) {
					System.out.println("\nVocê saiu");
					System.out.println("\n");
					homeMenu();					
				} else {
					System.out.println("\n");
					mainMenu();
				}
			default:
				System.out.println("Opção Invalida!\r\n");
				mainMenu();
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Por favor, digite um número!");
		}
		
	}
	
	// ---------------------- métodos do Usuário ----------------------
	public static void homeMenu() {
		try {
			System.out.println("--------------------------------------------");
			System.out.println("|                BEM-VINDO                 |");
			System.out.println("--------------------------------------------");
			System.out.println("------- Crie uma conta ou faça login -------");
			System.out.println("--------------------------------------------");
			System.out.print("| Opção 1 - Cadastro                       |\r\n"+
							 "| Opção 2 - Login                          |\r\n"+
							 "| Opção 3 - Encerrar                       |\r\n"+
							 "--------------------------------------------\r\n"+
					 		 ">> ");
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
				mainMenu();
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
				mainMenu();
			}
		}
		System.out.println("Email ou senha incorreto!");
		System.out.println("____________________________________________\n\n\n");
		homeMenu();
	}
	
	// ---------------------- métodos do produto ----------------------
	public static void productMenu() {
		try {
			System.out.println("--------- Digite uma opção abaixo ----------");
			System.out.println("--------------------------------------------");
			System.out.print("| Opção 1 - Adicionar ao carrinho          |\r\n"+
							 "| Opção 2 - Ver carrinho                   |\r\n"+
							 "| Opção 3 - Editar produto                 |\r\n"+
							 "| Opção 4 - Excluir produto                |\r\n"+
							 "| Opção 5 - Voltar ao menu principal       |\r\n"+
							 "--------------------------------------------\r\n"+
							 ">> ");
			int option = input.nextInt();
			System.out.println("");
			
			switch (option) {
			case 1:
				addCart();
				break;
			case 2:
				System.out.println("");
				showCart();
				break;
			case 3:
				break;
			case 4:
				deleteProduct();
				break;
			case 5:
				System.out.println("");
				mainMenu();
				break;
			default:
				System.out.println("Opção Invalida!\r\n");
				System.out.println("--------------------------------------------");
				cartMenu();
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Por favor, digite um número!");
		}
	}
	
	// Criar produto
	public static void createProduct() {
		System.out.println("DIGITE OS DADOS DO PRODUTO");
		System.out.print("Nome: ");
		String name = input.next();
		System.out.print("Preço: ");
		Double price = input.nextDouble();
		System.out.print("Descrição: ");
		String description = input.next();
		System.out.print("Quantidade: ");
		Integer amount = input.nextInt();
		
		product = new Product(name, price, description, amount);
		productList.add(product);
		System.out.println("\nProduto adicionado com sucesso!\n\n");
		mainMenu();
	}
	
	// Mostrar produto
	public static void showProducts() {
		if (productList.size() > 0) {
			System.out.println("------------ LISTA DE PRODUTOS -------------");
			for (Product p : productList) {
				System.out.println(p + "\n");
			}
			productMenu();
		} else {
			System.out.println("Nenhum produto cadastrado!\n\n");
			mainMenu();
		}
	} 
	
	// Editar produto
	public static void editProduct() {
		
	}
	
	// Deletar produto
	public static void deleteProduct() {
		System.out.print("Digite o ID do produto que deseja excluir: ");
		Integer id = input.nextInt();
		System.out.println("");
		
		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).getIdProduct() == id) {
				productList.remove(i);
				System.out.println("Produto excluido com sucesso!\n\n");
			}
		}
		showProducts();
	}
	
	// ---------------------- métodos do carrinho ----------------------
	public static void cartMenu() {
		try {
			System.out.println("--------- Digite uma opção abaixo ----------");
			System.out.println("--------------------------------------------");
			System.out.print("| Opção 1 - Continuar comprando            |\r\n"+
							 "| Opção 2 - Editar quantidade              |\r\n"+
							 "| Opção 3 - Remover do carrinho            |\r\n"+
							 "| Opção 4 - Comprar                        |\r\n"+
							 "--------------------------------------------\r\n"+
							 ">> ");
			int option = input.nextInt();
			System.out.println("");
			
			switch (option) {
			case 1:
				System.out.println("");
				showProducts();
			case 2:
				editCartItem();
				break;
			case 3:
				deleteCartItem();
				break;
			case 4:
				break;
			default:
				System.out.println("Opção Invalida!\r\n");
				System.out.println("--------------------------------------------");
				cartMenu();
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Por favor, digite um número!");
		}
	}
	
	// Adicionar ao carrinho
	public static void addCart() {
		System.out.print("Digite o ID do produto que deseja adicionar: ");
		Integer id = input.nextInt();
		
		for (Product p : productList) {
			if (p.getIdProduct() == id) {
				System.out.print("Digite a quantidade: ");
				Integer amount = input.nextInt();
				if(cart.include(p, amount)) {
					System.out.println("\nProduto adicionado ao carrinho com sucesso!\n\n");
				} else {
					System.out.println("\nProduto já existe no carrinho!\n\n");
				}
			}
		}
		showProducts();
	}
	
	// Mostrar carrinho
	public static void showCart() {
		cart.displayProducts();
		
		if (cart.getProducts().size() > 0) {
			cartMenu();
		} else {
			mainMenu();
		}
	}
	
	// Editar quantidade
	public static void editCartItem() {
		System.out.print("Digite o ID do produto que deseja editar: ");
		Integer id = input.nextInt();
		System.out.print("Digite a nova quantidade: ");
		Integer amount = input.nextInt();
		System.out.println("");
		
		if (cart.editQuantity(id, amount)) {
			System.out.println("Quantidade editada com sucesso!\n\n");
		} else {
			System.out.println("Produto com id digitado não encontrado!\n\n");
		}
		showCart();
	}
	
	// Deletar produto do carrinho
	public static void deleteCartItem() {
		System.out.print("Digite o ID do produto que deseja remover: ");
		Integer id = input.nextInt();
		System.out.println("");
		
		if (cart.remove(id)) {
			System.out.println("Produto excluído com sucesso!\n\n");
		} else {
			System.out.println("Produto com id digitado não encontrado!\n\n");
		}
		showCart();
	}

}
