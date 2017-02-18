package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.model.enums.Media;
import kamienica.model.exception.NoMainCounterException;
import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Reading;
import kamienica.model.entity.Residence;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

public class ReadingEnergyServiceTest extends ServiceTest {

    private Set<Long> meterIdList = new HashSet<>(Arrays.asList(1L, 2L, 3L, 4L, 5L));

    @Test
    public void getLatest() throws NoMainCounterException {
        final Residence r = residenceService.getById(1L);
        List<ReadingEnergy> list = IReadingService.getLatestNew(r, Media.ENERGY);

        assertEquals(5, list.size());
        for (ReadingEnergy readingEnergy : list) {
            assertEquals(LocalDate.parse("2016-09-01"), readingEnergy.getReadingDate());
        }
    }

    @Transactional
    @Test
    public void getLatestActiveOnly() throws NoMainCounterException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());
        MeterEnergy meter = meterService.getById(3L, Media.ENERGY);
        meter.setDeactivation(LocalDate.parse("2016-01-01"));
        meterService.update(meter, Media.ENERGY);
        final Residence r = residenceService.getById(1L);

        List<ReadingEnergy> list2 = IReadingService.getLatestNew(r, Media.ENERGY);

        assertEquals(4, list2.size());
        for (ReadingEnergy readingEnergy : list2) {
            assertEquals(LocalDate.parse("2016-09-01"), readingEnergy.getReadingDate());
        }
    }

    @Test
    public void getList() {
        assertEquals(16, IReadingService.getList(Media.ENERGY).size());
    }

    @Test
    public void getListForResidence() {
        final Residence r = residenceService.getById(1L);

        List<ReadingEnergy> result = (List<ReadingEnergy>) IReadingService.getList(r, Media.ENERGY);
        assertEquals(15, result.size());
    }

    @Test
    @Transactional
    public void shouldDeleteLatestList() {
        final Residence r = residenceService.getById(RESIDENCE_ID);
        IReadingService.deleteLatestReadings(r, Media.ENERGY);
        List<? extends Reading> list = IReadingService.getList(Media.ENERGY);
        assertEquals(10, list.size());
        for (Reading readingEnergy : list) {
            assertNotEquals(LocalDate.parse("2016-09-01"), readingEnergy.getReadingDate());
        }
    }

    @Test
    public void shouldRetrieviePreviousReadings() {
        List<ReadingEnergy> list = IReadingService.getPreviousReading(LocalDate.parse("2016-09-01"), meterIdList);

        for (ReadingEnergy readingEnergy : list) {
            assertEquals(LocalDate.parse("2016-08-01"), readingEnergy.getReadingDate());
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getByDate() {
        final Residence r = residenceService.getById(RESIDENCE_ID);
        List<ReadingEnergy> list = (List<ReadingEnergy>) IReadingService.getByDate(r, LocalDate.parse("2016-07-01"), Media.ENERGY);
        for (ReadingEnergy readingEnergy : list) {
            assertEquals(LocalDate.parse("2016-07-01"), readingEnergy.getReadingDate());
        }
    }

    @Test
    public void getUnresolved() {
        List<ReadingEnergy> list = IReadingService.getUnresolvedReadingsEnergy();
        assertEquals(2, list.size());
        assertEquals("2016-07-01", list.get(0).getReadingDate().toString());
        assertEquals(true, list.get(0).getMeter().isMain());
        assertEquals("2016-09-01", list.get(1).getReadingDate().toString());

    }

    @Transactional
    @Test
    public void firstReadingForANewMeter() throws NoMainCounterException {
        final Apartment ap = apartmentService.getById(2L);
        MeterEnergy meter = new MeterEnergy("test", "newlyAdded", "added", ap);
        meterService.save(meter, Media.ENERGY);
        List<ReadingEnergy> list = IReadingService.getLatestNew(ap.getResidence(), Media.ENERGY);
        assertEquals(6, list.size());
    }

    @Test
    public void getById() {
        ReadingEnergy reading = IReadingService.getById(4L, Media.ENERGY);
        assertEquals(LocalDate.parse("2016-07-01"), reading.getReadingDate());
        assertEquals(2, reading.getValue(), 0);

    }

    @Test
    public void getPreviousReadings() {
        List<ReadingEnergy> list = IReadingService.getPreviousReading(LocalDate.parse("2016-08-01"), meterIdList);
        assertEquals(5, list.size());
        for (ReadingEnergy readingEnergy : list) {
            assertEquals("2016-07-01", readingEnergy.getReadingDate().toString());

        }
    }

    @Transactional
    @Test
    public void add() throws NoMainCounterException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());
        List<MeterEnergy> list = meterService.getListForOwner(Media.ENERGY);
        List<ReadingEnergy> toSave = new ArrayList<>();
        for (MeterEnergy meter : list) {
            ReadingEnergy reading = new ReadingEnergy(LocalDate.parse("2050-01-01"), 800, meter);
            toSave.add(reading);
        }
        final Residence r = residenceService.getById(1L);

        IReadingService.save(toSave, LocalDate.parse("2050-01-01"), Media.ENERGY);
        assertEquals(21, IReadingService.getList(Media.ENERGY).size());
        assertEquals(LocalDate.parse("2050-01-01"), IReadingService.getLatestNew(r, Media.ENERGY).get(0).getReadingDate());
    }

    @Transactional
    @Test
    public void update() throws NoMainCounterException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());
        final Residence r = residenceService.getById(1L);
        List<ReadingEnergy> list = IReadingService.getLatestNew(r, Media.ENERGY);

        for (ReadingEnergy aList : list) {
            aList.setValue(6767.0);
        }
        IReadingService.update(list, new LocalDate(), Media.ENERGY);
        List<ReadingEnergy> list2 = IReadingService.getLatestNew(r, Media.ENERGY);
        assertEquals(5, list2.size());
        for (ReadingEnergy readingEnergy : list2) {
            assertEquals(6767, readingEnergy.getValue(), 0);
        }
    }


}
