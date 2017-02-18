package kamienica.model.entity;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DIVISION")
public class Division implements Serializable {


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

	@Override
	public String toString() {
		return "Division [id=" + id + ", date=" + date + ", tenant=" + tenant + ", apartment=" + apartment
				+ ", divisionValue=" + divisionValue + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apartment == null) ? 0 : apartment.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		long temp;
		temp = Double.doubleToLongBits(divisionValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tenant == null) ? 0 : tenant.hashCode());
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
		Division other = (Division) obj;
		if (apartment == null) {
			if (other.apartment != null)
				return false;
		} else if (!apartment.equals(other.apartment))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (Double.doubleToLongBits(divisionValue) != Double.doubleToLongBits(other.divisionValue))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tenant == null) {
			if (other.tenant != null)
				return false;
		} else if (!tenant.equals(other.tenant))
			return false;
		return true;
	}


}
