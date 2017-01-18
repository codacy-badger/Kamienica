package kamienica.core.conventer;

import kamienica.feature.invoice.InvoiceService;
import kamienica.model.InvoiceGas;
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
    private InvoiceService service;

	@Override
	public InvoiceGas convert(Object element) {
		final Long id = Long.parseLong((String) element);
		return service.getGasByID(id);
	}

}
