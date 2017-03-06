package kamienica.feature.payment;

import kamienica.model.entity.Invoice;
import kamienica.model.entity.Payment;
import kamienica.model.entity.Reading;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Media;
import kamienica.model.jpa.dao.BasicDao;

import java.util.List;

public interface IPaymentDao extends BasicDao<Payment> {

	void deleteByDate(String date);

	void deleteForInvoice(Invoice invoice);

	List<Payment> getByInvoice(Invoice invoice);

	List<Payment> getByReading(Reading reading);

	List<Payment> getPaymentForTenant(Tenant tenant, Media media);

	Payment getLatestPayment();

}
