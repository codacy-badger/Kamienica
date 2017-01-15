package kamienica.feature.division;

import kamienica.model.Apartment;
import kamienica.model.Division;
import kamienica.model.Tenant;
import org.joda.time.LocalDate;

import java.util.List;

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
		return new StringBuilder().append("DivisionForm [divisionList=").append(divisionList).append(", date=").append(date).append(", apartments=").append(apartments).append(", tenants=").append(tenants).append("]").toString();
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
