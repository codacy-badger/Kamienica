package kamienica.feature.apartment;

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
public class ApartmentConverter implements Converter<Object, Apartment> {

	@Autowired
	private ApartmentService service;

	@Override
	public Apartment convert(Object element) {
		Integer id = Integer.parseInt((String) element);
		return service.getById(id);
	}

}
