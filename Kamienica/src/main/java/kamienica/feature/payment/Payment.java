package kamienica.feature.payment;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import kamienica.feature.tenant.Tenant;

@MappedSuperclass
@Inheritance
public abstract class Payment {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate paymentDate;
	@Column
	private double paymentAmount;
	@ManyToOne
	private Tenant tenant;

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

	@Override
	public String toString() {
		return "Opłata dla " + tenant.fullName() + " z dnia " + paymentDate.toString() + " Wynosi " + paymentAmount;
	}

	public Payment() {
	}

	public Payment(Long id, LocalDate paymentDate, double paymentAmount, Tenant tenant) {
		this.id = id;
		this.paymentDate = paymentDate;
		this.paymentAmount = paymentAmount;
		this.tenant = tenant;

	}
}
