package kamienica.core.conventer;

import kamienica.core.enums.Media;
import kamienica.feature.reading.ReadingGas;
import kamienica.feature.reading.ReadingService;
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
public class ReadingGasConverter implements Converter<Object, ReadingGas> {

	@Autowired
	ReadingService readingService;

	@Override
	public ReadingGas convert(Object element) {
		Long id = Long.parseLong((String) element);
		return readingService.getById(id, Media.GAS);
	}

}