package entities;

import utils.Utils;

public class Product {
	private static Integer count = 1;
	
	private Integer idProduct;
	private String name;
	private Double price;
	private String description;
	private Integer amount;
	
	public Product() {}

	public Product(String name, Double price, String description, Integer amount) {
		this.idProduct = Product.count;
		this.name = name;
		this.price = price;
		this.description = description;
		this.amount = amount;
		Product.count += 1; 
	}

	public Integer getIdProduct() {
		return idProduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "ID: " + this.getIdProduct() + 
				"\nNome: " + this.getName() + 
				"\nPreço: " + Utils.convertCurrency(this.getPrice()) + 
				"\nDescrição: " + this.getDescription();
	}
}
