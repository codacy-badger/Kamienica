package kamienica.model.conventer;

import kamienica.feature.invoice.IInvoiceService;
import kamienica.model.entity.Invoice;
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
public class InvoiceConverter implements Converter<Object, Invoice> {

	@Autowired
	private IInvoiceService service;

	@Override
	public Invoice convert(Object element) {
		final Long id = Long.parseLong((String) element);
		return service.getByID(id);
	}

}
