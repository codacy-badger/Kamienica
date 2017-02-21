package kamienica.feature.meter;

import kamienica.model.enums.Media;
import kamienica.model.entity.Meter;
import kamienica.model.entity.Residence;

import java.util.List;
import java.util.Set;

public interface IMeterService {

	void save(Meter meter, Media media);

	void update(Meter meter);

	void delete(Long id, Media media);

	List<Meter> list(Media media);

	List<Meter> getListForOwner(Media media);

	Meter getById(Long id, Media media);

	Set<Long> getIdList(Media media);

	Set<Long> getIdListForActiveMeters(Residence r, Media media);

	boolean ifMainExists(Media media);

}
