package kamienica.core;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import kamienica.apartment.Apartment;
import kamienica.model.Division;
import kamienica.model.Tenant;

public class DivisionManager {

	public static ArrayList<Division> prepareDivisionListForRegistration(List<Tenant> tenantList,
			List<Apartment> apartmentList) {
		ArrayList<Division> divisionList = new ArrayList<>();
		for (Tenant ten : tenantList) {
			for (Apartment ap : apartmentList) {
				Division tmp = new Division();
				tmp.setApartment(ap);
				tmp.setTenant(ten);
				if (ap.getApartmentNumber() == 0) {
					tmp.setDivisionValue(Double.valueOf(decimalFormat((double) (1 / (double) tenantList.size()))));
				} else if (ap.getApartmentNumber() == ten.getApartment().getApartmentNumber()) {
					tmp.setDivisionValue(1);
				} else {
					tmp.setDivisionValue(0);
				}

				divisionList.add(tmp);
			}
		}
		return divisionList;
	}

	private static double decimalFormat(double input) {
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
		DecimalFormat df = (DecimalFormat) nf;
		df.applyPattern("#.00");
		return Double.parseDouble(df.format(input));
	}
		

}
