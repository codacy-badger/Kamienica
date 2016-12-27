package kamienica.model;

import org.joda.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "invoicewater")
public class InvoiceWater extends Invoice implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8938418725115169355L;
    @OneToOne
    private ReadingWater baseReading;

    @Override
    public ReadingWater getBaseReading() {
        return baseReading;
    }

    public void setBaseReading(ReadingWater baseReading) throws Exception {
        if (baseReading.getMeter().getApartment() != null) {
            throw new Exception();

        }
        this.baseReading = baseReading;
    }

    public InvoiceWater() {
    }

    public InvoiceWater(String serialNumber, LocalDate date, double totalAmount,
                        ReadingWater reading) {
        super(serialNumber, date, totalAmount);
        this.baseReading = reading;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public LocalDate getReadingDate() {
        return baseReading.getReadingDate();
    }
}
