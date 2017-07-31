package kamienica.service.stresstest;

import kamienica.configuration.ServiceTest;
import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Meter;
import kamienica.model.entity.Residence;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Media;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

public class StressTest extends ServiceTest {
    private static final List<Residence> residences = new ArrayList<>();
    private static final List<Apartment> apartments = new ArrayList<>();
    private static final List<Meter> meters = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(StressTest.class);

    //test takes much less time locally but it's longer on travis CI
    private static final int MAX_TIME = 10;

    @BeforeClass
    public static void init() {
        prepareData();
    }

    @Test
    public void stressTest() {
        mockStatic(SecurityDetails.class);
        Tenant t = tenantService.getById(1L);
        when(SecurityDetails.getLoggedTenant()).thenReturn(t);

        final long start = System.currentTimeMillis();
        LOGGER.info("Start");
        for (Residence r : residences) residenceService.save(r);
        for (Apartment a : apartments) apartmentService.save(a);
        for (Meter m : meters) meterService.save(m);
        final long timeElapsed = (System.currentTimeMillis() - start) / 1000;
        LOGGER.info("Time taken: " + timeElapsed);
        assertTrue(timeElapsed < MAX_TIME);
    }

    private static void prepareData() {
        LOGGER.info("Preparing data...");
        for (int i = 0; i < 1000; i++) {
            final Residence r = new Residence(String.valueOf(i), String.valueOf(i), "Gdynia");
            residences.add(r);
            createApartments(r);
        }
        LOGGER.info("Done");
    }

    private static void createApartments(final Residence r) {
        for (int i = 1; i < 5; i++) {
            final Apartment ap = new Apartment(null, i, String.valueOf(i), String.valueOf(i), r);
            apartments.add(ap);
            createMetersForApartment(ap);
        }
    }

    private static void createMetersForApartment(Apartment ap) {
        final String uniqueFlag = ap.getDescription() + ap.getResidence().getNumber();
        final Meter energy = new Meter("energy" + uniqueFlag, "energy" + uniqueFlag, "", ap, Media.ENERGY);
        final Meter water = new Meter("watre" + uniqueFlag, "water" + uniqueFlag, "", ap, Media.WATER);
        final Meter gas = new Meter("gas" + uniqueFlag, "gas" + uniqueFlag, "", ap, Media.GAS);
        meters.add(energy);
        meters.add(water);
        meters.add(gas);
    }
}
