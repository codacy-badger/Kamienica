package kamienica.model;

import kamienica.feature.reading.ReadingGas;
import org.joda.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "invoicegas")
public class InvoiceGas extends Invoice implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1048539735580240509L;
    @OneToOne
    private ReadingGas baseReading;

    @Override
    public ReadingGas getBaseReading() {
        return baseReading;
    }

    public void setBaseReading(ReadingGas baseReading) throws Exception {
        if (baseReading.getMeter().getApartment() != null) {
            throw new Exception();

        }
        this.baseReading = baseReading;
    }

    public InvoiceGas() {

    }

    public InvoiceGas(String serialNumber, LocalDate date, double totalAmount, ReadingGas reading) {
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
