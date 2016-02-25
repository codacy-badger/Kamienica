package kamienica.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class PaymentGas extends PaymentAbstract {

	@ManyToOne
	private InvoiceGas invoice;

	public PaymentGas() {
	}

	

	public PaymentGas(int id, Date paymentDate, double paymentAmount, Tenant tenant, InvoiceGas invoice) {
		super(id, paymentDate, paymentAmount, tenant);
		this.invoice = invoice;
	}



	public InvoiceGas getInvoice() {
		return invoice;
	}



	public void setInvoice(InvoiceGas invoice) {
		this.invoice = invoice;
	}



}
