package kamienica.initBinder;

import java.beans.PropertyEditorSupport;

import kamienica.model.MeterWater;
import kamienica.service.MeterService;

public class MeterWaterIB extends PropertyEditorSupport {

	private final MeterService licznikService;
	
	public MeterWaterIB(MeterService licznikService) {
		this.licznikService= licznikService;
			}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		MeterWater licznik = licznikService.getWaterByID(Integer.valueOf(text));
        setValue(licznik);
}

}
