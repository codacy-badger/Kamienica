package kamienica.feature.payment;

import kamienica.model.Tenant;
import kamienica.model.InvoiceGas;
import org.joda.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "paymentgas")
public class PaymentGas extends Payment implements Serializable {

    private static final long serialVersionUID = -1508559107778012965L;
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
