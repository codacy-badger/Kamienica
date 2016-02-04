package kamienica.wrapper;

import java.util.ArrayList;
import java.util.Date;

import kamienica.model.Division;

public class DivisionForm {

	private ArrayList<Division> divisionList = new ArrayList<>();
	private Date date;

	public DivisionForm(){
		this.date = new Date();
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
