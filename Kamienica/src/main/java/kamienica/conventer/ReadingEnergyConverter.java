package kamienica.conventer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import kamienica.model.ReadingEnergy;
import kamienica.service.ReadingService;

@Component
public class ReadingEnergyConverter implements Converter<Object, ReadingEnergy>{

	@Autowired
	ReadingService readingService;
	
	@Override
	public ReadingEnergy convert(Object element) {
		Integer id = Integer.parseInt((String)element);
		return readingService.getEnergyById(id);
	}

}
