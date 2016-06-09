package kamienica.conventer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import kamienica.model.Tenant;
import kamienica.service.TenantService;
/**
 * 
 * @author macfol
 * 
 *         The class's role is to convert string value from the jsp form into
 *         object from DB
 *
 */
@Component
public class TenantConverter implements Converter<Object, Tenant> {

	@Autowired
	TenantService service;

	@Override
	public Tenant convert(Object element) {
		Integer id = Integer.parseInt((String) element);
		return service.getTenantById(id);
	}

}
