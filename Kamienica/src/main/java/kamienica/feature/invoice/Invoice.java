package kamienica.feature.invoice;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import kamienica.feature.reading.ReadingAbstract;

@MappedSuperclass
@Inheritance
public abstract class Invoice {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	@Column(nullable = false, unique = true)
	@NotEmpty(message = "Podaj wartość")
	private String serialNumber;
	@Column
	private String description;
	@Column(nullable = false, unique = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Wprowadź datę")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate date;
	@Column(nullable = false)
	@Min(value = 0, message = "Tylko wartości dodatnie")
	@NotNull(message = "Podaj wartość")
	private double totalAmount;

	@Autowired
	public Invoice(String serialNumber, String description, LocalDate date, double totalAmount) {
		this.serialNumber = serialNumber;
		this.description = description;
		this.date = date;
		this.totalAmount = totalAmount;

	}

	public Invoice() {
		this.date = new LocalDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public abstract ReadingAbstract getBaseReading();

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", serialNumber=" + serialNumber + ", description=" + description + ", date="
				+ date + ", totalAmount=" + totalAmount + "]";
	}

	public abstract LocalDate getReadingDate();

}
