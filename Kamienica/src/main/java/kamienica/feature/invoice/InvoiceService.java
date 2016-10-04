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

	public <T extends Reading> List<T> prepareForRegistration(Media media) throws InvalidDivisionException;

	public <T extends Invoice> void save(T invoice, Media media);

	public void saveEnergy(InvoiceEnergy invoice, List<PaymentEnergy> payment);

	public void saveGas(InvoiceGas invoice, List<PaymentGas> payment);

	public void saveWater(InvoiceWater invoice, List<PaymentWater> payment);

	public void list(Map<String, Object> model, Media media);
	
	public List<? extends Invoice> getList(Media media);

//	public List<InvoiceWater> getWaterInvoiceList();
//
//	public List<InvoiceGas> getGasInvoiceList();
//
//	public List<InvoiceEnergy> getEnergyInvoiceList();

	public void delete(Long id, Media media);

	public void deleteGasByID(Long id);

	public void deleteWaterByID(Long id);

	public void deleteEnergyByID(Long id);

	public <T extends Invoice> void update(T invoice, Media media);

	public void updateGas(InvoiceGas invoice, List<PaymentGas> payments);

	public void updateWater(InvoiceWater invoice, List<PaymentWater> payments);

	public void updateEnergy(InvoiceEnergy invoice, List<PaymentEnergy> payments);

	public InvoiceGas getGasByID(Long id);

	public InvoiceWater getWaterByID(Long id);

	public InvoiceEnergy getEnergyByID(Long id);

	public List<InvoiceEnergy> getUnpaidInvoiceEnergy();

	public List<InvoiceGas> getUnpaidInvoiceGas();

	public List<InvoiceWater> getUnpaidInvoiceWater();

	public InvoiceEnergy getLatestPaidEnergy();

	public InvoiceWater getLatestPaidWater();

	public InvoiceGas getLatestPaidGas();

}
