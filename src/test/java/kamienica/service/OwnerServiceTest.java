package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Residence;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Status;
import org.h2.tools.Server;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

public class OwnerServiceTest extends ServiceTest {

    @Before
    public void mockUser() {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getLoggedTenant()).thenReturn(getOwner());
        List<Residence> res = new ArrayList<>();
        res.add(getOWnersResidence());
        when(SecurityDetails.getResidencesForOwner()).thenReturn(res);
    }

    @Transactional
    @Test
    @Ignore("Method will be reimplemented")
    public void getEmptyApartments() throws Exception {
        Server.main();
        Tenant t = tenantService.getById(4L);
        t.setStatus(Status.INACTIVE);
        tenantService.update(t);
        List<Tenant> tenants = tenantService.getActiveTenants();
        List<Apartment> result = ownerService.getEmptyApartments();
        assertEquals(1, result.size());
    }

}