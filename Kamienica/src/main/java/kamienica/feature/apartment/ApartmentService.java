package kamienica.feature.apartment;

import java.util.List;

public interface ApartmentService {

	public void save(Apartment apartment);

	public List<Apartment> getList();

	public void deleteByID(Long id);

	public void update(Apartment apartment);

	public Apartment getById(Long id);
	
}
