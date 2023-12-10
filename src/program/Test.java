package program;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import app.controllers.CartController;
import entities.Cart;
import entities.Product;
import entities.payment.Credit;
import entities.payment.Installment;
import services.CreditService;
import services.PaypalService;

public class Test {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Scanner input = new Scanner(System.in);
		
		List<Product> pro = new ArrayList<Product>();
		//List<User> user = new ArrayList<User>();
		
		Product p1 = new Product("Notebook", 5000.0, "Descrição");
		Product p2 = new Product("Ram", 400.0, "Ddr4 8Gb");
		
		pro.add(p1);
		pro.add(p2);
		
		//for (Product cli : pro) {
			//System.out.println(cli);
		//}
		
		Cart c = new Cart();
		
		c.include(p1, 2);
		c.include(p2, 3);
		
		c.displayProducts();
		
		//Integer id = 2;
		//Integer amount = 1;
		//System.out.println("Editar: " + c.editQuantity(id, amount));
		
		c.displayProducts();
		
		System.out.println("Remover: " + c.remove(10));
		
		c.displayProducts();
		
		//Date date = sdf.parse("30/10/2023");
		
		Credit credit = new Credit(c.totalValue());
		
		CreditService cs = new CreditService(new PaypalService());
		
		
		System.out.println("O número de parcelas deve ser no mínimo 3 e no máximo 12");
		System.out.print("Digite a quantidade de parcelas: ");
		int amount = input.nextInt();
		System.out.println("");
		
		System.out.print("Deseja adicionar algum valor de entrada? (S/N): ");
		String anticipation = input.next();
		System.out.println("");
		
		double advanceValue = 0.0;
		if (anticipation.toUpperCase().equals("S")) {
			System.out.println("O valor de entrada deve ser no mínimo " 
							+ c.totalValue() * 0.2 + " e no máximo " + c.totalValue() * 0.6);
			System.out.print("Digite o valor: ");
			advanceValue = input.nextDouble();
		}
		
		
		
		try {
			cs.processInstallments(credit, amount, 0.0);
			
			System.out.println("");
			System.out.println("Parcelas: ");
			for (Installment installment : credit.getInstallments()) {
				System.out.println(installment);
			}
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
		
		
		

	}

}
