package kamienica.conventer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import kamienica.model.ReadingWater;
import kamienica.service.ReadingService;

@Component
public class ReadingWaterConverter implements Converter<Object, ReadingWater> {

	@Autowired
	ReadingService readingService;

	@Override
	public ReadingWater convert(Object element) {
		Integer id = Integer.parseInt((String) element);
		return readingService.getWaterById(id);
	}

}
