package kamienica.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import kamienica.core.util.JodaDateSerializer;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Table(name = "PAYMENT")
@Entity
public class Payment {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonSerialize(using = JodaDateSerializer.class)
    private LocalDate paymentDate;
    @Column
    private double paymentAmount;
    @ManyToOne
    private Tenant tenant;
    @OneToOne
    private Invoice invoice;

    public Payment() {
    }

    public Payment(LocalDate paymentDate, double paymentAmount, Tenant tenant, Invoice invoice) {
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.tenant = tenant;
        this.invoice = invoice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
