package kamienica.model;

import kamienica.model.Apartment;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@MappedSuperclass
@Inheritance
public abstract class Meter {

	@Id
	@GeneratedValue
	@Column
	protected Long id;
	@Column(nullable = false, unique = true)
	@NotEmpty(message = "Wprowadź wartość")
	protected String description;
	@Column(nullable = false, unique = true)
	@NotEmpty(message = "Wprowadź wartość")
	protected String serialNumber;
	@Column(nullable = false)
	@NotEmpty(message = "Wprowadź wartość")
	protected String unit;
	@ManyToOne
	protected Apartment apartment;
	@ManyToOne
	protected Residence residence;
	@Column(columnDefinition = "TINYINT(1)")
	protected boolean main = false;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	protected LocalDate deactivation = LocalDate.parse("2600-01-01");

	public Meter(String description, String serialNumber, String unit, Apartment apartment) {
		this.description = description;
		this.serialNumber = serialNumber;
		this.unit = unit;
		this.apartment = apartment;
	}

	public Meter(String description, String serialNumber, String unit, Apartment apartment, Residence res) {
		this.description = description;
		this.serialNumber = serialNumber;
		this.unit = unit;
		this.apartment = apartment;
		this.residence = res;
	}

	public Meter() {
	}


	public Residence getResidence() {
		return residence;
	}

	public void setResidence(Residence residence) {
		this.residence = residence;
	}

	public LocalDate getDeactivation() {
		return deactivation;
	}

	public void setDeactivation(LocalDate deactivation) {
		this.deactivation = deactivation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {

		if (this.apartment == null) {
			return "\n" + "id=" + id + ", description=" + description + ", serialNumber=" + serialNumber + ", unit="
					+ unit + ", apartment= null , main=" + main + ", deactivation=" + deactivation + "]";

		} else {

			return "\nid=" + id + ", description=" + description + ", serialNumber=" + serialNumber + ", unit=" + unit
					+ ", apartment=" + apartment + ", main=" + main + ", deactivation=" + deactivation + "]";
		}

	}

	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
		if (apartment == null) {
			this.main = true;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isMain() {
		return main;
	}

	public void setMain(boolean main) {

		this.main = main;

	}

}
