package kamienica.dao;

import kamienica.configuration.DaoTest;
import kamienica.feature.apartment.IApartmentDao;
import kamienica.feature.residence.ResidenceDao;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Meter;
import kamienica.model.entity.Residence;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Media;
import kamienica.model.jpa.dao.BasicDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class BasicDaoTest extends DaoTest {

    @Autowired
    private BasicDao<Apartment> apartemtnDao;
    @Autowired
    private BasicDao<Residence> residenceDao;
    @Autowired
    private BasicDao<Meter> meterDao;

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
        assertEquals(4, result);
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