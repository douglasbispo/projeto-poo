package program;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.Cart;
import entities.Product;
import entities.User;
import entities.payment.Credit;
import entities.payment.Installment;
import services.CreditService;
import services.PaypalService;

public class Test {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		List<Product> pro = new ArrayList<Product>();
		List<User> user = new ArrayList<User>();
		
		Product p1 = new Product("Notebook", 5000.0, "Descrição", 10);
		Product p2 = new Product("Ram", 400.0, "Ddr4 8Gb", 20);
		
		pro.add(p1);
		pro.add(p2);
		
		for (Product cli : pro) {
			//System.out.println(cli);
		}
		
		Cart c = new Cart();
		
		c.include(p1, 2);
		c.include(p2, 3);
		
		c.displayProducts();
		
		Integer id = 2;
		Integer amount = 1;
		//System.out.println("Editar: " + c.editQuantity(id, amount));
		
		c.displayProducts();
		
		System.out.println("Remover: " + c.remove(10));
		
		c.displayProducts();
		
		Date date = sdf.parse("30/10/2023");
		
		Credit credit = new Credit(789, date, c.totalValue());
		
		CreditService cs = new CreditService(new PaypalService());
		
		cs.processContract(credit, 10);
		
		
		System.out.println("");
		System.out.println("Parcelas: ");
		for (Installment installment : credit.getInstallments()) {
			System.out.println(installment);
		}

	}

}
