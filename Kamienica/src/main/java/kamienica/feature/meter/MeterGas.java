package kamienica.feature.meter;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import kamienica.model.Apartment;

@Entity
@Table(name = "metergas")
public class MeterGas extends Meter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private boolean cwu;

    @Autowired
    public MeterGas(String description, String serialNumber, String unit, Apartment apartment, boolean isCWU) {
        super(description, serialNumber, unit, apartment);
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
