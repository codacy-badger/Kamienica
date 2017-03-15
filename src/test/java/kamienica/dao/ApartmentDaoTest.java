package kamienica.dao;

import kamienica.configuration.DaoTest;
import kamienica.feature.apartment.IApartmentDao;
import kamienica.feature.residence.ResidenceDao;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Residence;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ApartmentDaoTest extends DaoTest {

    @Autowired
    private IApartmentDao apartemtnDao;
    @Autowired
    private ResidenceDao residenceDao;

    @Test
    public void findById() {
        Apartment ap = apartemtnDao.getById(1L);
        assertNotNull(ap);
    }

    @Test
    @Transactional
    public void getNumOfEmptyApartment() throws Exception {
        final int result = apartemtnDao.getNumOfEmptyApartment();
        assertEquals(0, result);
    }

    @Test
    public void getListForOwner() throws Exception {
        final Residence r = residenceDao.getById(1L);
        List<Residence> residences = Collections.singletonList(r);
        List<Apartment> result = apartemtnDao.getListForOwner(residences);
        assertEquals(4, result.size());
    }

}