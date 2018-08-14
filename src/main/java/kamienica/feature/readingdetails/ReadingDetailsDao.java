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

    private static final String RESIDENCE = "residence";
    private static final String MEDIA = "media";
    private static final String READING_DATE = "readingDate";

    @Override
    public ReadingDetails getLatest(final Residence residence, final Media media) {
        final DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ReadingDetails.class);
        detachedCriteria.add(Restrictions.eq(RESIDENCE, residence));
        detachedCriteria.add(Restrictions.eq(MEDIA, media));
        detachedCriteria.setProjection(Projections.max(READING_DATE));

        final Criteria c = createCriteria(residence, media, detachedCriteria);

        return (ReadingDetails) c.uniqueResult();
    }

    @Override
    public ReadingDetails getLatestPriorToDate(final LocalDate date, final Residence residence, final Media media) {
        final DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ReadingDetails.class);
        detachedCriteria.add(Restrictions.eq(RESIDENCE, residence));
        detachedCriteria.add(Restrictions.eq(MEDIA, media));
        detachedCriteria.add(Restrictions.lt(READING_DATE, date));
        detachedCriteria.setProjection(Projections.max(READING_DATE));

        final Criteria c = createCriteria(residence, media, detachedCriteria);

        return (ReadingDetails) c.uniqueResult();
    }

    private Criteria createCriteria(Residence residence, Media media,
        DetachedCriteria detachedCriteria) {
        final Criteria c = createEntityCriteria();
        c.add(Restrictions.eq(RESIDENCE, residence));
        c.add(Restrictions.eq(MEDIA, media));
        c.add(Property.forName(READING_DATE).eq(detachedCriteria));
        return c;
    }

    @Override
    public List<ReadingDetails> getUnresolved(final Residence residence, final Media media) {
        final Criterion resCrit = Restrictions.eq(RESIDENCE, residence);
        final Criterion medCrit = Restrictions.eq(MEDIA, media);
        final Criterion unresvldCrit = Restrictions.eq("resolvement", Resolvement.UNRESOLVED);
        return findByCriteria(resCrit, medCrit, unresvldCrit);
    }

    @Override
    public List<ReadingDetails> getUnresolved(Media media) {
        final Criterion medCrit = Restrictions.eq(MEDIA, media);
        final Criterion unresvldCrit = Restrictions.eq("resolvement", Resolvement.UNRESOLVED);
        return findByCriteria(medCrit, unresvldCrit);
    }
}
