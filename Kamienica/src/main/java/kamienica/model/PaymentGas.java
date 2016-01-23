package kamienica.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class PaymentGas extends PaymentAbstract {

	@OneToOne
	private InvoiceGas invoice;

	public PaymentGas() {
	}

	public InvoiceGas getInvoice() {
		return invoice;
	}

	public PaymentGas(int id, Date paymentDate, double paymentAmount, Tenant tenant, Date readingDate,
			InvoiceGas invoice) {
		super(id, paymentDate, paymentAmount, tenant, readingDate);
		this.invoice = invoice;
	}

	public void setInvoice(InvoiceGas invoice) {
		this.invoice = invoice;
	}

}
