package kamienica.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
@Inheritance
public abstract class Invoice {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	@Column(nullable = false, unique = true)
	@NotEmpty(message = "Podaj wartość")
	private String serialNumber;
	@Column
	private String description;
	@Column(nullable = false, unique = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy.MM.dd")
	@NotNull(message = "Wprowadź datę")
	private Date date;
	@Column(nullable = false)
	@Min(value = 0, message = "Tylko wartości dodatnie")
	@NotNull(message = "Podaj wartość")
	private double totalAmount;


	@Autowired
	public Invoice(String serialNumber, String description, Date date, double totalAmount) {
		this.serialNumber = serialNumber;
		this.description = description;
		this.date = date;
		this.totalAmount = totalAmount;

	}

	public Invoice() {
		this.date = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}



	@Override
	public String toString() {
		return "Invoice [id=" + id + ", serialNumber=" + serialNumber + ", description=" + description + ", date="
				+ date + ", totalAmount=" + totalAmount + "]";
	}

}
