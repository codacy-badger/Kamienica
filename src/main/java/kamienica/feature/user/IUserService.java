package kamienica.feature.user;

import kamienica.model.entity.Payment;
import kamienica.model.entity.Reading;
import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.Tenant;

import java.util.List;
import java.util.Map;

public interface IUserService {

   List<Reading> getReadings();

    List<Payment> getPayments();

    Tenant getTenantInfo();

    Map<String, ReadingDetails> getLatestReadingDates();

}
