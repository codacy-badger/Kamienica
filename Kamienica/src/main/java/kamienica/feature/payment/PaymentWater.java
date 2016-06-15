package kamienica.feature.payment;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.LocalDate;

import kamienica.feature.invoice.InvoiceWater;
import kamienica.feature.tenant.Tenant;

@Entity
@Table(name="paymentwater")
public class PaymentWater extends PaymentAbstract {

	@ManyToOne
	private InvoiceWater invoice;

	public PaymentWater() {
	}

	public PaymentWater(Long id, LocalDate paymentDate, double paymentAmount, Tenant tenant,
			InvoiceWater invoice) {
		super(id, paymentDate, paymentAmount, tenant);
		this.invoice = invoice;
	}

	public InvoiceWater getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceWater invoice) {
		this.invoice = invoice;
	}



}
