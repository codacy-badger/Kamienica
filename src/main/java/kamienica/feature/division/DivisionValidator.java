package kamienica.feature.division;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.Division;

import java.util.List;

public class DivisionValidator {

    public static boolean checkIfDivisionIsCorrect(final List<Apartment> apartmentList, final List<Division> divisionList) {

        for (Apartment a : apartmentList) {
            double sumForElement = 0;

            for (Division division : divisionList) {
                if (isDivisionForApartmentInScope(a, division)) {
                    sumForElement += division.getDivisionValue();
                }
            }

            if (sumForElement < 0.99 || sumForElement > 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDivisionForApartmentInScope(Apartment anApartmentList, Division division) {
        return division.getApartment().getId().equals(anApartmentList.getId());
    }
}
