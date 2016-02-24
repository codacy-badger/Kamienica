package kamienica.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

@MappedSuperclass
@Inheritance
public abstract class MeterAbstract {

	@Id
	@GeneratedValue
	@Column
	protected int id;
	@Column(nullable = false)
	@NotEmpty(message="Wprowadź wartość")
	protected String description;
	@Column(nullable = false, unique = true)
	@NotEmpty(message="Wprowadź wartość")
	protected String serialNumber;
	@Column(nullable = false)
	@NotEmpty(message="Wprowadź wartość")
	protected String unit;
	@ManyToOne
	protected Apartment apartment;

	@Autowired
	public MeterAbstract(String description, String serialNumber,
			String unit, Apartment apartment) {
		super();
		this.description = description;
		this.serialNumber = serialNumber;
		this.unit = unit;
		this.apartment = apartment;

	}

	public MeterAbstract() {
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
		String opisMieszkania;
		try {
			opisMieszkania = apartment.getDescription();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			opisMieszkania = "brak";
		}
		return "\n" + description + ", serialNumber:" + serialNumber
				+ ", Mieszkanie:" + opisMieszkania;
	}

	
	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
