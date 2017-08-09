package kamienica.core.calculator;

import kamienica.core.util.CommonUtils;
import kamienica.model.entity.Division;
import kamienica.model.entity.Invoice;
import kamienica.model.entity.MediaUsage;
import kamienica.model.entity.Payment;
import kamienica.model.entity.Tenant;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentCalculator {


    public static List<Payment> createPaymentList( final Invoice invoice,
                                                  final List<Division> division, final List<MediaUsage> usage) {
        double sumOfExpences = invoice.getTotalAmount();
        List<Payment> listToReturn = new ArrayList<>();

        double usageSum = sumUsage(usage);


        final List<Tenant> tenants = extractTenantsFromDivision(division);
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


    private static List<Tenant> extractTenantsFromDivision(List<Division> division) {
        return division.stream().map(Division::getTenant).distinct().collect(Collectors.toList());
    }
}