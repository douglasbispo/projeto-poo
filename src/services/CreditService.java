package services;

import java.util.Calendar;
import java.util.Date;

import entities.payment.Credit;
import entities.payment.Installment;

public class CreditService implements IPaymentsServices<Credit> {

	private ICreditPaymentService iCreditPaymentService;
	
	public CreditService(ICreditPaymentService iCreditPaymentService) {
		this.iCreditPaymentService = iCreditPaymentService;
	}
	
	@Override
	public double processPurchase(Credit credit) {
		return credit.getPrice() + iCreditPaymentService.paymentFee(credit.getPrice());
	}
	
	@Override
	public void processInstallments(Credit credit, int months, double anticipation) {
		if (anticipation < credit.getPrice() * 0.1 && anticipation != 0) {
			throw new RuntimeException("A entrada deve ser pelo menos 10% do valor total");
		} else if (anticipation > credit.getPrice() * 0.8) {
			throw new RuntimeException("A entrada deve ser menor que 80% do valor total");
		} else if (months < 3 || months > 12) {
			throw new RuntimeException("O número de parcelas deve ser no mínimo 3 e no máximo 12");
		}
		
		double basicQuota = (credit.getPrice() - anticipation) / months;
		
		for (int i = 1; i <= months; i++) {
			double updatedQuota = basicQuota + iCreditPaymentService.interest(basicQuota, i);
			
			double fullQuota = updatedQuota + iCreditPaymentService.paymentFee(updatedQuota);
			
			Date dueDate = addMonths(credit.getDate(), i);
			
			Installment installment = new Installment(dueDate, fullQuota);
			credit.getInstallments().add(installment);
		}
	}
	
	@Override
	public Date addMonths(Date date, int N) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, N);
		return calendar.getTime();
	}
	
}
