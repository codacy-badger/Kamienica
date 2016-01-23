package kamienica.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Division {
	@Id
	@GeneratedValue
	private int id;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy.MM.dd")
	private Date date;
	@ManyToOne
	private Tenant tenant;
	@ManyToOne
	private Apartment apartment;
	@Column(nullable = false)
	private double divisionValue;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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

	@Autowired
	public Division(int id, Date date, Tenant tenant, Apartment apartment, double divisionValue) {
		super();
		this.id = id;
		this.date = date;
		this.tenant = tenant;
		this.apartment = apartment;
		this.divisionValue = divisionValue;
	}

	public Division() {
	}

	@Override
	public String toString() {
		return "\nPodzial: " + tenant.getFullName() + "->" + apartment.getDescription() + "-> podzial=" + divisionValue
				+ "]" + " ID " +getId();
	}

}
