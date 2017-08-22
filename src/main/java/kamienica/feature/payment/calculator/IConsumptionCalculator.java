package kamienica.feature.payment.calculator;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.Invoice;
import kamienica.model.entity.MediaUsage;
import kamienica.model.entity.Reading;
import kamienica.model.exception.NegativeConsumptionValue;
import kamienica.model.exception.UsageCalculationException;

import java.util.List;

public interface IConsumptionCalculator {

    List<MediaUsage> calculateConsumption(Invoice invoice, List<Apartment> apartments);
}
