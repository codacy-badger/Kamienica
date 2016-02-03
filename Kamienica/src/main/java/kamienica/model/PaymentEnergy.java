package kamienica.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class PaymentEnergy extends PaymentAbstract {

	@OneToOne
	private InvoiceEnergy invoice;

	public PaymentEnergy() {

	}


	public PaymentEnergy(int id, Date paymentDate, double paymentAmount, Tenant tenant, Date readingDate,
			InvoiceEnergy invoice) {
		super(id, paymentDate, paymentAmount, tenant, readingDate);
		this.invoice = invoice;
	}


	public InvoiceEnergy getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceEnergy invoice) {
		this.invoice = invoice;
	}


	@Override
	public String toString() {
		return "PaymentEnergy [invoice=" + invoice + ", getId()=" + getId() + ", getPaymentDate()=" + getPaymentDate()
				+ ", getPaymentAmount()=" + getPaymentAmount() + ", getTenant()=" + getTenant() + ", getReadingDate()="
				+ getReadingDate() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

}
