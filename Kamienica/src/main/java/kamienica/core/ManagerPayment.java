package kamienica.core;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import kamienica.model.Division;
import kamienica.model.InvoiceEnergy;
import kamienica.model.InvoiceGas;
import kamienica.model.InvoiceWater;
import kamienica.model.PaymentEnergy;
import kamienica.model.PaymentGas;
import kamienica.model.PaymentWater;
import kamienica.model.Tenant;
import kamienica.model.UsageValue;

public class ManagerPayment {

	public static ArrayList<PaymentEnergy> createPaymentEnergyList(ArrayList<Tenant> tenants, InvoiceEnergy invoice,
			ArrayList<Division> division, ArrayList<UsageValue> usage) {

		double sumOfExpences = invoice.getTotalAmount();
		ArrayList<PaymentEnergy> listToReturn = new ArrayList<PaymentEnergy>();

		double usageSum = sumUsage(usage);

		for (Tenant tenant : tenants) {
			HashMap<Integer, Double> divisionForTenant = set(division, tenant);
			double payment = 0;

			for (UsageValue w : usage) {
				double factor = w.getUsage() / usageSum;
				payment += sumOfExpences * factor * divisionForTenant.get(w.getApartment().getApartmentNumber());
			}

			payment = decimalFormat(payment);
			PaymentEnergy forList = new PaymentEnergy();
			forList.setInvoice(invoice);
			forList.setTenant(tenant);
			forList.setPaymentAmount(payment);
			forList.setPaymentDate(new Date());

			listToReturn.add(forList);
		}

		return listToReturn;
	}

	public static ArrayList<PaymentGas> createPaymentGasList(ArrayList<Tenant> tenants, InvoiceGas invoice,
			ArrayList<Division> division, ArrayList<UsageValue> usage) {
		double sumOfExpences = invoice.getTotalAmount();
		ArrayList<PaymentGas> listToReturn = new ArrayList<PaymentGas>();

		double usageSum = sumUsage(usage);

		for (Tenant tenant : tenants) {
			HashMap<Integer, Double> divisionForTenant = set(division, tenant);
			double payment = 0;

			for (UsageValue w : usage) {
				double factor = w.getUsage() / usageSum;
				payment += sumOfExpences * factor * divisionForTenant.get(w.getApartment().getApartmentNumber());
			}

			payment = decimalFormat(payment);
			PaymentGas forList = new PaymentGas();
			forList.setInvoice(invoice);
			forList.setTenant(tenant);
			forList.setPaymentAmount(payment);
			forList.setPaymentDate(new Date());

			listToReturn.add(forList);
		}

		return listToReturn;
	}

	public static ArrayList<PaymentWater> createPaymentWaterList(ArrayList<Tenant> tenants, InvoiceWater invoice,
			ArrayList<Division> podzial, ArrayList<UsageValue> usage) {
		ArrayList<PaymentWater> listToReturn = new ArrayList<PaymentWater>();

		double sumOfExpences = invoice.getTotalAmount();
		double usageSum = sumUsage(usage);

		for (Tenant tenant : tenants) {
			HashMap<Integer, Double> divForTenants = set(podzial, tenant);
			double payment = 0;
			for (UsageValue w : usage) {
				double factor = w.getUsage() / usageSum;

				payment += sumOfExpences * factor * divForTenants.get(w.getApartment().getApartmentNumber());
			}

			payment = decimalFormat(payment);
			PaymentWater forList = new PaymentWater();
			forList.setInvoice(invoice);
			forList.setTenant(tenant);
			forList.setPaymentAmount(payment);
			forList.setPaymentDate(new Date());
			listToReturn.add(forList);
		}
		return listToReturn;
	}

	static double sumUsage(ArrayList<UsageValue> listaZuzycia) {
		double suma = 0;
		for (UsageValue i : listaZuzycia) {
			suma += i.getUsage();
		}

		return suma;
	}

	private static HashMap<Integer, Double> set(ArrayList<Division> division, Tenant tenant) {
		HashMap<Integer, Double> output = new HashMap<>();
		for (Division p : division) {
			if (tenant.getId() == p.getTenant().getId()) {
				output.put(p.getApartment().getApartmentNumber(), p.getDivisionValue());
			}

		}
		return output;
	}

	private static double decimalFormat(double input) {
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
		DecimalFormat df = (DecimalFormat) nf;
		df.applyPattern("#.00");
		return Double.parseDouble(df.format(input));
	}

}