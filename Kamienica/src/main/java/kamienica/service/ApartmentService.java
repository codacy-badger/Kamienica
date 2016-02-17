package kamienica.service;

import java.util.List;

import kamienica.model.Apartment;

public interface ApartmentService {

	public void save(Apartment apartment);

	public List<Apartment> getList();

	public void deleteByID(int id);

	public void update(Apartment apartment);

	public Apartment getById(int id);
	
//	public Apartment getByApartmentNumber(int number);
}
