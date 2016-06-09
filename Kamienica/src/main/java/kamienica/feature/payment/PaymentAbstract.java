package kamienica.feature.payment;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import kamienica.feature.tenant.Tenant;

@MappedSuperclass
@Inheritance
public abstract class PaymentAbstract {

	@Id
	@GeneratedValue
	@Column
	private int id;
	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy.MM.dd")
	private Date paymentDate;
	@Column
	private double paymentAmount;
	@ManyToOne
	private Tenant tenant;



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
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
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		return "Opłata dla " + tenant.getFullName() + " z dnia " + df.format(paymentDate) + " Wynosi " + paymentAmount;
	}

	public PaymentAbstract() {
	}

	public PaymentAbstract(int id, Date paymentDate, double paymentAmount, Tenant tenant) {
		this.id = id;
		this.paymentDate = paymentDate;
		this.paymentAmount = paymentAmount;
		this.tenant = tenant;
		
	}
}
