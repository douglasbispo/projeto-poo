package app;

import java.util.InputMismatchException;
import java.util.Scanner;

import app.controllers.CartController;
import app.controllers.ProductController;
import app.controllers.UserController;
import entities.Product;
import entities.user.Address;
import entities.user.Client;

public class MainProgram {
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		Product p1 = new Product("Notebook", 3000.0, "Ryzen 5 5500u, 8 gb ram ddr4, ssd 256gb", 10);
		Product p2 = new Product("Memória ram", 200.0, "Ddr4 8Gb 3600MHz", 20);
		
		ProductController.productList.add(p1);
		ProductController.productList.add(p2);
		
		Address address = new Address("55292400", "Rua A", 50, "Boa vista", "Garahuns");
		Client c1 = new Client("Douglas", "111.111.111-11", address, "douglas", "123");
		
		UserController.clientList.add(c1);
		//String email = JOptionPane.showInputDialog("Email: ");
		
		mainMenu();
		//UserController.homeMenu();
	}
	
	// menu principal 
	public static void mainMenu() {
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
							 "| Opção 5 - Logout                         |\r\n"+
							 "--------------------------------------------\r\n"+
					 		 ">> ");
			int option = input.nextInt();
			System.out.println("");
			
			switch (option) {
			case 1:
				ProductController.createProduct();
				break;
			case 2:
				System.out.println("");
				ProductController.showProducts();
				break;
			case 3:
				System.out.println("");
				CartController.showCart();
				break;
			case 4:
				break;
			case 5:
				System.out.print("Tem certeza que deseja sair (S/N): ");
				String logout = input.next();
				
				if (logout.toUpperCase().equals("S")) {
					System.out.println("\nVocê saiu");
					System.out.println("\n");
					UserController.homeMenu();					
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

}
