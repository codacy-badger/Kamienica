package kamienica.feature.apartment;

import kamienica.model.jpa.service.BasicService;
import kamienica.model.entity.Apartment;

import java.util.List;

public interface IApartmentService extends BasicService<Apartment> {

    List<Apartment> paginatedList(Integer firstResult, Integer maxResults);

}
