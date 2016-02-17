package kamienica.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class PaymentEnergy extends PaymentAbstract {

	@ManyToMany
	private List<InvoiceEnergy> invoice;

	public PaymentEnergy() {

	}

	public PaymentEnergy(int id, Date paymentDate, double paymentAmount, Tenant tenant, List<InvoiceEnergy> invoice) {
		super(id, paymentDate, paymentAmount, tenant);
		this.invoice = invoice;
	}

	public List<InvoiceEnergy> getInvoice() {
		return invoice;
	}

	public void setInvoice(List<InvoiceEnergy> invoice) {
		this.invoice = invoice;
	}

	@Override
	public String toString() {
		return "PaymentEnergy [invoice=" + invoice + ", getId()=" + getId() + ", getPaymentDate()=" + getPaymentDate()
				+ ", getPaymentAmount()=" + getPaymentAmount() + ", getTenant()=" + getTenant() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
