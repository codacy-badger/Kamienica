package kamienica.model;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "division")
public class Division implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -643280853187144912L;

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate date;
    @ManyToOne
    private Tenant tenant;
    @ManyToOne
    private Apartment apartment;
    @Column(nullable = false)
    private double divisionValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public double getDivisionValue() {
        return divisionValue;
    }

    public void setDivisionValue(double divisionValue) {
        this.divisionValue = divisionValue;
    }

    public Division(LocalDate LocalDate, Tenant tenant, Apartment apartment, double divisionValue) {
        this.date = LocalDate;
        this.tenant = tenant;
        this.apartment = apartment;
        this.divisionValue = divisionValue;
    }

    public Division(Long id, LocalDate LocalDate, Tenant tenant, Apartment apartment, double divisionValue) {
        this.id = id;
        this.date = LocalDate;
        this.tenant = tenant;
        this.apartment = apartment;
        this.divisionValue = divisionValue;
    }

    public Division() {
    }


}
