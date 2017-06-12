package kamienica.dao;

import kamienica.configuration.DaoTest;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Meter;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.jpa.dao.IBasicDao;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class BasicDaoTest extends DaoTest {

    @Autowired
    private IBasicDao<Apartment> apartemtnDao;
    @Autowired
    private IBasicDao<Residence> residenceDao;
    @Autowired
    private IBasicDao<Meter> meterDao;

    private final static String SQL = "SELECT * FROM RESIDENCE";

    @Test
    public void findById() {
        Apartment ap = apartemtnDao.getById(1L);
        assertNotNull(ap);
    }

    @Test
    public void getIdList() {
        final Residence r = residenceDao.getById(1L);
        final Set<Long> result = meterDao.getIdList(r, Media.ENERGY);
        assertEquals(5, result.size());

        final long idFromAnotherResidence = 6L;
        assertFalse(result.contains(idFromAnotherResidence));
    }

    @Test
    public void countByCriteria() {
        final Residence r = residenceDao.getById(1L);
        final Criterion c = Restrictions.eq("residence", r);
        long result = apartemtnDao.countByCriteria(c);
        assertEquals(5, result);
    }

    @Test
    public void getBySQLQuery() {
        List<Residence> result = residenceDao.getBySQLQuery(SQL);
        assertEquals(2, result.size());
    }

    @Test
    @Ignore("java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to kamienica.model.entity.Residence")
    public void getOneBySQLQuery() {
        final String whereClause = " WHERE ID=1";
        Residence result = residenceDao.getOneBySQLQuery(SQL+whereClause);
        final long resultedId = result.getId();
        assertEquals(1L, resultedId);
    }

}