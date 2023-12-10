package services;

import java.util.Date;

import entities.payment.Debit;

public class DebitService implements IPaymentsServices<Debit>{
	private static final double DISCOUNT_RATE = 0.1;
	
	@Override
	public double processPurchase(Debit debit) {
		double basicQuota = debit.getPrice() * DISCOUNT_RATE;
		return debit.getPrice() - basicQuota;
	}

	@Override
	public void processInstallments(Debit entity, int months, double anticipation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date addMonths(Date date, int N) {
		// TODO Auto-generated method stub
		return null;
	}

}
