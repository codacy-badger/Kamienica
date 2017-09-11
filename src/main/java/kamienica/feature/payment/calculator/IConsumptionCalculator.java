package kamienica.feature.payment.calculator;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.Invoice;
import kamienica.model.entity.MediaUsage;

import java.util.List;

public interface IConsumptionCalculator {

    List<MediaUsage> calculateConsumption(Invoice invoice, List<Apartment> apartments);
}
