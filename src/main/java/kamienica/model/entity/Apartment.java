package kamienica.model.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "APARTMENT", uniqueConstraints = {@UniqueConstraint(columnNames = {"apartmentNumber", "residence_id"})})
public class Apartment extends DBEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }

}
