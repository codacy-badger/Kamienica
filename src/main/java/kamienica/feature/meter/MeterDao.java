package kamienica.feature.meter;

import kamienica.model.entity.Meter;
import kamienica.model.entity.Residence;
import kamienica.model.jpa.dao.BasicDaoImpl;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository("meter")
public class MeterDao extends BasicDaoImpl<Meter> implements IMeterDao {

    @Override
    public boolean ifMainExists() {

        Criteria crit = getSession().createCriteria(persistentClass);
        crit.add(Restrictions.eq("main", true));
        crit.setProjection(Projections.rowCount());
        Long count = (Long) crit.uniqueResult();
        return (count >= 1);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<Long> getIdListForActiveMeters(final Residence r) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.gt("deactivation", new LocalDate()));
        criteria.add(Restrictions.eq("residence", r));
        criteria.setProjection(Projections.property("id"));
        return new HashSet<>(criteria.list());

    }


}
