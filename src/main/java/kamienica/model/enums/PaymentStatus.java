package kamienica.model.enums;

public enum PaymentStatus {
	PAID("Opłacono"), UNPAID("Nie opłacono");

	String paymentStatus;

	PaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}
}
