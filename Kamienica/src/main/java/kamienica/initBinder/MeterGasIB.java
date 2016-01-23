package kamienica.initBinder;

import java.beans.PropertyEditorSupport;

import kamienica.model.MeterGas;
import kamienica.service.MeterService;

public class MeterGasIB extends PropertyEditorSupport {

	private final MeterService licznikService;
	
	public MeterGasIB(MeterService licznikService) {
		this.licznikService= licznikService;
			}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		MeterGas licznik = licznikService.getGasByID(Integer.valueOf(text));
        setValue(licznik);
}
}
