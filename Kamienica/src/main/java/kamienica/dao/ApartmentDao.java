package kamienica.dao;

import java.util.List;

import kamienica.model.Apartment;


public interface ApartmentDao {

	public void save(Apartment apartment);

	public List<Apartment> getList();

	public void deleteByID(int id);

	public void update(Apartment apartment);

	public Apartment getById(int id);

	public Apartment getByAparmentNumber(int number);
}
