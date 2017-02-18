package kamienica.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "RESIDENCE", uniqueConstraints = {@UniqueConstraint(columnNames = {"street", "number", "city"})})
public class Residence implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private String city;

    public Residence() {
    }

    public Residence(Long id, String street, String number, String city) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.city = city;
    }

    public Residence(String street, String number, String city) {
        this.street = street;
        this.number = number;
        this.city = city;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(street).append(" ").append(number).append(", ").append(city).toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Residence residence = (Residence) o;

        if (!getId().equals(residence.getId())) return false;
        if (!getStreet().equals(residence.getStreet())) return false;
        if (!getNumber().equals(residence.getNumber())) return false;
        return getCity().equals(residence.getCity());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getStreet().hashCode();
        result = 31 * result + getNumber().hashCode();
        result = 31 * result + getCity().hashCode();
        return result;
    }
}
