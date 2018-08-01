package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.feature.residence.IPurgeService;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Residence;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PurgeServiceTest extends ServiceTest {

    @Autowired
    private IPurgeService service;

    @Test
    public void purgeData() {
        final Residence r = getOWnersResidence();
        service.purgeData(r);

        final List<Apartment> apartments = apartmentService.list();
        assertEquals(1, apartments.size());
    }

}