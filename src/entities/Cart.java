package entities;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	private static Integer count = 1;
	
	private Integer idCart;
	private Integer amount;
	private Map<Product, Integer> products;
	
	public Cart() {
		this.idCart = Cart.count;
		products = new HashMap<Product, Integer>();
		Cart.count += 1;
	}

	public Integer getIdCart() {
		return idCart;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Map<Product, Integer> getProducts() {
		return products;
	}

	// Adicionar ao carrinho
	public boolean include(Product p, Integer amount) {
		if (products.containsKey(p)) return false;
		products.put(p, amount);
		return true;
	}
	
	// Editar quantidade de produtos no carrinho
	public boolean editQuantity(Integer id, Integer amount) {
		for (Product p : products.keySet())  {
			if (p.getIdProduct() == id) {
				products.put(p, amount);
				return true;
			}
		}
		return false;
	}
	
	// Remover produto do carrinho
	public boolean remove(Integer id) {
		for (Product p : products.keySet())  {
			if (p.getIdProduct() == id) {
				products.remove(p);
				return true;
			}
		}
		return false;
	}
	
	// limpar carrinho
	public void cleanCart() {
		if (products.size() > 0) {
			products.clear();			
		}
	}
	
	// Mostrar valor total do carrinho
	public Double totalValue() {
		Double purchasePrice = 0.0;
		
		for (Product p : products.keySet()) {
			int amount = products.get(p);
			purchasePrice += p.getPrice() * amount;
		}
		
		return purchasePrice;
	}
	
	// Mostrar produtos no carrinho
	public void displayProducts() {
		if (products.size() > 0) {
			System.out.println("--------------- SEU CARRINHO ---------------");
			for (Product p : products.keySet()) {
				System.out.println(p);
				System.out.println("Quantidade: " + products.get(p).byteValue() + "\n");
			}
			System.out.println("--------------------------------------------");
			System.out.println("Valor Total: " + utils.Utils.convertCurrency(totalValue()));
			System.out.println("--------------------------------------------");
		} else {
			System.out.println("--------------------------------------------");
			System.out.println("----------- Carrinho está vazio! -----------");
			System.out.println("-- Para adicionar vá à lista de produtos ---");
			System.out.println("--------------------------------------------\n\n");
		}
	}
}
