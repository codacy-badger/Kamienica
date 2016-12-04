package kamienica.core.calculator;

import kamienica.core.util.CommonUtils;
import kamienica.model.MediaUsage;
import kamienica.model.Division;
import kamienica.model.InvoiceEnergy;
import kamienica.model.InvoiceGas;
import kamienica.model.InvoiceWater;
import kamienica.feature.payment.PaymentEnergy;
import kamienica.feature.payment.PaymentGas;
import kamienica.feature.payment.PaymentWater;
import kamienica.model.Tenant;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PaymentCalculator {


    public static List<PaymentEnergy> createPaymentEnergyList(List<Tenant> tenants, InvoiceEnergy invoice,
                                                              List<Division> division, List<MediaUsage> usage) {
        double sumOfExpences = invoice.getTotalAmount();
        List<PaymentEnergy> listToReturn = new ArrayList<>();

        double usageSum = sumUsage(usage);

        for (Tenant tenant : tenants) {
            HashMap<Integer, Double> divisionForTenant = setTenantDivision(division, tenant);
            double payment = 0;

            for (MediaUsage w : usage) {
                double factor = w.getUsage() / usageSum;
                payment += sumOfExpences * factor * divisionForTenant.get(w.getApartment().getApartmentNumber());
            }

            payment = CommonUtils.decimalFormat(payment);
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
                                                        List<Division> division, List<MediaUsage> usage) {
        double sumOfExpences = invoice.getTotalAmount();
        List<PaymentGas> listToReturn = new ArrayList<>();

        double usageSum = sumUsage(usage);

        for (Tenant tenant : tenants) {
            HashMap<Integer, Double> divisionForTenant = setTenantDivision(division, tenant);
            double payment = 0;

            for (MediaUsage w : usage) {
                double factor = w.getUsage() / usageSum;
                payment += sumOfExpences * factor * divisionForTenant.get(w.getApartment().getApartmentNumber());
            }

            payment = CommonUtils.decimalFormat(payment);
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
                                                            List<Division> podzial, List<MediaUsage> usage) {
        List<PaymentWater> listToReturn = new ArrayList<>();

        double sumOfExpences = invoice.getTotalAmount();
        double usageSum = sumUsage(usage);

        for (Tenant tenant : tenants) {
            HashMap<Integer, Double> divForTenants = setTenantDivision(podzial, tenant);
            double payment = 0;
            for (MediaUsage w : usage) {
                double factor = w.getUsage() / usageSum;

                payment += sumOfExpences * factor * divForTenants.get(w.getApartment().getApartmentNumber());
            }

            payment = CommonUtils.decimalFormat(payment);
            PaymentWater forList = new PaymentWater();
            forList.setInvoice(invoice);
            forList.setTenant(tenant);
            forList.setPaymentAmount(payment);
            forList.setPaymentDate(new LocalDate());
            listToReturn.add(forList);
        }
        return listToReturn;
    }

    private static double sumUsage(List<MediaUsage> listaZuzycia) {
        double suma = 0;
        for (MediaUsage i : listaZuzycia) {
            suma += i.getUsage();
        }

        return suma;
    }

    private static HashMap<Integer, Double> setTenantDivision(List<Division> division, Tenant tenant) {
        HashMap<Integer, Double> output = new HashMap<>();
        for (Division p : division) {
            if (tenant.getId().equals(p.getTenant().getId())) {
                output.put(p.getApartment().getApartmentNumber(), p.getDivisionValue());
            }

        }
        return output;
    }

}