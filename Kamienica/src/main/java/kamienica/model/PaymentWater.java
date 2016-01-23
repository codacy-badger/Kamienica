package kamienica.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class PaymentWater extends PaymentAbstract {

	@OneToOne
	private InvoiceWater invoice;

	public PaymentWater() {
	}

	public PaymentWater(int id, Date paymentDate, double paymentAmount, Tenant tenant, Date readingDate,
			InvoiceWater invoice) {
		super(id, paymentDate, paymentAmount, tenant, readingDate);
		this.invoice = invoice;
	}

	public InvoiceWater getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceWater invoice) {
		this.invoice = invoice;
	}

}
