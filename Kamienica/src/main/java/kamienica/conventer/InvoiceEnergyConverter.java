package kamienica.conventer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import kamienica.model.InvoiceEnergy;
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
public class InvoiceEnergyConverter implements Converter<Object, InvoiceEnergy> {

	@Autowired
	private InvoiceService service;

	@Override
	public InvoiceEnergy convert(Object element) {
		Integer id = Integer.parseInt((String) element);
		return service.getEnergyByID(id);
	}

}
