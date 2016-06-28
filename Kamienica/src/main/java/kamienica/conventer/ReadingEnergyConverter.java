package kamienica.conventer;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import kamienica.feature.reading.ReadingEnergy;
import kamienica.feature.reading.ReadingService;

/**
 * 
 * @author macfol
 * 
 *         The class's role is to convert string value from the jsp form into
 *         object from DB
 *
 */
@Component
public class ReadingEnergyConverter implements Converter<Object, ReadingEnergy>{

	@Autowired
	ReadingService readingService;
	
	@Override
	public ReadingEnergy convert(Object element) {
		Long id = Long.parseLong((String) element);
		return readingService.getEnergyById(id);
	}

}
