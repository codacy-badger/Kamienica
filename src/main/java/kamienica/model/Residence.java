package kamienica.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "residence", uniqueConstraints = {@UniqueConstraint(columnNames = {"street", "number", "city"})})
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
}
