package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.ResidenceOwnership;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.powermock.api.mockito.PowerMockito.when;

public class ResidenceOwnershipServiceTest extends ServiceTest {

    @Test
    public void listForOwner() {
        when(SecurityDetails.getLoggedTenant()).thenReturn(getOwner());
        final List<ResidenceOwnership> result = residenceOwnershipService.list();
        assertEquals(1, result.size());
    }


    @Ignore("must add admin")
    @Test
    public void listForAdmin() {
        final List<ResidenceOwnership> result = residenceOwnershipService.list();
        assertEquals(1, result.size());
    }

    @Ignore
    @Test
    public void delete() throws Exception {
        fail();
    }

    @Ignore
    @Test
    public void save() throws Exception {
        fail();
    }

    @Ignore
    @Test
    public void update() throws Exception {
        fail();
    }

}