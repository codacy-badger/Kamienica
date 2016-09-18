package kamienica.core.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.joda.time.LocalDate;

import kamienica.feature.division.Division;
import kamienica.feature.invoice.InvoiceEnergy;
import kamienica.feature.invoice.InvoiceGas;
import kamienica.feature.invoice.InvoiceWater;
import kamienica.feature.payment.PaymentEnergy;
import kamienica.feature.payment.PaymentGas;
import kamienica.feature.payment.PaymentWater;
import kamienica.feature.tenant.Tenant;
import kamienica.feature.usagevalue.UsageValue;

public class PaymentCalculator {

	public static List<PaymentEnergy> createPaymentEnergyList(List<Tenant> tenants, InvoiceEnergy invoice,
			List<Division> division, List<UsageValue> usage) {
		double sumOfExpences = invoice.getTotalAmount();
		ArrayList<PaymentEnergy> listToReturn = new ArrayList<PaymentEnergy>();

		double usageSum = sumUsage(usage);

		for (Tenant tenant : tenants) {
			HashMap<Integer, Double> divisionForTenant = setTenantDivision(division, tenant);
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
			forList.setPaymentDate(new LocalDate());
			listToReturn.add(forList);
		}

		return listToReturn;
	}

	public static List<PaymentGas> createPaymentGasList(List<Tenant> tenants, InvoiceGas invoice,
			List<Division> division, List<UsageValue> usage) {
		double sumOfExpences = invoice.getTotalAmount();
		ArrayList<PaymentGas> listToReturn = new ArrayList<PaymentGas>();

		double usageSum = sumUsage(usage);

		for (Tenant tenant : tenants) {
			HashMap<Integer, Double> divisionForTenant = setTenantDivision(division, tenant);
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
			forList.setPaymentDate(new LocalDate());

			listToReturn.add(forList);
		}

		return listToReturn;
	}

	public static List<PaymentWater> createPaymentWaterList(List<Tenant> tenants, InvoiceWater invoice,
			List<Division> podzial, List<UsageValue> usage) {
		ArrayList<PaymentWater> listToReturn = new ArrayList<PaymentWater>();

		double sumOfExpences = invoice.getTotalAmount();
		double usageSum = sumUsage(usage);

		for (Tenant tenant : tenants) {
			HashMap<Integer, Double> divForTenants = setTenantDivision(podzial, tenant);
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
			forList.setPaymentDate(new LocalDate());
			listToReturn.add(forList);
		}
		return listToReturn;
	}

	private static double sumUsage(List<UsageValue> listaZuzycia) {
		double suma = 0;
		for (UsageValue i : listaZuzycia) {
			suma += i.getUsage();
		}

		return suma;
	}

	private static HashMap<Integer, Double> setTenantDivision(List<Division> division, Tenant tenant) {
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