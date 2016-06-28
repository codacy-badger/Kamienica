package kamienica.feature.payment;

public enum PaymentStatus {
	PAID("Opłacono"), UNPAID("Nie opłacono");

	String paymentStatus;

	private PaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}
}
