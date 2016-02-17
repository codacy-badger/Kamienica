package kamienica.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class PaymentGas extends PaymentAbstract {

	@ManyToMany
	private List<InvoiceGas> invoice;

	public PaymentGas() {
	}

	public List<InvoiceGas> getInvoice() {
		return invoice;
	}

	public PaymentGas(int id, Date paymentDate, double paymentAmount, Tenant tenant, List<InvoiceGas> invoice) {
		super(id, paymentDate, paymentAmount, tenant);
		this.invoice = invoice;
	}

	public void setInvoice(List<InvoiceGas> invoice) {
		this.invoice = invoice;
	}

}
