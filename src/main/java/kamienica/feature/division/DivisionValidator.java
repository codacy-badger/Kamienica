package kamienica.feature.division;

import kamienica.model.Apartment;
import kamienica.model.Division;

import java.util.List;

public class DivisionValidator {

    public static boolean checkIfDivisionIsCorrect(final List<Apartment> apartmentList, final List<Division> divisionList) {

        for (Apartment anApartmentList : apartmentList) {
            double sumForElement = 0;

            for (Division division : divisionList) {
                if (isDivisionForApartmentInScope(anApartmentList, division)) {
                    sumForElement += division.getDivisionValue();
                }
            }

            if (sumForElement >= 0.99) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDivisionForApartmentInScope(Apartment anApartmentList, Division division) {
        return division.getApartment().getId().equals(anApartmentList.getId());
    }
}
