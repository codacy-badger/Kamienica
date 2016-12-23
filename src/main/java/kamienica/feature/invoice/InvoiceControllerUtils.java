//package kamienica.feature.invoice;
//
//import kamienica.feature.reading.Reading;
//
//import java.util.HashMap;
//import java.util.List;
//
//public class InvoiceControllerUtils {
//
//	public InvoiceControllerUtils() {
//	}
//
//	public <T extends Reading> void checkIfListIsEmpty(HashMap<String, Object> model, List<T> readings) {
//		if (readings.isEmpty()) {
//			model.put("error", "Brakuje odczytów dla nowej faktury");
//		} else {
//			model.put("readings", readings);
//		}
//	}
//
//	public HashMap<String, Object> setUrlForEnergy() {
//		HashMap<String, Object> model = new HashMap<>();
//		model.put("saveUrl", "/Admin/Invoice/invoiceEnergySave.html");
//		model.put("media", "Energia");
//		return model;
//	}
//
//	public void setUrlForGas(HashMap<String, Object> model) {
//		model.put("saveUrl", "/Admin/Invoice/invoiceGasSave");
//		model.put("media", "Gaz");
//	}
//
//}
