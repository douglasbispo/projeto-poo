package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import entities.Cart;
import entities.Product;

public class Program {
	private static Scanner input = new Scanner(System.in);
	private static Cart cart = new Cart();
	private static Product product = new Product();
	private static List<Product> productList = new ArrayList<Product>();

	public static void main(String[] args) {
		
		
		Product p1 = new Product("Notebook", 5000.0, "Descrição", 10);
		Product p2 = new Product("Ram", 400.0, "Ddr4 8Gb", 20);
		
		productList.add(p1);
		productList.add(p2);
		
		//cart.include(p1, 2);
		//cart.include(p2, 3);
		
		mainMenu();
	}
	
	private static void mainMenu() {
		try {
			System.out.println("--------------------------------------------");
			System.out.println("|              MENU PRINCIPAL              |");
			System.out.println("--------------------------------------------");
			System.out.println("--------- Digite uma opção abaixo ----------");
			System.out.println("--------------------------------------------");
			System.out.print("| Opção 1 - Adicionar produto              |\r\n"+
							 "| Opção 2 - Lista de produtos              |\r\n"+
							 "| Opção 3 - Ver carrinho                   |\r\n"+
							 "| Opção 4 -                                |\r\n"+
							 "| Opção 0 - Sair                           |\r\n"+
							 "--------------------------------------------\r\n"+
					 		 ">> ");
			int option = input.nextInt();
			System.out.println("");
			
			switch (option) {
			case 1:
				createProduct();
				break;
			case 2:
				showProducts();
				break;
			case 3:
				showCart();
				break;
			case 4:
				break;
			case 0:
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
	
	// métodos do produto
	public static void productMenu() {
		try {
			System.out.println("--------- Digite uma opção abaixo ----------");
			System.out.println("--------------------------------------------");
			System.out.print("| Opção 1 - Adicionar ao carrinho          |\r\n"+
							 "| Opção 2 - Ver carrinho                   |\r\n"+
							 "| Opção 3 - Editar produto                 |\r\n"+
							 "| Opção 4 - Remover produto                |\r\n"+
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
				showCart();
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
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
		
		productList.add(product = new Product(name, price, description, amount));
		System.out.println("\nProduto adicionado com sucesso!\n");
		mainMenu();
	}
	
	public static void showProducts() {
		if (productList.size() > 0) {
			System.out.println("------------ LISTA DE PRODUTOS -------------");
			for (Product p : productList) {
				System.out.println(p + "\n");
			}
			productMenu();
		} else {
			System.out.println("Nenhum peduto cadastrado!\n");
			mainMenu();
		}
	}
	
	// métodos do carrinho
	public static void cartMenu() {
		try {
			System.out.println("--------- Digite uma opção abaixo ----------");
			System.out.println("--------------------------------------------");
			System.out.print("| Opção 1 - Continuar comprando            |\r\n"+
							 "| Opção 2 - Editar quantidade              |\r\n"+
							 "| Opção 3 - Remove do carrinho             |\r\n"+
							 "| Opção 4 - Comprar                        |\r\n"+
							 "--------------------------------------------\r\n"+
							 ">> ");
			int option = input.nextInt();
			System.out.println("");
			
			switch (option) {
			case 1:
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
	
	public static void addCart() {
		System.out.print("Digite o ID do produto que deseja adicionar: ");
		Integer id = input.nextInt();
		
		for (Product p : productList) {
			if (p.getIdProduct() == id) {
				System.out.print("Digite a quantidade: ");
				Integer amount = input.nextInt();
				if(cart.include(p, amount)) {
					System.out.println("\nProduto adicionado ao carrinho com sucesso!\n");
				} else {
					System.out.println("\nProduto já existe no carrinho!\n");
				}
			}
		}
		showProducts();
	}
	
	public static void showCart() {
		cart.displayProducts();
		
		if (cart.getProducts().size() > 0) {
			cartMenu();
		} else {
			mainMenu();
		}
	}
	
	public static void editCartItem() {
		System.out.print("Digite o ID do produto que deseja editar: ");
		Integer id = input.nextInt();
		System.out.print("Digite a nova quantidade: ");
		Integer amount = input.nextInt();
		System.out.println("");
		
		if (cart.editQuantity(id, amount)) {
			System.out.println("Quantidade editada com sucesso!\n");
		} else {
			System.out.println("Produto com id digitado não encontrado!\n");
		}
		showCart();
	}
	
	public static void deleteCartItem() {
		System.out.print("Digite o ID do produto que deseja editar: ");
		Integer id = input.nextInt();
		System.out.println("");
		
		if (cart.remove(id)) {
			System.out.println("Produto excluído com sucesso!\n");
		} else {
			System.out.println("Produto com id digitado não encontrado!\n");
		}
		showCart();
	}

}
