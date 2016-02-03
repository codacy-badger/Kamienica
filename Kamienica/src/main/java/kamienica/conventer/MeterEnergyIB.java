package kamienica.conventer;

import java.beans.PropertyEditorSupport;

import kamienica.model.MeterEnergy;
import kamienica.service.MeterService;

public class MeterEnergyIB extends PropertyEditorSupport {

	private final MeterService licznikService;
	
	public MeterEnergyIB(MeterService licznikService) {
		this.licznikService= licznikService;
			}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		MeterEnergy licznik = licznikService.getEnergyByID(Integer.valueOf(text));
        setValue(licznik);
}
}