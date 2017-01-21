package kamienica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "metergas")
public class MeterGas extends Meter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private boolean cwu;

    public MeterGas(String description, String serialNumber, String unit, Apartment apartment, boolean isCWU) {
        super(description, serialNumber, unit, apartment);
        this.cwu = isCWU;
    }

    public MeterGas(String description, String serialNumber, String unit, Apartment apartment, Residence res, boolean isCWU) {
        super(description, serialNumber, unit, apartment, res);
        this.cwu = isCWU;
    }

    public MeterGas() {
        super.setUnit("m3");
        this.cwu = false;
    }

    @Override
    public String toString() {
        String apartment;
        try {
            apartment = this.apartment.getDescription();
        } catch (NullPointerException e) {
            apartment = "brak";
        }
        return "\n" + description + ", nrSeryjnyLicznika:" + serialNumber
                + ", Mieszkanie:" + apartment + ", CWU: " + cwu;
    }

    public boolean isCwu() {
        return cwu;
    }

    public void setCwu(boolean cwu) {
        this.cwu = cwu;
    }


}
