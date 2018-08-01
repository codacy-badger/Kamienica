package kamienica.core.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class JodaDateSerializer extends JsonSerializer<LocalDate> {

    private static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormat.forPattern(DATE_FORMAT);

    @Override
    public void serialize(final LocalDate value, final JsonGenerator gen, final SerializerProvider arg2) throws IOException {
        gen.writeString(FORMATTER.print(value));
    }


}
