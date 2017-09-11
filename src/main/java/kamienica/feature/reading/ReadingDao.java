package kamienica.feature.reading;

import kamienica.model.entity.Reading;
import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.jpa.dao.BasicDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("readingDao")
public class ReadingDao extends BasicDao<Reading> implements IReadingDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<Reading> getList(Residence r, Media media) {
        Criteria c = createEntityCriteria();
        c.createCriteria("meter").add(Restrictions.eq("residence", r));
        c.createCriteria("readingDetails").add(Restrictions.eq("media", media)).addOrder(Order.desc("readingDate"));
        return c.list();
    }

    @Override
    public List<Reading> list(ReadingDetails details) {
        return findByCriteria(Restrictions.eq("readingDetails", details));
    }
}
