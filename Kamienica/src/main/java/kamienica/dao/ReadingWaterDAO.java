package kamienica.dao;

import java.util.HashMap;
import java.util.List;

import kamienica.model.InvoiceGas;
import kamienica.model.InvoiceWater;
import kamienica.model.ReadingWater;

public interface ReadingWaterDAO extends ReadingDao<ReadingWater, InvoiceWater> {

	public HashMap<String, List<ReadingWater>> getWaterReadingForGasConsumption(InvoiceGas invoice);

}
