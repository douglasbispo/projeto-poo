package app.controllers;

import java.util.InputMismatchException;
import java.util.Scanner;

import app.MainProgram;
import entities.Cart;
import entities.Product;

public class CartController {
	private static Scanner input = new Scanner(System.in);
	public static Cart cart = new Cart();
	
	// menu
	public static void cartMenu() {
		try {
			System.out.println("--------- Digite uma opção abaixo ----------");
			System.out.println("--------------------------------------------");
			System.out.print("| Opção 1 - Continuar comprando            |\r\n"
					+ "| Opção 2 - Editar quantidade              |\r\n"
					+ "| Opção 3 - Remover do carrinho            |\r\n"
					+ "| Opção 4 - Comprar                        |\r\n"
					+ "--------------------------------------------\r\n" + ">> ");
			int option = input.nextInt();
			System.out.println("");

			switch (option) {
			case 1:
				System.out.println("");
				ProductController.showProducts();
			case 2:
				editCartItem();
				break;
			case 3:
				removeItemFromCart();
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

		for (Product p : ProductController.productList) {
			if (p.getIdProduct() == id) {
				System.out.print("Digite a quantidade: ");
				Integer amount = input.nextInt();
				if (cart.include(p, amount)) {
					System.out.println("\nProduto adicionado ao carrinho com sucesso!\n\n");
				} else {
					System.out.println("\nProduto já existe no carrinho!\n\n");
				}
			}
		}
		ProductController.showProducts();
	}

	// Mostrar carrinho
	public static void showCart() {
		cart.displayProducts();

		if (cart.getProducts().size() > 0) {
			cartMenu();
		} else {
			MainProgram.mainMenu();
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
	public static void removeItemFromCart() {
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
