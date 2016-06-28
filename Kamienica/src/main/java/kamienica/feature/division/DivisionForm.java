package kamienica.feature.division;

import java.util.ArrayList;

import org.joda.time.LocalDate;

public class DivisionForm {

	private ArrayList<Division> divisionList = new ArrayList<>();
	private  LocalDate date;

	public DivisionForm(){
		this.date = new LocalDate();
	}

	public ArrayList<Division> getDivisionList() {
		return divisionList;
	}

	public void setDivisionList(ArrayList<Division> divisionList) {
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
