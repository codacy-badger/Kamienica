package kamienica.feature.apartment;

import kamienica.core.util.SecurityDetails;
import kamienica.feature.residence.IResidenceDao;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Residence;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
public class ApartmentService implements IApartmentService {

    private final IApartmentDao apartmentDAO;
    private final IResidenceDao residenceDao;

    @Autowired
    public ApartmentService(final IApartmentDao apartmentDAO, final IResidenceDao residenceDao) {
        this.apartmentDAO = apartmentDAO;
        this.residenceDao = residenceDao;
    }

    @Override
    public void save(Apartment apartment) {
        apartmentDAO.save(apartment);
    }

    @Override
    public List<Apartment> list() {
        return apartmentDAO.getList();
    }

    @Override
    public List<Apartment> listForOwner() {
        List<Residence> residences = SecurityDetails.getResidencesForOwner();
        return apartmentDAO.findForResidence(residences);
    }

    @Override
    public List<Apartment> paginatedList(@NotNull final Integer firstResult, @NotNull final Integer maxResults) {
        return apartmentDAO.paginatedList(firstResult, maxResults);
    }

    @Override
    public List<Apartment> getByResidence(final Long residenceId, final boolean showSharedPart) {
        final Residence r = residenceDao.getById(residenceId);
        final Criterion c = Restrictions.eq("residence", r);
        if(showSharedPart) {
            return apartmentDAO.findByCriteria(c);
        }
        final Criterion noSharedPart = Restrictions.ne("apartmentNumber", 0);
        return apartmentDAO.findByCriteria(c, noSharedPart);
    }

    @Override
    public void delete(Apartment object) {
        apartmentDAO.delete(object);
    }

    @Override
    public void update(final Apartment apartment) {
        apartmentDAO.update(apartment);
    }

    @Override
    public Apartment getById(final Long id) {
        return apartmentDAO.getById(id);

    }

}
