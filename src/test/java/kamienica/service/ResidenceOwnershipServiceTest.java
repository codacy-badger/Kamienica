package kamienica.service;

import kamienica.configuration.DatabaseTest;
import kamienica.model.ResidenceOwnership;
import kamienica.model.Tenant;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by macfol on 1/16/17.
 */
public class ResidenceOwnershipServiceTest extends DatabaseTest {



    @Test
    public void listForOwner() throws Exception {
        final Tenant OWNER = tenantService.getTenantById(1L);
        final List<ResidenceOwnership> result = residenceOwnershipService.list(OWNER);
        assertEquals(1, result.size());
    }

    @Ignore("must add admin")
    @Test
    public void listForAdmin() throws Exception {
        final Tenant OWNER = tenantService.getTenantById(1L);
        final List<ResidenceOwnership> result = residenceOwnershipService.list(OWNER);
        assertEquals(1, result.size());
    }

    @Ignore
    @Test
    public void delete() throws Exception {

    }

    @Ignore
    @Test
    public void save() throws Exception {
    }

    @Ignore
    @Test
    public void update() throws Exception {

    }

}