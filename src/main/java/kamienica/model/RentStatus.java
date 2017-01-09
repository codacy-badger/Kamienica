package kamienica.model;


import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity(name = "RENT_STATUS")
public class RentStatus {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Tenant tenant;
    @OneToOne
    private Apartment apartmentRented;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate movementDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Apartment getApartmentRented() {
        return apartmentRented;
    }

    public void setApartmentRented(Apartment apartmentRented) {
        this.apartmentRented = apartmentRented;
    }

    public LocalDate getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(LocalDate movementDate) {
        this.movementDate = movementDate;
    }
}
