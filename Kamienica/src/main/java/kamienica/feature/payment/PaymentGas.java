package kamienica.feature.payment;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import kamienica.feature.invoice.InvoiceGas;
import kamienica.feature.tenant.Tenant;

@Entity
@Table(name="paymentgas")
public class PaymentGas extends PaymentAbstract {

	@ManyToOne
	private InvoiceGas invoice;

	public PaymentGas() {
	}

	

	public PaymentGas(Long id, Date paymentDate, double paymentAmount, Tenant tenant, InvoiceGas invoice) {
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
