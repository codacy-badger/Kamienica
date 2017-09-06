package kamienica.core.util;

import org.joda.time.Days;
import org.joda.time.LocalDate;

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
}
