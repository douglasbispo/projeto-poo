package services;

import java.util.Calendar;
import java.util.Date;

import entities.payment.Credit;
import entities.payment.Installment;

public class CreditService {

	private OnlinePaymentService onlinePaymentService;
	
	public CreditService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}
	
	public void processContract(Credit credit, int months) {
		double basicQuota = credit.getPrice() / months;
		
		for (int i = 1; i <= months; i++) {
			double updatedQuota = basicQuota + onlinePaymentService.interest(basicQuota, i);
			
			double fullQuota = updatedQuota + onlinePaymentService.paymentFee(updatedQuota);
			
			Date dueDate = addMonths(credit.getDate(), i);
			
			Installment installment = new Installment(dueDate, fullQuota);
			credit.getInstallments().add(installment);
		}
	}
	
	private Date addMonths(Date date, int N) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, N);
		return calendar.getTime();
	}
	
}
