package app.controllers;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import app.MainProgram;
import entities.Product;

public class ProductController {
	private static Scanner input = new Scanner(System.in);
	private static Product product = new Product();
	public static List<Product> productList = new ArrayList<Product>();
	
	// Menu
	public static void productMenu() {
		try {
			System.out.println("--------- Digite uma opção abaixo ----------");
			System.out.println("--------------------------------------------");
			System.out.print("| Opção 1 - Adicionar ao carrinho          |\r\n"
					+ "| Opção 2 - Ver carrinho                   |\r\n"
					+ "| Opção 3 - Editar produto                 |\r\n"
					+ "| Opção 4 - Excluir produto                |\r\n"
					+ "| Opção 5 - Voltar ao menu principal       |\r\n"
					+ "--------------------------------------------\r\n" + ">> ");
			int option = input.nextInt();
			System.out.println("");

			switch (option) {
			case 1:
				CartController.addCart();
				break;
			case 2:
				CartController.showCart();
				break;
			case 3:
				editProduct();
				break;
			case 4:
				deleteProduct();
				break;
			case 5:
				MainProgram.mainMenu();
				break;
			default:
				System.out.println("Opção Invalida!\r\n");
				System.out.println("--------------------------------------------");
				CartController.cartMenu();
				break;
			}
		} catch (InputMismatchException e) {
			System.err.println("\nPor favor, digite um número!\n");
			input.nextLine();
			productMenu();
		}
	}

	// Método para verificar se o produto existe
	public static boolean checkProductExists(int id) {
		for (Product p : productList) {
			if (p.getIdProduct() == id) {
				return true;
			}
		}
		return false;
	}

	// Criar produto
	public static void createProduct() {
		try {
			System.out.println("DIGITE OS DADOS DO PRODUTO");
			System.out.print("Nome: ");
			String name = input.nextLine();
			System.out.print("Preço: ");
			Double price = input.nextDouble();
			input.nextLine();
			System.out.print("Descrição: ");
			String description = input.nextLine();

			product = new Product(name, price, description);
			productList.add(product);
			System.out.println("\nProduto adicionado com sucesso!\n");
			MainProgram.mainMenu();
		} catch (InputMismatchException e) {
			input.nextLine();
			System.err.println("\nPor favor, digite um número!\n");
			createProduct();
		}
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
			System.out.println("Nenhum produto cadastrado!\n");
			MainProgram.mainMenu();
		}
	}

	// Editar produto
	public static void editProduct() {
		try {
			System.out.print("Digite o ID do produto que deseja editar: ");
			Integer id = input.nextInt();
			System.out.println("");
			
			if (checkProductExists(id)) {
				for (int i = 0; i < productList.size(); i++) {
					if (productList.get(i).getIdProduct() == id) {
						System.out.println("DIGITE OS DADOS DO PRODUTO");
						input.nextLine();
						System.out.print("Nome: ");
						String name = input.nextLine();
						System.out.print("Preço: ");
						Double price = input.nextDouble();
						input.nextLine();
						System.out.print("Descrição: ");
						String description = input.nextLine();

						productList.get(i).setName(name);
						productList.get(i).setPrice(price);
						productList.get(i).setDescription(description);

						// remover o produto do carrinho após atualizar
						CartController.cart.remove(id);
						System.out.println("Produto atualizado com sucesso!\n");
					}
				}
			} else {
				System.err.println("Produto com o ID digitado não existe!\n");
			}
		} catch (InputMismatchException e) {
			System.err.println("\nPor favor, digite um número!\n");
			input.nextLine();
			editProduct();
		}
		showProducts();
	}

	// Deletar produto
	public static void deleteProduct() {
		try {
			System.out.print("Digite o ID do produto que deseja excluir: ");
			Integer id = input.nextInt();
			System.out.println("");

			if (checkProductExists(id)) {
				for (int i = 0; i < productList.size(); i++) {
					if (productList.get(i).getIdProduct() == id) {
						productList.remove(i);

						// remover o produto também do carrinho
						CartController.cart.remove(id);
						System.out.println("Produto excluido com sucesso!\n");
					}
				}
			} else {
				System.err.println("Produto com o ID digitado não existe!\n");
			}
		} catch (InputMismatchException e) {
			System.err.println("\nPor favor, digite um número!\n");
			input.nextLine();
			deleteProduct();
		}
		showProducts();
	}

}
