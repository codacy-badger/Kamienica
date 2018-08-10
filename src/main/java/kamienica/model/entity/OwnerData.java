package kamienica.model.entity;

public class OwnerData {

    private Invoice oldestInvoice;
    private ReadingDetails oldestReading;
    private int emptyApartments;
    private int numOfResidences;

    public OwnerData(Invoice oldestInvoice, ReadingDetails oldestReading, int emptyApartments, int numOfResidences) {
        this.oldestInvoice = oldestInvoice;
        this.oldestReading = oldestReading;
        this.emptyApartments = emptyApartments;
        this.numOfResidences = numOfResidences;
    }

    public OwnerData() {
    }

    public Invoice getOldestInvoice() {
        return oldestInvoice;
    }

    public void setOldestInvoice(Invoice oldestInvoice) {
        this.oldestInvoice = oldestInvoice;
    }

    public ReadingDetails getOldestReading() {
        return oldestReading;
    }

    public void setOldestReading(ReadingDetails oldestReading) {
        this.oldestReading = oldestReading;
    }

    public int getEmptyApartments() {
        return emptyApartments;
    }

    public void setEmptyApartments(int emptyApartments) {
        this.emptyApartments = emptyApartments;
    }

    public int getNumOfResidences() {
        return numOfResidences;
    }

    public void setNumOfResidences(int numOfResidences) {
        this.numOfResidences = numOfResidences;
    }

    @Override
    public String toString() {
        return "OwnerData{" +
                "oldestInvoice=" + oldestInvoice +
                ", oldestReading=" + oldestReading +
                ", emptyApartments=" + emptyApartments +
                ", numOfResidences=" + numOfResidences +
                '}';
    }
}
