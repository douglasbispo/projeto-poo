package entities.payment;

public class Debit {
	private Double price;
	
	public Debit() {}

	public Debit(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
