package kamienica.feature.reading;

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
		Integer id = Integer.parseInt((String) element);
		return readingService.getGasById(id);
	}

}
