package kamienica.model.conventer;

import kamienica.feature.tenant.ITenantService;
import kamienica.model.entity.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author macfol
 *         <p>
 *         The class's role is to convert string value from the jsp form into
 *         object from DB
 */
@Component
public class TenantConverter implements Converter<Object, Tenant> {

    @Autowired
    private ITenantService service;

    @Override
    public Tenant convert(Object element) {
        final Long id = Long.parseLong((String) element);
        return service.getById(id);
    }

}
