package app.controllers;

import java.util.InputMismatchException;
import java.util.Scanner;

import app.MainProgram;
import entities.payment.Credit;
import entities.payment.Debit;
import entities.payment.InCash;
import entities.payment.Installment;
import entities.payment.PaymentSlip;
import services.CreditService;
import services.DebitService;
import services.InCashService;
import services.PaymentSlipService;
import services.PaypalService;

public class PaymentController {
	private static Scanner input = new Scanner(System.in);
	
	private static InCashService inCashService = new InCashService();
	private static PaymentSlipService paymentSlipService = new PaymentSlipService();
	private static DebitService debitService = new DebitService();
	private static CreditService creditService = new CreditService(new PaypalService());
	
	private static InCash inCash = new InCash();
	private static PaymentSlip paymentSlip = new PaymentSlip(); 
	private static Debit debit = new Debit();
	private static Credit credit = new Credit();
	

	public static void paymentMenu() {
		if (CartController.cart.getProducts().size() > 0) {
			try {
				System.out.println("--------------------------------------------");
				System.out.println(
						"Valor total da compra: " + utils.Utils.convertCurrency(CartController.cart.totalValue()));
				System.out.println("--------------------------------------------");
				System.out.println("----------- FORMAS DE PAGAMENTOS -----------");
				System.out.println("--------------------------------------------");
				System.out.print("| Opção 1 - À vista com 20% de desconto    |\r\n"
						+ "| Opção 2 - Boleto com 15% ou parcelado    |\r\n"
						+ "| Opção 3 - Débito com 10% de desconto     |\r\n"
						+ "| Opção 4 - Crédito com juros              |\r\n"
						+ "| Opção 5 - Cancelar compra                |\r\n"
						+ "--------------------------------------------\r\n" + ">> ");
				int option = input.nextInt();
				System.out.println("");

				switch (option) {
				case 1:
					inCash();
					break;
				case 2:
					paymentSlip();
					break;
				case 3:
					debit();
					break;
				case 4:
					credit();
					break;
				case 5:
					CartController.cartMenu();
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
				paymentMenu();
			}
		} else {
			System.out.println("\n--------------------------------------------");
			System.out.println("-- Nenhum produto adicionado ao carrinho ---");
			System.out.println("-- Para adicionar vá à lista de produtos ---");
			System.out.println("--------------------------------------------\n\n");
			MainProgram.mainMenu();
		}
	}
	
	// Pagamento à vista
	public static void inCash() {
		inCash = new InCash(CartController.cart.totalValue());

		System.out.println("Total da compra à vista com 20% de desconto: "
				+ utils.Utils.convertCurrency(
						inCashService.processPurchase(inCash)));
		System.out.println("");

		System.out.print("Deseja finalizar a compra? (S/N): ");
		String finish = input.next();
		System.out.println("");

		if (finish.toUpperCase().equals("S")) {
			finalizePurchase();
		} else {
			paymentMenu();
		}
	}
	
	// Pagamento no boleto
	public static void paymentSlip() {
		try {
			paymentSlip = new PaymentSlip(CartController.cart.totalValue());

			System.out.println("No boleto só tem desconto se pagar à vista");
			System.out.print("Deseja parcelar? (S/N): ");
			String installmentOption = input.next();
			System.out.println("");

			if (installmentOption.toUpperCase().equals("S")) {
				System.out.println("O número de parcelas deve ser no mínimo 3 e no máximo 12");
				System.out.print("Digite a quantidade de parcelas: ");
				int months = input.nextInt();
				System.out.println("");

				while (months < 3 || months > 12) {
					System.out.println("quantidade invalida!\n");
					System.out.print("Digite novamente: ");
					months = input.nextInt();
					System.out.println("");
				}

				System.out.print("Deseja adicionar algum valor de entrada? (S/N): ");
				String anticipation = input.next();
				System.out.println("");

				double advanceValue = 0.0;
				if (anticipation.toUpperCase().equals("S")) {
					System.out.println("O valor de entrada deve ser no mínimo "
							+ utils.Utils.convertCurrency(CartController.cart.totalValue() * 0.1) + "(10%) e no máximo "
							+ utils.Utils.convertCurrency(CartController.cart.totalValue() * 0.8) + "(80%)");
					System.out.print("Digite o valor: ");
					advanceValue = input.nextDouble();
					System.out.println("");
				}

				try {
					paymentSlipService.processInstallments(paymentSlip, months, advanceValue);

					System.out.println("Parcelas: ");
					for (Installment installment : paymentSlip.getInstallments()) {
						System.out.println(installment);
					}

					System.out.print("\nDeseja finalizar a compra? (S/N): ");
					String finish = input.next();
					System.out.println("");

					if (finish.toUpperCase().equals("S")) {
						finalizePurchase();
					} else {
						paymentMenu();
					}
				} catch (RuntimeException e) {
					System.out.println(e.getMessage());
					System.out.println("");
					paymentMenu();
				}
			} else {
				System.out.println("Total da compra no boleto com 15% de desconto: "
						+ utils.Utils.convertCurrency(paymentSlipService.processPurchase(paymentSlip)));
				System.out.println("");

				System.out.print("Deseja finalizar a compra? (S/N): ");
				String finish = input.next();
				System.out.println("");

				if (finish.toUpperCase().equals("S")) {
					finalizePurchase();
				} else {
					paymentMenu();
				}
			}
		} catch (InputMismatchException e) {
			System.err.println("\nPor favor, digite um número!\n");
			input.nextLine();
			paymentSlip();
		}
		MainProgram.mainMenu();
	}
	
	// Pagamento no débito
	public static void debit() {
		debit = new Debit(CartController.cart.totalValue());
		
		System.out.println("Total da compra no débito com 10% de desconto: "
				+ utils.Utils.convertCurrency(
						debitService.processPurchase(
								debit)));
		System.out.println("");

		System.out.print("Deseja finalizar a compra? (S/N): ");
		String finish = input.next();
		System.out.println("");

		if (finish.toUpperCase().equals("S")) {
			finalizePurchase();
		} else {
			paymentMenu();
		}
	}
	
	// Pagamento no crédito
	public static void credit() {
		try {
			credit = new Credit(CartController.cart.totalValue());

			System.out.print("Deseja parcelar? (S/N): ");
			String installmentOption = input.next();
			System.out.println("");

			if (installmentOption.toUpperCase().equals("S")) {
				System.out.println("O número de parcelas deve ser no mínimo 3 e no máximo 12");
				System.out.print("Digite a quantidade de parcelas: ");
				int months = input.nextInt();
				System.out.println("");

				while (months < 3 || months > 12) {
					System.out.println("Quantidade invalida!\n");
					System.out.print("Digite novamente: ");
					months = input.nextInt();
					System.out.println("");
				}

				System.out.print("Deseja adicionar algum valor de entrada? (S/N): ");
				String anticipation = input.next();
				System.out.println("");

				double advanceValue = 0.0;
				if (anticipation.toUpperCase().equals("S")) {
					System.out.println("O valor de entrada deve ser no mínimo "
							+ utils.Utils.convertCurrency(CartController.cart.totalValue() * 0.1) + "(10%) e no máximo "
							+ utils.Utils.convertCurrency(CartController.cart.totalValue() * 0.8) + "(80%)");
					System.out.print("Digite o valor: ");
					advanceValue = input.nextDouble();
					System.out.println("");
				}

				try {
					creditService.processInstallments(credit, months, advanceValue);

					System.out.println("Parcelas com acréscimo: ");
					for (Installment installment : credit.getInstallments()) {
						System.out.println(installment);
					}

					System.out.print("\nDeseja finalizar a compra? (S/N): ");
					String finish = input.next();
					System.out.println("");

					if (finish.toUpperCase().equals("S")) {
						finalizePurchase();
					} else {
						paymentMenu();
					}
				} catch (RuntimeException e) {
					System.out.println(e.getMessage());
					System.out.println("");
					paymentMenu();
				}
			} else {
				System.out.println("Total da compra no crédito com acréscimo: "
						+ utils.Utils.convertCurrency(creditService.processPurchase(credit)));
				System.out.println("");

				System.out.print("Deseja finalizar a compra? (S/N): ");
				String finish = input.next();
				System.out.println("");

				if (finish.toUpperCase().equals("S")) {
					finalizePurchase();
				} else {
					paymentMenu();
				}
			}
		} catch (InputMismatchException e) {
			System.err.println("\nPor favor, digite um número!\n");
			input.nextLine();
			credit();
		}
		MainProgram.mainMenu();
	}

	private static void finalizePurchase() {
		CartController.cart.cleanCart();
		System.out.println("Compra realizada com secesso!");
		System.out.println("Obrigado pela preferência, volte sempre.\n");
		
		PaymentController.purchaseCompleted();
	}
	
	public static void purchaseCompleted() {
		try {
			System.out.println("--------- Digite uma opção abaixo ----------");
			System.out.print("| Opção 1 - Continuar comprando            |\r\n"
						   + "| Opção 2 - Encerrar sessão                |\r\n"
						   + "--------------------------------------------\r\n" + ">> ");
			int option = input.nextInt();
			System.out.println("");

			switch (option) {
			case 1:
				MainProgram.mainMenu();
			case 2:
				System.out.println("Sessão encerrada!");
				System.out.println("Volte sempre...");
				System.exit(option);
			default:
				System.out.println("Opção Invalida!\r\n");
				purchaseCompleted();
				break;
			}
		} catch (InputMismatchException e) {
			System.err.println("\nPor favor, digite um número!\n");
			input.nextLine();
			purchaseCompleted();
		}
	}

}
