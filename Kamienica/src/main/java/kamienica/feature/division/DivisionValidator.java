package kamienica.feature.division;

import java.util.List;
import java.util.Objects;

import kamienica.model.Apartment;
import kamienica.feature.tenant.Tenant;
import kamienica.model.Division;

public class DivisionValidator {

	public static boolean validateDivision(List<Apartment> apartments, List<Division> divisionTocheck,
			List<Tenant> currentTenants) {

		boolean toReturn = true;
		double[] sum = new double[apartments.size()];
		for (Tenant tenant : currentTenants) {
			boolean stop = true;
			for (Division p : divisionTocheck) {
				if (Objects.equals(p.getTenant().getId(), tenant.getId())) {
					stop = false;
					break;
				}
			}
			if (stop) {

				return false;
			}
		}
		for (Apartment m : apartments) {
			int nrM = m.getApartmentNumber();
			for (Division p : divisionTocheck) {
				int apartmentNumber = p.getApartment().getApartmentNumber();
				if (m.getApartmentNumber() == apartmentNumber) {

					sum[nrM] = sum[nrM] + p.getDivisionValue();
				}
			}

		}

		for (double d : sum) {
			if (d != 1 && d != 0.99) {

				toReturn = false;
				break;
			}
		}

		return toReturn;
	}

	public static boolean checksumForDivision(List<Apartment> apartmentList, List<Division> divisionList) {
		// check if total contribution for each element equals 1
		double[] checklist = new double[apartmentList.size()];
		boolean toReturn = true;

		for (int i = 0; i < apartmentList.size(); i++) {
			double validator = 0;
			for (Division p : divisionList) {
				if (Objects.equals(p.getApartment().getId(), apartmentList.get(i).getId())) {
					double tmp = 0;
					if (p.getDivisionValue() == 0.33) {
						tmp = (double) 1 / 3;
					}
					if (p.getDivisionValue() == 0.66) {
						tmp = (double) 2 / 3;
					}
					if (tmp != 0) {
						validator += tmp;
					} else {
						validator += p.getDivisionValue();
					}
				}

				if (validator == 0.99) {
					validator = 1;
				}
				checklist[i] = validator;
			}
		}

		for (double aChecklist : checklist) {

			if (aChecklist != 1) {
				toReturn = false;

				break;
			}
		}
		return toReturn;
	}
}
