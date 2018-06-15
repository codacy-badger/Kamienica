package kamienica.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import kamienica.core.util.JodaDateSerializer;
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
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern="dd-MM-yyyy")
    @JsonSerialize(using = JodaDateSerializer.class)
    private LocalDate contractStart;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern="dd-MM-yyyy")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonSerialize(using = JodaDateSerializer.class)
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
