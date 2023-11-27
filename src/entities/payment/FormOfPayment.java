package entities.payment;

public class FormOfPayment {
	
	protected Double price;
	
	public FormOfPayment(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
