package kamienica.core.util;

import kamienica.feature.user_admin.SecurityUser;
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

	public static SecurityUser fetchSecurityUser() {
		return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
