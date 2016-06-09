package kamienica.feature.meter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MeterGasConverter implements Converter<Object, MeterGas> {

	@Autowired
	MeterService service;

	@Override
	public MeterGas convert(Object element) {
		Integer id = Integer.parseInt((String) element);
		return service.getGasByID(id);
	}
}
