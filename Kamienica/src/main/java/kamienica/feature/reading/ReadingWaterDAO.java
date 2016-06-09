package kamienica.feature.reading;

import java.util.HashMap;
import java.util.List;

import kamienica.feature.invoice.InvoiceGas;
import kamienica.feature.invoice.InvoiceWater;

public interface ReadingWaterDAO extends ReadingDao<ReadingWater, InvoiceWater> {

	public HashMap<String, List<ReadingWater>> getWaterReadingForGasConsumption(InvoiceGas invoice);

}
