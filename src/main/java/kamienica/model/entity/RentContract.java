package kamienica.model.entity;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RENT_CONTRACT")
public class RentContract extends DBEntity {

    @OneToOne
    private Apartment apartment;
    private double rentCost;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate contractStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate contractEnd = LocalDate.parse("2100-01-01");

    public RentContract(Apartment apartment, double rentCost, LocalDate contractStart, LocalDate contractEnd) {
        this.apartment = apartment;
        this.rentCost = rentCost;
        this.contractStart = contractStart;
        this.contractEnd = contractEnd;
    }

    public RentContract(Apartment apartment, double rentCost, LocalDate contractStart) {
        this.apartment = apartment;
        this.rentCost = rentCost;
        this.contractStart = contractStart;
    }

    public RentContract() {

    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public double getRentCost() {
        return rentCost;
    }

    public void setRentCost(double rentCost) {
        this.rentCost = rentCost;
    }

    public LocalDate getContractStart() {
        return contractStart;
    }

    public void setContractStart(LocalDate contractStart) {
        this.contractStart = contractStart;
    }

    public LocalDate getContractEnd() {
        return contractEnd;
    }

    public void setContractEnd(LocalDate contractEnd) {
        this.contractEnd = contractEnd;
    }

}
