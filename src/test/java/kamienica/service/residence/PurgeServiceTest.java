package kamienica.service.residence;

import static org.junit.Assert.assertEquals;

import java.util.List;
import kamienica.configuration.ServiceTest;
import kamienica.feature.residence.IPurgeService;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Residence;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;

@WithUserDetails(ServiceTest.OWNER)
public class PurgeServiceTest extends ServiceTest {

    @Autowired
    private IPurgeService service;

    @Test
    public void purgeData() {
        final Residence r = getOwnersResidence();
        service.purgeData(r);

        final List<Apartment> apartments = apartmentService.list();
        assertEquals(1, apartments.size());
    }

}