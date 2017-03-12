package kamienica.feature.user;

import kamienica.model.entity.Payment;
import kamienica.model.entity.Reading;
import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.Tenant;

import java.util.List;
import java.util.Map;

public interface IUserService {

    Map<String, List<Reading>> getMapOfReadings();

    Map<String, List<Payment>> getMapOfPayments();

    Tenant getTenantInfo();

    Map<String, ReadingDetails> getLatestReadingDates();

}
