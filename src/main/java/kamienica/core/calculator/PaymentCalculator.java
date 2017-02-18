package kamienica.core.calculator;

import kamienica.model.entity.*;
import kamienica.model.exception.InvalidDivisionException;
import kamienica.core.util.CommonUtils;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PaymentCalculator {


    public static List<Payment> createPaymentList(final List<Tenant> tenants, final Invoice invoice,
                                                  final List<Division> division, final List<MediaUsage> usage) {
        double sumOfExpences = invoice.getTotalAmount();
        List<Payment> listToReturn = new ArrayList<>();

        double usageSum = sumUsage(usage);

        for (Tenant tenant : tenants) {
            HashMap<Integer, Double> divisionForTenant = setTenantDivision(division, tenant);
            double payment = 0;

            for (MediaUsage w : usage) {
                double factor = w.getUsage() / usageSum;
                payment += sumOfExpences * factor * divisionForTenant.get(w.getApartment().getApartmentNumber());
            }

            payment = CommonUtils.decimalFormat(payment);
            Payment forList = new Payment();
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