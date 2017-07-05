package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.feature.residence.IResidencePurgeService;
import kamienica.model.entity.Residence;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNull;

public class ResidencePurgeServiceTest extends ServiceTest {

    private static final long ID = 1L;

    @Transactional
    @Test
    public void shouldDeleteResidence() {
        final Residence r = residenceService.getById(ID);
        purgeService.purgeData(r);

        final Residence result = residenceService.getById(ID);
        assertNull(result);
    }

}