package services;

import java.util.Date;

public interface IPaymentsServices<E> {
	
	double processPurchase(E entity);
	void processInstallments(E entity, int months, double anticipation);
	Date addMonths(Date date, int N);
	
}
