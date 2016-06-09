package kamienica.conventer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import kamienica.model.MeterWater;
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
public class MeterWaterConverter implements Converter<Object, MeterWater> {

	@Autowired
	MeterService service;

	@Override
	public MeterWater convert(Object element) {
		Integer id = Integer.parseInt((String) element);
		return service.getWaterByID(id);
	}

}
