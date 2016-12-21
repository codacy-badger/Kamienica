package kamienica.feature.payment;

import kamienica.model.InvoiceWater;
import kamienica.model.Tenant;
import org.joda.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="paymentwater")
public class PaymentWater extends Payment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7997656192652708867L;
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