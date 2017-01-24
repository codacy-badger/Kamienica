package kamienica.core.calculator;

import kamienica.core.exception.InvalidDivisionException;
import kamienica.core.util.CommonUtils;
import kamienica.model.*;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PaymentCalculator {


    public static List<PaymentEnergy> createPaymentEnergyList(final List<Tenant> tenants, final InvoiceEnergy invoice,
                                                              final List<Division> division, final List<MediaUsage> usage) throws InvalidDivisionException {
        validateDivision(division);
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

    private static void validateDivision(List<Division> division) throws InvalidDivisionException {
        if (division == null || division.size() == 0) {
            throw new InvalidDivisionException();
        }
    }

    public static List<PaymentGas> createPaymentGasList(final List<Tenant> tenants, final InvoiceGas invoice,
                                                        final List<Division> division, final List<MediaUsage> usage) {
        double sumOfExpences = invoice.getTotalAmount();
        List<PaymentGas> listToReturn = new ArrayList<>();

        final double usageSum = sumUsage(usage);

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

    public static List<PaymentWater> createPaymentWaterList(final List<Tenant> tenants, final InvoiceWater invoice,
                                                            final List<Division> podzial, final List<MediaUsage> usage) {
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
        division.stream().filter(p -> tenant.getId().equals(p.getTenant().getId())).forEachOrdered(p -> {
            output.put(p.getApartment().getApartmentNumber(), p.getDivisionValue());
        });
        return output;
    }

}