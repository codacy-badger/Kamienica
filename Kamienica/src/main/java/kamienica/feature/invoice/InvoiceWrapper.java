package kamienica.feature.invoice;

public class InvoiceWrapper {

	private InvoiceGas gas;
	private InvoiceEnergy energy;
	private InvoiceWater water;

	public InvoiceGas getGas() {
		return gas;
	}

	public void setGas(InvoiceGas gas) {
		this.gas = gas;
	}

	public InvoiceEnergy getEnergy() {
		return energy;
	}

	public void setEnergy(InvoiceEnergy energy) {
		this.energy = energy;
	}

	public InvoiceWater getWater() {
		return water;
	}

	public void setWater(InvoiceWater water) {
		this.water = water;
	}

	public InvoiceWrapper() {
	}

	@Override
	public String toString() {
		return "InvoiceWrapper [gas=" + gas + ", energy=" + energy + ", water=" + water + "]";
	}
}
