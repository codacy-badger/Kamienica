package kamienica.feature.payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.feature.invoice.Invoice;
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

//	@Override
//	public List<PaymentAbstract> list(Media media) {
//		List<PaymentAbstract> list;
//		switch (media) {
//		case ENERGY:
//
//			return energy.getList();
//		case GAS:
//
//			break;
//		case WATER:
//
//			break;
//
//		default:
//			break;
//		}
//	}

	@Override
	public List<PaymentGas> getPaymentGasByInvoice(Invoice invoice) {

		return gas.getByInvoice(invoice);
	}

	@Override
	public List<PaymentWater> getPaymentWaterByInvoice(Invoice invoice) {

		return water.getByInvoice(invoice);
	}

	@Override
	public List<PaymentEnergy> getPaymentEnergyList() {
		return energy.getList();
	}

	@Override
	public List<PaymentGas> getPaymentGasList() {
		return gas.getList();
	}

	@Override
	public List<PaymentWater> getPaymentWaterList() {
		return water.getList();
	}

	// @Override
	// public void saveGasList(List<PaymentGas> payment) {
	// for (PaymentGas paymentGas : payment) {
	// gas.save(paymentGas);
	// }
	//
	// }
	//
	// @Override
	// public void saveWaterList(List<PaymentWater> payment) {
	// for (PaymentWater paymentWater : payment) {
	// water.save(paymentWater);
	// }
	//
	// }
	//
	// @Override
	// public void saveEnergyList(List<PaymentEnergy> payment) {
	// for (PaymentEnergy paymentEnergy : payment) {
	// energy.save(paymentEnergy);
	// }
	// }

	@Override
	public List<PaymentEnergy> getEnergyByInvoice(Invoice invoice) {

		return energy.getByInvoice(invoice);
	}

	@Override
	public List<PaymentEnergy> getPaymentEnergyForTenant(Tenant tenant) {
		return energy.getPaymentForTenant(tenant);
	}

	@Override
	public List<PaymentGas> getPaymentGasForTenant(Tenant tenant) {
		return gas.getPaymentForTenant(tenant);
	}

	@Override
	public List<PaymentWater> getPaymentWaterForTenant(Tenant tenant) {
		return water.getPaymentForTenant(tenant);
	}

	// @Override
	// public void deleteEnergyByDate(Long id) {
	// PaymentEnergy forDeletion = energy.getById(id);
	// energy.deleteByDate(forDeletion.getPaymentDate().toString());
	// }
	//
	// @Override
	// public void deleteWaterByDate(Long id) {
	// PaymentWater forDeletion = water.getById(id);
	// water.deleteByDate(forDeletion.getPaymentDate().toString());
	//
	// }
	//
	// @Override
	// public void deleteGasByDate(Long id) {
	// PaymentGas forDeletion = gas.getById(id);
	// gas.deleteByDate(forDeletion.getPaymentDate().toString());
	// }

}
