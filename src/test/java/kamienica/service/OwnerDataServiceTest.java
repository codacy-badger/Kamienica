package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.OwnerData;
import kamienica.model.entity.Residence;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

public class OwnerDataServiceTest extends ServiceTest {



    @Test
    public void getMainData() throws Exception {
        List<Residence> residences = Collections.singletonList(getOWnersResidence());
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(residences);

        final OwnerData result = dataService.getMainData();


        System.out.println(result);
        assertEquals(0, result.getEmptyApartments());
        assertEquals(1, result.getNumOrResidences());



    }

}