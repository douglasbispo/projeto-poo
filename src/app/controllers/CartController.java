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
					+ "| Opção 5 - Limpar carrinho                |\r\n"
					+ "--------------------------------------------\r\n" + ">> ");
			int option = input.nextInt();
			System.out.println("");

			switch (option) {
			case 1:
				ProductController.showProducts();
			case 2:
				editCartItem();
				break;
			case 3:
				removeItemFromCart();
				break;
			case 4:
				PaymentController.paymentMenu();
				break;
			case 5:
				cleanCart();
				break;
			default:
				System.out.println("Opção Invalida!\r\n");
				System.out.println("--------------------------------------------");
				cartMenu();
				break;
			}
		} catch (InputMismatchException e) {
			System.err.println("\nPor favor, digite um número!\n");
			input.nextLine();
			cartMenu();
		}
	}

	// Método para verificar se o produto existe no carrinho
	public static boolean checkProductExistsInCart(int id) {
		for (Product p : cart.getProducts().keySet()) {
			if (p.getIdProduct() == id) {
				return true;
			}
		}
		return false;
	}
	
	// Adicionar ao carrinho
	public static void addCart() {
		try {
			System.out.print("Digite o ID do produto que deseja adicionar: ");
			Integer id = input.nextInt();

			if (ProductController.checkProductExists(id)) {
				for (Product p : ProductController.productList) {
					if (p.getIdProduct() == id) {
						System.out.print("Digite a quantidade: ");
						Integer amount = input.nextInt();
						if (cart.include(p, amount)) {
							System.out.println("\nProduto adicionado ao carrinho com sucesso!\n");
						} else {
							System.out.println("\nProduto já existe no carrinho!\n");
						}
					}
				}
			} else {
				System.err.println("\nProduto com o ID digitado não existe!\n");
			}
		} catch (InputMismatchException e) {
			System.err.println("\nPor favor, digite um número!\n");
			input.nextLine();
			addCart();
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
		try {
			System.out.print("Digite o ID do produto que deseja editar: ");
			Integer id = input.nextInt();

			if (checkProductExistsInCart(id)) {
				
				System.out.print("Digite a nova quantidade: ");
				Integer amount = input.nextInt();
				System.out.println("");

				if (cart.editQuantity(id, amount)) {
					System.out.println("Quantidade editada com sucesso!\n\n");
				} 
			} else {
				System.err.println("\nProduto com o ID digitado não existe no carrinho!\n");
			}
		} catch (InputMismatchException e) {
			System.err.println("\nPor favor, digite um número!\n");
			input.nextLine();
			editCartItem();
		}
		showCart();
	}

	// Deletar produto do carrinho
	public static void removeItemFromCart() {
		try {
			System.out.print("Digite o ID do produto que deseja remover: ");
			Integer id = input.nextInt();
			System.out.println("");

			if (checkProductExistsInCart(id)) {
				if (cart.remove(id)) {
					System.out.println("Produto excluído com sucesso!\n\n");
				}
			} else {
				System.err.println("Produto com o ID digitado não existe no carrinho!\n");
			}
		} catch (InputMismatchException e) {
			System.err.println("\nPor favor, digite um número!\n");
			input.nextLine();
			removeItemFromCart();
		}
		showCart();
	}
	
	// Limpar carrinho
	public static void cleanCart() {
		try {
			cart.cleanCart();
			System.out.println("\nTodos os itens foram removidos do carrinho!\n");			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		MainProgram.mainMenu();
	}
}
