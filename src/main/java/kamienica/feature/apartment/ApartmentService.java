package kamienica.feature.apartment;

import kamienica.model.Apartment;
import kamienica.model.Tenant;

import java.util.List;

public interface ApartmentService {

	void save(Apartment apartment);

	List<Apartment> getList();

	List<Apartment> getListForOwner();

	List<Apartment> paginatedList(Integer firstResult, Integer maxResults);

	void deleteByID(Long id);

	void update(Apartment apartment);

	Apartment getById(Long id);

}
