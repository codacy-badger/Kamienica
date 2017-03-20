package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.ResidenceOwnership;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

public class ResidenceOwnershipServiceTest extends ServiceTest {

    @Test
    public void listForOwner() {
        when(SecurityDetails.getLoggedTenant()).thenReturn(getOwner());
        final List<ResidenceOwnership> result = residenceOwnershipService.list();
        assertEquals(1, result.size());
    }


}