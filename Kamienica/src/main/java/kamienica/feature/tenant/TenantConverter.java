package kamienica.feature.tenant;

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
public class TenantConverter implements Converter<Object, Tenant> {

	@Autowired
	TenantService service;

	@Override
	public Tenant convert(Object element) {
		Long id = Long.parseLong((String) element);
		return service.getTenantById(id);
	}

}
