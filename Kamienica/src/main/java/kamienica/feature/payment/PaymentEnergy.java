package kamienica.feature.payment;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.LocalDate;

import kamienica.feature.invoice.InvoiceEnergy;
import kamienica.feature.tenant.Tenant;

@Entity
@Table(name="paymentenergy")
public class PaymentEnergy extends PaymentAbstract {

	@ManyToOne
	private InvoiceEnergy invoice;

	public PaymentEnergy() {

	}

	public PaymentEnergy(Long id, LocalDate paymentDate, double paymentAmount, Tenant tenant, InvoiceEnergy invoice) {
		super(id, paymentDate, paymentAmount, tenant);
		this.invoice = invoice;
	}


	

	public InvoiceEnergy getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceEnergy invoice) {
		this.invoice = invoice;
	}

}
