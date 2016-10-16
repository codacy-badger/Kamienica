package kamienica.feature.invoice;

import java.util.List;
import java.util.Map;

import kamienica.core.exception.InvalidDivisionException;
import kamienica.core.util.Media;
import kamienica.feature.payment.PaymentEnergy;
import kamienica.feature.payment.PaymentGas;
import kamienica.feature.payment.PaymentWater;
import kamienica.feature.reading.Reading;

public interface InvoiceService {

	<T extends Reading> List<T> prepareForRegistration(Media media) throws InvalidDivisionException;

	<T extends Invoice> void save(T invoice, Media media);

	void saveEnergy(InvoiceEnergy invoice, List<PaymentEnergy> payment);

	void saveGas(InvoiceGas invoice, List<PaymentGas> payment);

	void saveWater(InvoiceWater invoice, List<PaymentWater> payment);

	void list(Map<String, Object> model, Media media);
	
	List<? extends Invoice> getList(Media media);

//	public List<InvoiceWater> getWaterInvoiceList();
//
//	public List<InvoiceGas> getGasInvoiceList();
//
//	public List<InvoiceEnergy> getEnergyInvoiceList();

	void delete(Long id, Media media);

	void deleteGasByID(Long id);

	void deleteWaterByID(Long id);

	void deleteEnergyByID(Long id);

	<T extends Invoice> void update(T invoice, Media media);

	void updateGas(InvoiceGas invoice, List<PaymentGas> payments);

	void updateWater(InvoiceWater invoice, List<PaymentWater> payments);

	void updateEnergy(InvoiceEnergy invoice, List<PaymentEnergy> payments);

	InvoiceGas getGasByID(Long id);

	InvoiceWater getWaterByID(Long id);

	InvoiceEnergy getEnergyByID(Long id);

	List<InvoiceEnergy> getUnpaidInvoiceEnergy();

	List<InvoiceGas> getUnpaidInvoiceGas();

	List<InvoiceWater> getUnpaidInvoiceWater();

	InvoiceEnergy getLatestPaidEnergy();

	InvoiceWater getLatestPaidWater();

	InvoiceGas getLatestPaidGas();

}
