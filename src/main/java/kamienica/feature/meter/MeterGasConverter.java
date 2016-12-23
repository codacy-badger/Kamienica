package kamienica.feature.meter;

import kamienica.core.enums.Media;
import kamienica.model.MeterGas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MeterGasConverter implements Converter<Object, MeterGas> {

	@Autowired
	MeterService service;

	@Override
	public MeterGas convert(Object element) {
		Long id = Long.parseLong((String) element);
		return service.getById(id, Media.GAS);
	}
}
