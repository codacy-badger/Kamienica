package kamienica.conventer;

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
import kamienica.model.MeterGas;
import kamienica.service.MeterService;

@Component
public class MeterGasConverter implements Converter<Object, MeterGas> {

	@Autowired
	MeterService service;

	@Override
	public MeterGas convert(Object element) {
		Integer id = Integer.parseInt((String) element);
		return service.getGasByID(id);
	}
}
