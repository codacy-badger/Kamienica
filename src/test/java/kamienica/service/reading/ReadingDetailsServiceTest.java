package kamienica.service.reading;

import static org.junit.Assert.assertEquals;

import java.util.List;
import kamienica.configuration.ServiceTest;
import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

public class ReadingDetailsServiceTest extends ServiceTest {

    private static final LocalDate FIRST_SEPTEMBER = LocalDate.parse("2016-09-01");
    private static final LocalDate FIRST_AUGUTST = LocalDate.parse("2016-10-01");
    private static Residence r;

    @Before
    public void setResidence() {
        r = getOWnersResidence();
    }

    @Test
    public void getLatestWater() {
        final ReadingDetails water = readingDetailsService.getLatest(r, Media.WATER);
        assertEquals(FIRST_SEPTEMBER, water.getReadingDate());
        assertEquals(Media.WATER, water.getMedia());
    }

    @Test
    public void getLatestAfterDateEnergy() {
        final ReadingDetails water = readingDetailsService.getLatest(r, Media.WATER);
        assertEquals(FIRST_SEPTEMBER, water.getReadingDate());
        assertEquals(Media.WATER, water.getMedia());
    }

    @Test
    public void getLatestGas() {
        final ReadingDetails gas = readingDetailsService.getLatest(r, Media.GAS);
        assertEquals(FIRST_AUGUTST, gas.getReadingDate());
        assertEquals(Media.GAS, gas.getMedia());
    }

    @Test
    public void getLatestEnergy() {
        final ReadingDetails energy = readingDetailsService.getLatest(r, Media.ENERGY);
        assertEquals(FIRST_SEPTEMBER, energy.getReadingDate());
        assertEquals(Media.ENERGY, energy.getMedia());
    }

    @Test
    public void getUnresolvedEnergy() {
        final List<ReadingDetails> energy = readingDetailsService.getUnresolved(r, Media.ENERGY);
        assertEquals(2, energy.size());
    }

    @Test
    public void getUnresolvedGas() {
        final List<ReadingDetails> gas = readingDetailsService.getUnresolved(r, Media.GAS);
        assertEquals(2, gas.size());
    }

    @Test
    public void getUnresolvedWater() {
        final List<ReadingDetails> water = readingDetailsService.getUnresolved(r, Media.WATER);
        assertEquals(2, water.size());

    }
}
