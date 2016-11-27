package kamienica.core.conventer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import kamienica.model.Apartment;
import kamienica.feature.apartment.ApartmentService;

/**
 * 
 * @author macfol
 * 
 *         The class's role is to convert string value from the jsp form into
 *         object from DB
 *
 */
@Component
public class ApartmentConverter implements Converter<Object, Apartment> {

	@Autowired
	private ApartmentService service;

	@Override
	public Apartment convert(Object element) {
		Long id = Long.parseLong((String) element);
		return service.getById(id);
	}

}
