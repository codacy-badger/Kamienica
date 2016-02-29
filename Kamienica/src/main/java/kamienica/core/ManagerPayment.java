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

	public static ArrayList<PaymentEnergy> createPaymentEnergyList(ArrayList<Tenant> tenants,
			InvoiceEnergy invoice, ArrayList<Division> division, ArrayList<UsageValue> usage) {

		double sumOfExpences = invoice.getTotalAmount();
		ArrayList<PaymentEnergy> listToReturn = new ArrayList<PaymentEnergy>();

		// NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
		// DecimalFormat df = (DecimalFormat) nf;
		// df.applyPattern("#.00");
		double sumaZuzycia = sumUsage(usage);

		for (Tenant tenant : tenants) {
			HashMap<Integer, Double> podzialDlaNajemcy = tworzPodzialDlaNajemcy(division, tenant);
			double oplata = 0;

			for (UsageValue w : usage) {
				double ulamek = w.getUsage() / sumaZuzycia;
				oplata += sumOfExpences * ulamek * podzialDlaNajemcy.get(w.getMieszkanie().getApartmentNumber());
			}

			// oplata = Double.parseDouble(df.format(oplata));
			oplata = decimalFormat(oplata);
			PaymentEnergy forList = new PaymentEnergy();
			forList.setInvoice(invoice);
			forList.setTenant(tenant);
			forList.setPaymentAmount(oplata);
			forList.setPaymentDate(new Date());

			listToReturn.add(forList);
		}

		return listToReturn;
	}

	public static ArrayList<PaymentGas> createPaymentGasList(ArrayList<Tenant> tenants, InvoiceGas invoice,
			ArrayList<Division> division, ArrayList<UsageValue> usage) {
		double sumOfExpences = invoice.getTotalAmount();
		ArrayList<PaymentGas> listToReturn = new ArrayList<PaymentGas>();
		// NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
		// DecimalFormat df = (DecimalFormat) nf;
		// df.applyPattern("#.00");

		double sumaZuzycia = sumUsage(usage);

		for (Tenant tenant : tenants) {
			HashMap<Integer, Double> podzialDlaNajemcy = tworzPodzialDlaNajemcy(division, tenant);
			double oplata = 0;

			for (UsageValue w : usage) {
				double ulamek = w.getUsage() / sumaZuzycia;
				oplata += sumOfExpences * ulamek * podzialDlaNajemcy.get(w.getMieszkanie().getApartmentNumber());
			}

			// oplata = Double.parseDouble(df.format(oplata));
			oplata = decimalFormat(oplata);
			PaymentGas forList = new PaymentGas();
			forList.setInvoice(invoice);
			forList.setTenant(tenant);
			forList.setPaymentAmount(oplata);
			forList.setPaymentDate(new Date());

			listToReturn.add(forList);
		}

		return listToReturn;
	}

	public static ArrayList<PaymentWater> createPaymentWaterList(ArrayList<Tenant> tenants, InvoiceWater invoice,
			ArrayList<Division> podzial, ArrayList<UsageValue> usage) {
		ArrayList<PaymentWater> listToReturn = new ArrayList<PaymentWater>();
		// NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
		// DecimalFormat df = (DecimalFormat) nf;
		// df.applyPattern("#.00");
		double sumOfExpences = invoice.getTotalAmount();

		double sumaZuzycia = sumUsage(usage);

		for (Tenant tenant : tenants) {
			HashMap<Integer, Double> podzialDlaNajemcy = tworzPodzialDlaNajemcy(podzial, tenant);
			double oplata = 0;
			for (UsageValue w : usage) {
				double ulamek = w.getUsage() / sumaZuzycia;

				oplata += sumOfExpences * ulamek * podzialDlaNajemcy.get(w.getMieszkanie().getApartmentNumber());
			}

			// oplata = Double.parseDouble(df.format(oplata));
			oplata = decimalFormat(oplata);
			PaymentWater forList = new PaymentWater();
			forList.setInvoice(invoice);
			forList.setTenant(tenant);
			forList.setPaymentAmount(oplata);
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

	private static HashMap<Integer, Double> tworzPodzialDlaNajemcy(ArrayList<Division> podzial, Tenant tenant) {
		HashMap<Integer, Double> output = new HashMap<>();
		for (Division p : podzial) {
			if (tenant.getId() == p.getTenant().getId()) {
				output.put(p.getApartment().getApartmentNumber(), p.getDivisionValue());
			}

		}
		return output;
	}

	// private static double sumEnergy(List<InvoiceEnergy> invoice) {
	// double sum = 0;
	// for (Invoice inv : invoice) {
	// sum = sum + inv.getTotalAmount();
	// }
	// return sum;
	// }
	//
	// private static double sumWater(List<InvoiceWater> invoice) {
	// double sum = 0;
	// for (Invoice inv : invoice) {
	// sum = sum + inv.getTotalAmount();
	// }
	// return sum;
	// }
	//
	// private static double sumGas(List<InvoiceGas> invoice) {
	// double sum = 0;
	// for (Invoice inv : invoice) {
	// sum = sum + inv.getTotalAmount();
	// }
	// return sum;
	// }

//	private static double sumInvoicesAmount(List<? extends Invoice> invoice) {
//		double sum = 0;
//		for (Invoice inv : invoice) {
//			sum = sum + inv.getTotalAmount();
//		}
//		return sum;
//	}

	private static double decimalFormat(double input) {
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
		DecimalFormat df = (DecimalFormat) nf;
		df.applyPattern("#.00");
		return Double.parseDouble(df.format(input));
	}
	// =======================METHODS_USED_IN_CONTROLLER==============================
	// public static void prepareModelForNewWaterPayment(HashMap<String, Object>
	// model, ArrayList<Date> readingDatesWater,
	// List<InvoiceWater> invoiceWater) {
	// ReadingInvoiceForm waterFirstBinder = new ReadingInvoiceForm();
	// waterFirstBinder.setInvoice(invoiceWater.get(0));
	// invoiceWater.remove(0);
	// waterFirstBinder.setDate(readingDatesWater.get(0));
	// readingDatesWater.remove(0);
	// model.put("waterFirstBinder", waterFirstBinder);
	// ArrayList<ReadingInvoiceForm> waterBinder = new ArrayList<>();
	// while (!invoiceWater.isEmpty() && !readingDatesWater.isEmpty()) {
	// ReadingInvoiceForm out = new ReadingInvoiceForm();
	// out.setDate(readingDatesWater.get(0));
	// out.setInvoice(invoiceWater.get(0));
	// waterBinder.add(out);
	// readingDatesWater.remove(0);
	// invoiceWater.remove(0);
	//
	// }
	// model.put("waterBinder", waterBinder);
	// model.put("readingDatesWater", readingDatesWater);
	// model.put("invoiceWater", invoiceWater);
	// }

	// public static void prepareModelForNewGasPayment(HashMap<String, Object>
	// model, ArrayList<Date> readingDatesGas,
	// List<InvoiceGas> invoiceGas) {
	// ReadingInvoiceForm gasFirstBinder = new ReadingInvoiceForm();
	// gasFirstBinder.setInvoice(invoiceGas.get(0));
	// invoiceGas.remove(0);
	// gasFirstBinder.setDate(readingDatesGas.get(0));
	// readingDatesGas.remove(0);
	// model.put("gasFirstBinder", gasFirstBinder);
	// ArrayList<ReadingInvoiceForm> gasBinder = new ArrayList<>();
	// while (!invoiceGas.isEmpty() && !readingDatesGas.isEmpty()) {
	// ReadingInvoiceForm out = new ReadingInvoiceForm();
	// out.setDate(readingDatesGas.get(0));
	// out.setInvoice(invoiceGas.get(0));
	// gasBinder.add(out);
	// readingDatesGas.remove(0);
	// invoiceGas.remove(0);
	//
	// }
	// model.put("gasBinder", gasBinder);
	// model.put("readingDatesGas", readingDatesGas);
	// model.put("invoiceGas", invoiceGas);
	// }

	// public static void prepareModelForNewEnergyPayment(HashMap<String,
	// Object> model,
	// ArrayList<Date> readingDatesEnergy, List<InvoiceEnergy> invoiceEnergy) {
	// ReadingInvoiceForm energyFirstBinder = new ReadingInvoiceForm();
	// energyFirstBinder.setInvoice(invoiceEnergy.get(0));
	// invoiceEnergy.remove(0);
	// energyFirstBinder.setDate(readingDatesEnergy.get(0));
	// readingDatesEnergy.remove(0);
	// model.put("energyFirstBinder", energyFirstBinder);
	// ArrayList<ReadingInvoiceForm> energyBinder = new ArrayList<>();
	// while (!invoiceEnergy.isEmpty() && !readingDatesEnergy.isEmpty()) {
	// ReadingInvoiceForm out = new ReadingInvoiceForm();
	// out.setDate(readingDatesEnergy.get(0));
	// out.setInvoice(invoiceEnergy.get(0));
	// energyBinder.add(out);
	// readingDatesEnergy.remove(0);
	// invoiceEnergy.remove(0);
	//
	// }
	// model.put("energyBinder", energyBinder);
	// model.put("readingDatesEnergy", readingDatesEnergy);
	// model.put("invoiceEnergy", invoiceEnergy);
	// }
}