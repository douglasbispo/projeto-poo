package services;

import java.util.Date;

import entities.payment.InCash;

public class InCashService implements IPaymentsServices<InCash> {
	private static final double DISCOUNT_RATE = 0.2;

	@Override
	public double processPurchase(InCash inCash) {
		double basicQuota = inCash.getPrice() * DISCOUNT_RATE;
		return inCash.getPrice() - basicQuota;
	}

	@Override
	public void processInstallments(InCash entity, int months, double anticipation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date addMonths(Date date, int N) {
		// TODO Auto-generated method stub
		return null;
	}

}
