package kamienica.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "apartment")
public class Apartment implements Serializable {

    private static final long serialVersionUID = 1L;

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
		return "Apartment [id=" + id + ", apartmentNumber=" + apartmentNumber + ", intercom=" + intercom
				+ ", description=" + description + "]";
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + apartmentNumber;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((intercom == null) ? 0 : intercom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Apartment other = (Apartment) obj;
		if (apartmentNumber != other.apartmentNumber)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (intercom == null) {
			if (other.intercom != null)
				return false;
		} else if (!intercom.equals(other.intercom))
			return false;
		return true;
	}

}
