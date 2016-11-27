package kamienica.feature.payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.core.enums.Media;
import kamienica.model.Invoice;
import kamienica.feature.tenant.Tenant;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentDao<PaymentGas> gas;
	@Autowired
	private PaymentDao<PaymentEnergy> energy;
	@Autowired
	private PaymentDao<PaymentWater> water;

	@Override
	public List<? extends Payment> getPaymentList(Media media) {
		switch (media) {
		case ENERGY:
			return energy.getList();
		case GAS:
			return gas.getList();
		case WATER:
			return water.getList();
		default:
			return null;
		}

	}


	@Override
	public List<? extends Payment> getPaymentForTenant(Tenant tenant, Media media) {
		switch (media) {
		case ENERGY:
			return energy.getPaymentForTenant(tenant);
		case GAS:
			return gas.getPaymentForTenant(tenant);
		case WATER:
			return water.getPaymentForTenant(tenant);
		default:
			return null;
		}
	}

	@Override
	public List<? extends Payment> getPaymentByInvoice(Invoice invoice, Media media) {
		switch (media) {
		case ENERGY:
			return energy.getByInvoice(invoice);
		case GAS:
			return gas.getByInvoice(invoice);
		case WATER:
			return water.getByInvoice(invoice);
		default:
			return null;
		}
	}

}
