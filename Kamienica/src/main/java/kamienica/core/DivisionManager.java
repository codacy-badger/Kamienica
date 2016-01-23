package kamienica.core;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import kamienica.model.Apartment;
import kamienica.model.Division;
import kamienica.model.Tenant;

public class DivisionManager {

	public static ArrayList<Division> prepareDivisionListForRegistration(List<Tenant> tenantList, List<Apartment>  apartmentList) {
		ArrayList<Division> divisionList = new ArrayList<>();
		double rr = tenantList.size();
		double contribution = (double) (1 / rr);
		DecimalFormat df = new DecimalFormat("####0.00");
		df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
System.out.println(tenantList.toString());
		for (Tenant i : tenantList) {
			for (Apartment y : apartmentList) {
				Division tmp = new Division();
				tmp.setApartment(y);
				tmp.setTenant(i);
				if (y.getApartmentNumber() == 0) {
					tmp.setDivisionValue(Double.valueOf(df.format(contribution)));
				} else if (y.getApartmentNumber() == i.getApartment().getApartmentNumber()) {
					tmp.setDivisionValue(1);
				} else {
					tmp.setDivisionValue(0);
				}

				divisionList.add(tmp);
			}
		}
	
	return divisionList;
	}	
	
}
