package entities.payment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentSlip {
	
	private Date date = new Date();
	private Double price;
	
	private List<Installment> installments = new ArrayList<Installment>();
	
	public PaymentSlip() {}

	public PaymentSlip(Double price) {
		super();
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<Installment> getInstallments() {
		return installments;
	}	
}
