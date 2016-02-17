package kamienica.conventer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import kamienica.model.InvoiceEnergy;
import kamienica.service.InvoiceService;

@Component
public class InvoiceEnergyConverter implements Converter<Object, InvoiceEnergy> {

	@Autowired
	InvoiceService service;

	@Override
	public InvoiceEnergy convert(Object element) {
		Integer id = Integer.parseInt((String) element);
		return service.getEnergyByID(id);
	}

}
