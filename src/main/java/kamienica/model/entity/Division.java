package kamienica.model.entity;

import java.io.Serializable;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class Division implements Serializable {

    private static final long serialVersionUID = 1L;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Tenant tenant;
    private Apartment apartment;
    private double divisionValue;

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

    public Division() {
    }
}
