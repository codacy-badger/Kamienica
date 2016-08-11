package kamienica.feature.payment;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.LocalDate;

import kamienica.feature.invoice.InvoiceGas;
import kamienica.feature.tenant.Tenant;

@Entity
@Table(name="paymentgas")
public class PaymentGas extends PaymentAbstract implements Serializable {

	@ManyToOne
	private InvoiceGas invoice;

	public PaymentGas() {
	}

	

	public PaymentGas(Long id, LocalDate paymentDate, double paymentAmount, Tenant tenant, InvoiceGas invoice) {
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
