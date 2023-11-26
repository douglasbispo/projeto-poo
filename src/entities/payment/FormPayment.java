package entities.payment;

public class FormPayment {
	
	protected Double price;
	
	public FormPayment(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
