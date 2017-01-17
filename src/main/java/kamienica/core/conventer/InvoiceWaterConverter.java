package kamienica.core.conventer;

import kamienica.feature.invoice.InvoiceService;
import kamienica.model.InvoiceWater;
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
public class InvoiceWaterConverter implements Converter<Object, InvoiceWater> {

    @Autowired
    private InvoiceService service;

    @Override
    public InvoiceWater convert(Object element) {
        final Long id = Long.parseLong((String) element);
        return service.getWaterByID(id);
    }

}
