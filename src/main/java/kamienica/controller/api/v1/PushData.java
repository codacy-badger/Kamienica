package kamienica.controller.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;


public class PushData {

    public static void pushData() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("data.sql", "UTF-8");
        LocalDate start = LocalDate.now().minusDays(800l);
        String query = "INSERT INTO CONSUMPTION  (DATE, VALUE) VALUES ('%s', '%s');";
        for (long i = 0; i < 800; i++) {
            writer.println(String.format(query, start.plusDays(i), (int) ((Math.random() * 900) + 100) / 100.0));
        }

        writer.println("The first line");
        writer.println("The second line");
        writer.close();
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        pushData();

    }
}
