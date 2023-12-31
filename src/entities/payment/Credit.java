package entities.payment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Credit {
	private Date date = new Date();
	protected Double price;
	
	private List<Installment> installments = new ArrayList<Installment>();

	public Credit() {}

	public Credit(Double price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
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
