package kamienica.model.conventer;


import kamienica.feature.reading.IReadingService;
import kamienica.model.entity.Reading;
import kamienica.model.enums.Media;
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
public class ReadingConverter implements Converter<Object, Reading>{

	@Autowired
	private IReadingService IReadingService;
	
	@Override
	public Reading convert(Object element) {
		final Long id = Long.parseLong((String) element);
		return IReadingService.getById(id);
	}

}
