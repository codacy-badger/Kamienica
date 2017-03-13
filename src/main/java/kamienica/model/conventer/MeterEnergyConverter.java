package kamienica.model.conventer;

import kamienica.feature.meter.IMeterService;
import kamienica.model.entity.Meter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 
 * @author macfol
 * 
 *         The class's role is to convert string value from the jsp form into
 *         object from DB
 *
 */
@Component
public class MeterEnergyConverter implements Converter<Object, Meter> {

	@Autowired
    private IMeterService service;

	@Override
	public Meter convert(Object element) {
		Long id = Long.parseLong((String) element);
		return service.getById(id);
	}

}
