package kamienica.feature.apartment;

import java.util.List;

public interface ApartmentService {

	public void save(Apartment apartment);

	public List<Apartment> getList();

	public List<Apartment> paginatedList(Integer firstResult, Integer maxResults);

	public void deleteByID(Long id);

	public void update(Apartment apartment);

	public Apartment getById(Long id);

}
