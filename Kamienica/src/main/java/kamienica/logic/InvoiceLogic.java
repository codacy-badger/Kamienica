package kamienica.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import kamienica.model.Apartment;
import kamienica.model.Division;
import kamienica.model.Tenant;
import kamienica.service.ApartmentService;
import kamienica.service.DivisionService;
import kamienica.service.TenantService;
import kamienica.validator.DivisionValidator;

public class InvoiceLogic {

	@Autowired
	private ApartmentService apartmentService;
	@Autowired
	private TenantService tenantService;
	@Autowired
	private DivisionService divisionService;

	public boolean checkDivision() {

		ArrayList<Tenant> tenants = (ArrayList<Tenant>) tenantService.getCurrentTenants();
		ArrayList<Division> division = (ArrayList<Division>) divisionService.getList();
		ArrayList<Apartment> apartments = (ArrayList<Apartment>) apartmentService.getList();
		return DivisionValidator.validateDivision(apartments, division, tenants);
	}

}
