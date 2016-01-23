package kamienica.validator;

import org.springframework.stereotype.Component;

import kamienica.model.Apartment;

@Component
public class ApartmentValidator {


	public static boolean checkIntercom(Apartment apartment) {
		int number;
		try {
		 number =	Integer.valueOf(apartment.getApartmentNumber());
		} catch (Exception e) {
			return false;
		}
		if(number < 0) {
			return false;
		}
		if (number > 9999) {
			return false;
		}
		return true;
	}
	
}
