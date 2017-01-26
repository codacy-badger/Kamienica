package kamienica.feature.apartment;

import kamienica.core.daoservice.BasicService;
import kamienica.model.Apartment;

import java.util.List;

public interface ApartmentService extends BasicService<Apartment> {

    List<Apartment> paginatedList(Integer firstResult, Integer maxResults);

}
