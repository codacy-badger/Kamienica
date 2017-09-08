package kamienica.feature.meter;

import kamienica.model.entity.Meter;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;

import java.util.List;
import java.util.Set;

public interface IMeterService {

	void save(Meter meter);

	void update(Meter meter);

	void delete(Long id);

	List<Meter> list(Media media);

	List<Meter> list(final Residence r, Media media);

	List<Meter> getListForOwner(Media media);

	Meter getById(Long id);

	Set<Long> getIdListForActiveMeters(Residence r, Media media);

	boolean ifMainExists(Media media);

}
