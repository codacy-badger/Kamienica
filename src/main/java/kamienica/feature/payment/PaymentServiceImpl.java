package kamienica.feature.payment;

import kamienica.core.enums.Media;
import kamienica.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	private PaymentDao<PaymentGas> gas;
	private PaymentDao<PaymentEnergy> energy;
	private PaymentDao<PaymentWater> water;

	@Autowired
	public PaymentServiceImpl(PaymentDao<PaymentGas> gas, PaymentDao<PaymentEnergy> energy, PaymentDao<PaymentWater> water) {
		this.gas = gas;
		this.energy = energy;
		this.water = water;
	}

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

//	@Override
//	public List<? extends Payment> getPaymentByInvoice(Invoice invoice, Media media) {
//		switch (media) {
//		case ENERGY:
//			return energy.getByInvoice(invoice);
//		case GAS:
//			return gas.getByInvoice(invoice);
//		case WATER:
//			return water.getByInvoice(invoice);
//		default:
//			return null;
//		}
//	}

}
