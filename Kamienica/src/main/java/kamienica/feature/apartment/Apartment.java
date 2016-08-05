package kamienica.feature.apartment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "apartment")
public class Apartment implements Serializable {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column(nullable = false, unique = true)
	@NotNull(message = "Wstaw wartość liczbową")
	@Min(value = 0, message = "Nie może być wartość ujemna")
	@Digits(integer = 100, fraction = 0, message = "Tylko wartości liczbowe")
	private int apartmentNumber;
	@Column
	@Size(min = 4, max = 4, message = "Kod musi zawiera c dok ladnie cztery cyfry")
	@Digits(integer = 4, fraction = 0, message = "Tylko wartości liczbowe")
	private String intercom;
	@Column(nullable = false)
	@NotEmpty(message = "Wprowadź opis")
	private String description;

	@Autowired
	public Apartment(Long id, int apartmentNumber, String intecom, String description) {
		this.id = id;
		this.apartmentNumber = apartmentNumber;
		this.intercom = intecom;
		this.description = description;

	}

	@Autowired
	public Apartment(int apartmentNumber, String intecom, String description) {

		this.apartmentNumber = apartmentNumber;
		this.intercom = intecom;
		this.description = description;

	}

	public Apartment() {
	}

	@Override
	public String toString() {
		return "Number " + apartmentNumber + "; " + description;
	}

	public int getApartmentNumber() {
		return apartmentNumber;
	}

	public String getIntercom() {
		return intercom;
	}

	public void setIntercom(String intecom) {
		this.intercom = intecom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setApartmentNumber(int nrMiekszania) {
		this.apartmentNumber = nrMiekszania;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
