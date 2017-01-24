package kamienica.core.util;

import kamienica.model.SecurityUser;
import kamienica.model.Tenant;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.security.core.context.SecurityContextHolder;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class CommonUtils {

    public static double decimalFormat(double input) {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
        DecimalFormat df = (DecimalFormat) nf;
        df.applyPattern("#.00");
        return Double.parseDouble(df.format(input));
    }


    public static int countDaysBetween(final LocalDate first, final LocalDate second) {
        return Days.daysBetween(first, second).getDays();
    }

    public static Tenant getLoggedTenant() {
        SecurityUser su = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return su.getTenant();
    }
}
