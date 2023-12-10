package entities.payment;

public class InCash {
	private Double price;
	
	public InCash() {}

	public InCash(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
		
}
