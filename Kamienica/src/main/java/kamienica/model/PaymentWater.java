package kamienica.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class PaymentWater extends PaymentAbstract {

	@ManyToMany
	private List<InvoiceWater> invoice;

	public PaymentWater() {
	}

	public PaymentWater(int id, Date paymentDate, double paymentAmount, Tenant tenant,
			List<InvoiceWater> invoice) {
		super(id, paymentDate, paymentAmount, tenant);
		this.invoice = invoice;
	}

	public List<InvoiceWater> getInvoice() {
		return invoice;
	}

	public void setInvoice(List<InvoiceWater> invoice) {
		this.invoice = invoice;
	}

}
