package kamienica.feature.meter;

import kamienica.model.enums.Media;
import kamienica.model.entity.Meter;
import kamienica.model.entity.Residence;

import java.util.List;
import java.util.Set;

public interface IMeterService {

	void save(Meter meter);

	void update(Meter meter);

	void delete(Long id);

	List<Meter> list(Media media);

	List<Meter> list(final Residence r, Media media);

	List<Meter> getListForOwner(Media media);

	//TODO remove media
	Meter getById(Long id);

	Set<Long> getIdList(final Residence r, final Media media);

	Set<Long> getIdListForActiveMeters(Residence r, Media media);

	boolean ifMainExists(Media media);

}
