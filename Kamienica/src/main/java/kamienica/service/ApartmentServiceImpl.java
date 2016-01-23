package kamienica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.dao.ApartmentDao;
import kamienica.model.Apartment;

@Service
@Transactional
public class ApartmentServiceImpl implements ApartmentService {

	@Autowired
	ApartmentDao apartmentDAO;

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
		apartmentDAO.deleteByID(id);

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

	@Override
	public Apartment getByApartmentNumber(int number) {
		Apartment apartment = apartmentDAO.getByAparmentNumber(number);
		if (apartment != null ) {
		System.out.println(apartment.toString()); 
		}
		return apartment;
	}

}
