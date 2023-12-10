package services;

import java.util.Calendar;
import java.util.Date;

import entities.payment.Installment;
import entities.payment.PaymentSlip;

public class PaymentSlipService implements IPaymentsServices<PaymentSlip> {
	private static final double DISCOUNT_RATE = 0.15;
	
	@Override
	public double processPurchase(PaymentSlip paymentSlip) {
		double basicQuota = paymentSlip.getPrice() * DISCOUNT_RATE;
		return paymentSlip.getPrice() - basicQuota;
	}
	
	@Override
	public void processInstallments(PaymentSlip paymentSlip, int months,  double anticipation) {
		if (anticipation < paymentSlip.getPrice() * 0.1 && anticipation != 0) {
			throw new RuntimeException("A entrada deve ser pelo menos 10% do valor total");
		} else if (anticipation > paymentSlip.getPrice() * 0.8) {
			throw new RuntimeException("A entrada deve ser menor que 80% do valor total");
		} else if (months < 3 || months > 12) {
			throw new RuntimeException("O número de parcelas deve ser no mínimo 3 e no máximo 12");
		}
		
		double basicQuota = (paymentSlip.getPrice() - anticipation) / months;
		
		for (int i = 1; i <= months; i++) {			
			Date dueDate = addMonths(paymentSlip.getDate(), i);
			
			Installment installment = new Installment(dueDate, basicQuota);
			paymentSlip.getInstallments().add(installment);
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
