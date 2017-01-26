package kamienica.feature.apartment;

import kamienica.core.util.SecurityDetails;
import kamienica.feature.residence.ResidenceService;
import kamienica.feature.settings.SettingsDao;
import kamienica.model.Apartment;
import kamienica.model.Residence;
import kamienica.model.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentDao apartmentDAO;
    private final SettingsDao settingsDao;
    private final ResidenceService residenceService;

    @Autowired
    public ApartmentServiceImpl(ApartmentDao apartmentDAO, SettingsDao settingsDao, ResidenceService residenceService) {
        this.apartmentDAO = apartmentDAO;
        this.settingsDao = settingsDao;
        this.residenceService = residenceService;
    }

    @Override
    public void save(Apartment apartment) {
        apartmentDAO.save(apartment);
        settingsDao.changeDivisionState(false);
    }

    @Override
    public List<Apartment> getList() {
        return apartmentDAO.getList();
    }

    @Override
    public List<Apartment> getListForOwner() {
        List<Residence> residences = SecurityDetails.getResidencesForOwner();
        return apartmentDAO.findForResidence(residences);
    }

    @Override
    public List<Apartment> paginatedList(@NotNull final Integer firstResult, @NotNull final Integer maxResults) {
        return apartmentDAO.paginatedList(firstResult, maxResults);
    }

    @Override
    public void deleteByID(Long id) {
        apartmentDAO.deleteById(id);
        settingsDao.changeDivisionState(false);

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
