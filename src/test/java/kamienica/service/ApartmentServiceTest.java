package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.core.util.SecurityDetails;
import kamienica.model.Apartment;
import kamienica.model.Residence;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

public class ApartmentServiceTest extends ServiceTest {

    @Test
    public void getList() {
        List<Apartment> list = apartmentService.list();
        assertEquals(4, list.size());
    }

    @Test
    public void getListForOwner() {
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());
        List<Apartment> list = apartmentService.listForOwner();
        assertEquals(4, list.size());
    }


    @Test
    public void getPaginatedList() {
        List<Apartment> list = apartmentService.paginatedList(4, 1);
        final long apartmentId = list.get(0).getId();
        assertEquals(4L, apartmentId);
    }

    @Test
    public void getById() {
        Apartment apartment = apartmentService.getById(3L);
        assertEquals(2, apartment.getApartmentNumber());

    }

    @Transactional
    @Test
    public void remove() {
        apartmentService.deleteById(5L);
        assertEquals(4, apartmentService.list().size());

    }

    @Transactional
    @Test
    public void update() {
        Apartment ap = apartmentService.getById(4L);
        ap.setDescription("test");
        apartmentService.update(ap);
        assertEquals("test", apartmentService.getById(4L).getDescription());

    }

    @Test(expected = Exception.class)
    public void addDuplicate() {
        final Residence r = residenceService.getById(1L);
        Apartment ap = new Apartment(1, "1234", "cośtam", r);
        apartmentService.save(ap);

    }

    @Transactional
    @Test
    public void addSameAppNumberButForDifferentResidence() {
        final Residence r = residenceService.getById(2L);
        Apartment ap = new Apartment(1, "1234", "cośtam", r);
        apartmentService.save(ap);

    }

}
