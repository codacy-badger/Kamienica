package kamienica.initBinder;

import java.beans.PropertyEditorSupport;

import kamienica.model.Tenant;
import kamienica.service.TenantService;

public class TenantIB extends PropertyEditorSupport {

	private final TenantService najemcaService;

	public TenantIB(TenantService najemcaService) {
		this.najemcaService = najemcaService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Tenant najemca = najemcaService.getTenantById(Integer.valueOf(text));
		setValue(najemca);
	}
}