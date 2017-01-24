package kamienica.core.conventer;


import kamienica.core.enums.Media;
import kamienica.feature.reading.ReadingService;
import kamienica.model.ReadingEnergy;
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
public class ReadingEnergyConverter implements Converter<Object, ReadingEnergy>{

	@Autowired
	private ReadingService readingService;
	
	@Override
	public ReadingEnergy convert(Object element) {
		final Long id = Long.parseLong((String) element);
		return readingService.getById(id, Media.ENERGY);
	}

}
