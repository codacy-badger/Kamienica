package kamienica.feature.invoice;

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
public class InvoiceGasConverter implements Converter<Object, InvoiceGas> {

	@Autowired
	InvoiceService service;

	@Override
	public InvoiceGas convert(Object element) {
		Long id = Long.parseLong((String) element);
		return service.getGasByID(id);
	}

}
