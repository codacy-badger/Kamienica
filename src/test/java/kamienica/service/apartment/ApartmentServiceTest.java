package kamienica.service.apartment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import kamienica.configuration.ServiceTest;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Residence;
import org.junit.Test;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;

@WithUserDetails(ServiceTest.OWNER)
public class ApartmentServiceTest extends ServiceTest {

    @Test
    public void getList() {
        final List<Apartment> list = apartmentService.list();
        assertEquals(6, list.size());
    }

    @Test
    public void getListForOwner() {
        final List<Apartment> list = apartmentService.listForOwner();
        assertEquals(5, list.size());
    }


    @Test
    public void getPaginatedList() {
        final List<Apartment> list = apartmentService.paginatedList(4, 1);
        final long apartmentId = list.get(0).getId();
        assertEquals(4L, apartmentId);
    }

    @Test
    public void getById() {
        Apartment apartment = apartmentService.getById(3L);
        assertEquals(2, apartment.getApartmentNumber());

    }

    @DirtiesContext
    @Test
    public void update() {
        final Apartment ap = apartmentService.getById(4L);
        ap.setDescription("test");
        apartmentService.update(ap);
        assertEquals("test", apartmentService.getById(4L).getDescription());

    }

    @Test(expected = Exception.class)
    public void addDuplicate() {
        final Residence r = residenceService.getById(1L);
        final Apartment ap = createDummyApartment(r);
        apartmentService.save(ap);
    }

    @DirtiesContext
    @Test
    public void addSameAppNumberButForDifferentResidence() {
        final Residence r = residenceService.getById(2L);
        final Apartment ap = createDummyApartment(r);
        apartmentService.save(ap);
        assertNotNull(ap.getId());
    }

    @Test
    public void shouldGetAllApartmentsForResidenceIncludingSharedPart() {
        final List<Apartment> result = apartmentService.getByResidence(1L, true);
        assertEquals(5, result.size());
    }

    @Test
    public void shouldGetAllApartmentsForResidenceWithoutSharedPart() {
        final List<Apartment> result = apartmentService.getByResidence(1L, false);
        assertEquals(4, result.size());
    }

    private Apartment createDummyApartment(final Residence r) {
        return new Apartment(1, "1234", "dummy", r);
    }
}
