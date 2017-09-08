package kamienica.feature.readingdetails;

import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;

import java.util.List;

public interface IReadingDetailsService {

    List<ReadingDetails> getUnresolved(Residence residence, Media media);

    ReadingDetails getLatest(Residence residence, Media media);

    List<ReadingDetails> list(Media media);

    List<ReadingDetails> getUnresolved(Media media);
}
