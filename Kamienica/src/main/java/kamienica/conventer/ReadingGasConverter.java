package kamienica.conventer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import kamienica.model.ReadingGas;
import kamienica.service.ReadingService;

@Component
public class ReadingGasConverter implements Converter<Object, ReadingGas> {

	@Autowired
	ReadingService readingService;

	@Override
	public ReadingGas convert(Object element) {
		Integer id = Integer.parseInt((String) element);
		return readingService.getGasById(id);
	}

}
