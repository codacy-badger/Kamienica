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
@Table(name = "apartment", uniqueConstraints = {@UniqueConstraint(columnNames = {"apartmentNumber", "residence_id"})})
public class Apartment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column
    private Long id;
    @ManyToOne
    private Residence residence;
    @Column(nullable = false)
    @NotNull(message = "Wstaw wartość liczbową")
    @Min(value = 0, message = "Nie może być wartość ujemna")
    @Digits(integer = 100, fraction = 0, message = "Tylko wartości liczbowe")
    private int apartmentNumber;
    @Column
    @Size(min = 4, max = 4, message = "Kod musi zawierać dokladnie cztery cyfry")
    @Digits(integer = 4, fraction = 0, message = "Tylko wartości liczbowe")
    private String intercom;
    @Column(nullable = false)
    @NotEmpty(message = "Wprowadź opis")
    private String description;


    @Autowired
    public Apartment(Long id, int apartmentNumber, String intecom, String description, Residence residence) {
        this.id = id;
        this.apartmentNumber = apartmentNumber;
        this.intercom = intecom;
        this.description = description;
        this.residence = residence;
    }

    @Autowired
    public Apartment(int apartmentNumber, String intecom, String description, Residence residence) {
        this.apartmentNumber = apartmentNumber;
        this.intercom = intecom;
        this.description = description;
        this.residence = residence;
    }

    public Apartment() {
    }

    @Autowired
    public Apartment(Residence res, int apartmentNumber, String intecom, String description) {
        this.apartmentNumber = apartmentNumber;
        this.intercom = intecom;
        this.description = description;
        this.residence = res;
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


    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apartment apartment = (Apartment) o;

        if (getApartmentNumber() != apartment.getApartmentNumber()) return false;
        if (getId() != null ? !getId().equals(apartment.getId()) : apartment.getId() != null) return false;
        if (getResidence() != null ? !getResidence().equals(apartment.getResidence()) : apartment.getResidence() != null)
            return false;
        if (getIntercom() != null ? !getIntercom().equals(apartment.getIntercom()) : apartment.getIntercom() != null)
            return false;
        return getDescription() != null ? getDescription().equals(apartment.getDescription()) : apartment.getDescription() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getResidence() != null ? getResidence().hashCode() : 0);
        result = 31 * result + getApartmentNumber();
        result = 31 * result + (getIntercom() != null ? getIntercom().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}
