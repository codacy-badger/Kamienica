package kamienica.conventer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import kamienica.model.InvoiceGas;
import kamienica.service.InvoiceService;

@Component
public class InvoiceGasConverter implements Converter<Object, InvoiceGas> {

	@Autowired
	InvoiceService service;

	@Override
	public InvoiceGas convert(Object element) {
		Integer id = Integer.parseInt((String) element);
		return service.getGasByID(id);
	}

}