package kamienica.feature.division;

import java.util.List;

import org.joda.time.LocalDate;

import kamienica.feature.apartment.Apartment;
import kamienica.feature.tenant.Tenant;

public class DivisionForm {

	private List<Division> divisionList;
	private LocalDate date;
	private List<Apartment> apartments;
	private List<Tenant> tenants;

	public List<Apartment> getApartments() {
		return apartments;
	}

	public void setApartments(List<Apartment> apartments) {
		this.apartments = apartments;
	}

	public List<Tenant> getTenants() {
		return tenants;
	}

	public void setTenants(List<Tenant> tenants) {
		this.tenants = tenants;
	}

	public DivisionForm() {
		this.date = new LocalDate();
	}

	public List<Division> getDivisionList() {
		return divisionList;
	}

	public void setDivisionList(List<Division> divisionList) {
		this.divisionList = divisionList;
	}

	@Override
	public String toString() {
		return "DivisionForm [divisionList=" + divisionList + ", date=" + date + ", apartments=" + apartments
				+ ", tenants=" + tenants + "]";
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
