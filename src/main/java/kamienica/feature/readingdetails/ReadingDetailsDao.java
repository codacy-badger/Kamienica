package kamienica.feature.readingdetails;

import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.enums.Resolvement;
import kamienica.model.jpa.dao.BasicDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("readingDetails")
public class ReadingDetailsDao extends BasicDao<ReadingDetails> implements IReadingDetailsDao {

    @Override
    public ReadingDetails getLatest(final Residence residence, final Media media) {
        final DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ReadingDetails.class);
        detachedCriteria.add(Restrictions.eq("residence", residence));
        detachedCriteria.add(Restrictions.eq("media", media));
        detachedCriteria.setProjection(Projections.max("readingDate"));

        final Criteria c = createEntityCriteria();
        c.add(Restrictions.eq("residence", residence));
        c.add(Restrictions.eq("media", media));
        c.add(Property.forName("readingDate").eq(detachedCriteria));

        return (ReadingDetails) c.uniqueResult();
    }

    @Override
    public ReadingDetails getLatestPriorToDate(LocalDate date, Residence residence, Media media) {
        final DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ReadingDetails.class);
        detachedCriteria.add(Restrictions.eq("residence", residence));
        detachedCriteria.add(Restrictions.eq("media", media));
        detachedCriteria.add(Restrictions.lt("readingDate", date));
        detachedCriteria.setProjection(Projections.max("readingDate"));

        final Criteria c = createEntityCriteria();
        c.add(Restrictions.eq("residence", residence));
        c.add(Restrictions.eq("media", media));
        c.add(Property.forName("readingDate").eq(detachedCriteria));

        return (ReadingDetails) c.uniqueResult();
    }

    @Override
    public List<ReadingDetails> getUnresolved(final Residence residence, final Media media) {
        final Criterion resCrit = Restrictions.eq("residence", residence);
        final Criterion medCrit = Restrictions.eq("media", media);
        final Criterion unresvldCrit = Restrictions.eq("resolvement", Resolvement.UNRESOLVED);
        return findByCriteria(resCrit, medCrit, unresvldCrit);
    }
}
