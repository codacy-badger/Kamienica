package kamienica.feature.apartment;

import java.util.List;

import kamienica.model.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.feature.settings.SettingsDao;

import javax.validation.constraints.NotNull;

@Service
@Transactional
public class ApartmentServiceImpl implements ApartmentService {

    @Autowired
    ApartmentDao apartmentDAO;
    @Autowired
    SettingsDao settingsDao;

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
    public List<Apartment> paginatedList(@NotNull final Integer firstResult, @NotNull final Integer maxResults) {
        return apartmentDAO.paginatedList(firstResult, maxResults);
    }

    @Override
    public void deleteByID(Long id) {
        apartmentDAO.deleteById(id);
        settingsDao.changeDivisionState(false);

    }

    @Override
    public void update(Apartment apartment) {
        apartmentDAO.update(apartment);
    }

    @Override
    public Apartment getById(Long id) {
        return apartmentDAO.getById(id);

    }

}
