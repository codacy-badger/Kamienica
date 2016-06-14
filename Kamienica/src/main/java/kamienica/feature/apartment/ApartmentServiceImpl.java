package kamienica.feature.apartment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.feature.tenant.TenantDao;

@Service
@Transactional
public class ApartmentServiceImpl implements ApartmentService {

	@Autowired
	ApartmentDao apartmentDAO;
	@Autowired
	TenantDao tenantDAO;

	@Override
	public void save(Apartment apartment) {
		apartmentDAO.save(apartment);

	}

	@Override
	public List<Apartment> getList() {
		return apartmentDAO.getList();
	}

	@Override
	public void deleteByID(int id) {
		apartmentDAO.deleteById(id);

	}

	@Override
	public void update(Apartment apartment) {
		apartmentDAO.update(apartment);
	}

	@Override
	public Apartment getById(int id) {
		Apartment apartment = apartmentDAO.getById(id);
		return apartment;

	}

}
