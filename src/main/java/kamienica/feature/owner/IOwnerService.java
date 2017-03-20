package kamienica.feature.owner;

import kamienica.model.entity.*;

import java.util.List;

public interface IOwnerService {

    List<Apartment> getEmptyApartments();

    List<ReadingDetails> getUnresolvedReadings();

    ReadingDetails getOldestReading();

    Payment getOldestPayment();
}
