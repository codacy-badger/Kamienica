package kamienica.feature.invoice;

import kamienica.core.enums.Media;
import kamienica.core.exception.InvalidDivisionException;
import kamienica.model.*;

import java.util.List;
import java.util.Map;

public interface InvoiceService {

	<T extends Reading> List<T> getUnpaidReadingForNewIncvoice(Media media) throws InvalidDivisionException;

	<T extends Invoice> void save(T invoice, Media media, Tenant tenant, Residence r) throws InvalidDivisionException;

	void list(Map<String, Object> model, Media media);
	
	List<? extends Invoice> getList(Media media);

	void delete(Long id, Media media);

	InvoiceGas getGasByID(Long id);

	InvoiceWater getWaterByID(Long id);

	InvoiceEnergy getEnergyByID(Long id);

}
