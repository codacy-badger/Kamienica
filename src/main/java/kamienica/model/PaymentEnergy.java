package kamienica.model;

import org.joda.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "paymentenergy")
public class PaymentEnergy extends Payment implements Serializable {

    private static final long serialVersionUID = -742907043105280124L;
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
