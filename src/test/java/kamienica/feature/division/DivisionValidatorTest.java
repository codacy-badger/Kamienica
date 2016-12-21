package kamienica.feature.division;

import kamienica.model.Apartment;
import kamienica.model.Division;
import kamienica.model.Tenant;
import kamienica.testutils.EntityProvider;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by macfol on 12/18/16.
 */
public class DivisionValidatorTest {

    private static final List<Apartment> apartments = EntityProvider.APARTMENTS;
    private static final List<Division> divisions = EntityProvider.DIVISION;
    private static final List<Division> divisionsWrong = EntityProvider.DIVISION_WRONG;
    private static final List<Tenant> tenants = EntityProvider.TENANTS;

//    @Test
//    public void validateDivisionShouldReturnTrue() throws Exception {
//        final boolean result = DivisionValidator.validateDivision(apartments, divisions, tenants);
//        assertTrue(result);
//    }
//
//    @Test
//    public void validateDivisionShouldReturnFalse() throws Exception {
//        final boolean result = DivisionValidator.validateDivision(apartments, divisionsWrong, tenants);
//        assertFalse(result);
//    }

    @Test
    public void checksumForDivisionShouldReturnTrue() throws Exception {
        final boolean result = DivisionValidator.checkIfDivisionIsCorrect(apartments, divisions);
        assertTrue(result);
    }

    @Test
    public void checksumForDivisionShouldReturnFalse() throws Exception {
        final boolean result = DivisionValidator.checkIfDivisionIsCorrect(apartments, divisionsWrong);
        assertFalse(result);
    }

}