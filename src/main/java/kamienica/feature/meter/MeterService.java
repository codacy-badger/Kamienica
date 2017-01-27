package kamienica.feature.meter;

import kamienica.core.enums.Media;
import kamienica.model.Meter;

import java.util.List;
import java.util.Set;

public interface MeterService {

	<T extends Meter> void save(T meter, Media media);

	<T extends Meter> void update(T meter, Media media);

	void delete(Long id, Media media);

	<T extends Meter> List<T> list(Media media);

	<T extends Meter> List<T> getListForOwner(Media media);

	<T extends Meter> T getById(Long id, Media media);

	Set<Long> getIdList(Media media);

	Set<Long> getIdListForActiveMeters(Media media);

	boolean ifMainExists(Media media);

}
