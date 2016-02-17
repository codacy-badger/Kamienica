package kamienica.conventer;

import java.beans.PropertyEditorSupport;

import kamienica.model.Apartment;
import kamienica.service.ApartmentService;

public class ApartmentIB extends PropertyEditorSupport {

	private final ApartmentService mieszkanieService;

	public ApartmentIB(ApartmentService mieszkanieService) {
		this.mieszkanieService = mieszkanieService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (Integer.valueOf(text) == -1) {
			setValue(null);
		} else {
			Apartment mieszkanie = mieszkanieService.getById(Integer.valueOf(text));
			setValue(mieszkanie);
		}
	}
}