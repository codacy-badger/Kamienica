package kamienica.conventer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import kamienica.model.MeterEnergy;
import kamienica.service.MeterService;

/**
 * 
 * @author macfol
 * 
 *         The class's role is to convert string value from the jsp form into
 *         object from DB
 *
 */
@Component
public class MeterEnergyConverter implements Converter<Object, MeterEnergy> {

	@Autowired
	MeterService service;

	@Override
	public MeterEnergy convert(Object element) {
		Integer id = Integer.parseInt((String) element);
		return service.getEnergyByID(id);
	}

}
