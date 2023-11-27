package entities.payment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Credit extends FormOfPayment {
	private Integer number;
	private Date date;
	
	private List<Installment> installments = new ArrayList<Installment>();

	public Credit(Integer number, Date date, Double price) {
		super(price);
		this.number = number;
		this.date = date;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Installment> getInstallments() {
		return installments;
	}
}
