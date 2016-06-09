package kamienica.conventer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import kamienica.model.InvoiceWater;
import kamienica.service.InvoiceService;

/**
 * 
 * @author macfol
 * 
 *         The class's role is to convert string value from the jsp form into
 *         object from DB
 *
 */
@Component
public class InvoiceWaterConverter implements Converter<Object, InvoiceWater> {

	@Autowired
	InvoiceService service;

	@Override
	public InvoiceWater convert(Object element) {
		Integer id = Integer.parseInt((String) element);
		return service.getWaterByID(id);
	}

}
