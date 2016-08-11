package kamienica.feature.division;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.feature.apartment.Apartment;
import kamienica.feature.apartment.ApartmentDao;
import kamienica.feature.tenant.Tenant;
import kamienica.feature.tenant.TenantDao;

@Service
@Transactional
public class DivisionServiceImpl implements DivisionService {
	@Autowired
	DivisionDao divisionDAO;
	@Autowired
	TenantDao tenantDAO;
	@Autowired
	ApartmentDao apartmentDAO;

	@Override
	public List<Division> getList() {
		return divisionDAO.getList();
	}

	@Override
	public void deleteByID(Long id) {
		divisionDAO.deleteById(id);

	}

	@Override
	public void update(Division division) {
		divisionDAO.update(division);
	}

	@Override
	public void deleteAll() {
		divisionDAO.deleteAll();

	}

	@Override
	public void saveList(List<Division> division, LocalDate date) {
		divisionDAO.deleteAll();
		for (Division div : division) {
			div.setDate(date);
			divisionDAO.save(div);
		}

	}

	@Override
	public  List<Division> prepareDivisionListForRegistration(List<Tenant> tenantList,
			List<Apartment> apartmentList) {
		
		
		ArrayList<Division> divisionList = new ArrayList<>();
		for (Tenant ten : tenantList) {
			for (Apartment ap : apartmentList) {
				Division tmp = new Division();
				tmp.setApartment(ap);
				tmp.setTenant(ten);
				if (ap.getApartmentNumber() == 0) {
					tmp.setDivisionValue(Double.valueOf(decimalFormat(1 / (double) tenantList.size())));
				} else if (ap.getApartmentNumber() == ten.getApartment().getApartmentNumber()) {
					tmp.setDivisionValue(1);
				} else {
					tmp.setDivisionValue(0);
				}

				divisionList.add(tmp);
			}
		}
		return divisionList;
	}

	private static double decimalFormat(double input) {
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
		DecimalFormat df = (DecimalFormat) nf;
		df.applyPattern("#.00");
		return Double.parseDouble(df.format(input));
	}

}
