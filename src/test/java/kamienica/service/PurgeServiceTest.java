package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.feature.residence.IPurgeService;
import kamienica.model.entity.Residence;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by maciej on 29/07/17.
 */
public class PurgeServiceTest extends ServiceTest {

    @Autowired
    private IPurgeService service;

    @Test
    public void purgeData() throws Exception {
        final Residence r = getOWnersResidence();
        service.purgeData(r);
    }

}