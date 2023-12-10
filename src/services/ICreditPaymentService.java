package services;

public interface ICreditPaymentService {

	double paymentFee(double amount);
	double interest(double amount, int months);
}
