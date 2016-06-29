package kamienica.feature.division;

import java.util.List;

import org.joda.time.LocalDate;

public class DivisionForm {

	private List<Division> divisionList;
	private LocalDate date;

	public DivisionForm() {
		this.date = new LocalDate();
	}

	public List<Division> getDivisionList() {
		return divisionList;
	}

	public void setDivisionList(List<Division> divisionList) {
		this.divisionList = divisionList;
	}

	@Override
	public String toString() {
		return "PodzialFormularz [podzialLista=" + divisionList + "]";
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
