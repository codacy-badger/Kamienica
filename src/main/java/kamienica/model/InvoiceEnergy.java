package kamienica.model;

import kamienica.feature.reading.ReadingEnergy;
import org.joda.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "invoiceenergy")
public class InvoiceEnergy extends Invoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToOne
    private ReadingEnergy baseReading;

    @Override
    public ReadingEnergy getBaseReading() {
        return baseReading;
    }

    public void setBaseReading(ReadingEnergy baseReading) throws Exception {
        if (baseReading.getMeter().getApartment() != null) {
            throw new Exception();

        }
        this.baseReading = baseReading;
    }

    public InvoiceEnergy() {
    }

    public InvoiceEnergy(String serialNumber, LocalDate date, double totalAmount,
                         ReadingEnergy reading) {
        super(serialNumber, date, totalAmount);
        this.baseReading = reading;
    }

    @Override
    public LocalDate getReadingDate() {
        return baseReading.getReadingDate();
    }

}
