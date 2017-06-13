package kamienica.feature.readingdetails;

import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReadingDetailsService implements IReadingDetailsService {

    private final IReadingDetailsDao readingDetailsDao;

    @Autowired
    public ReadingDetailsService(IReadingDetailsDao readingDetailsDao) {
        this.readingDetailsDao = readingDetailsDao;
    }


    @Override
    public List<ReadingDetails> getUnresolved(final Residence residence, final Media media) {
        return readingDetailsDao.getUnresolved(residence, media);
    }

    @Override
    public ReadingDetails getLatest(final Residence residence, final Media media) {
        return readingDetailsDao.getLatest(residence, media);
    }

    @Override
    public ReadingDetails getLatestPriorToDate(LocalDate date, Residence residence, Media media) {
        return readingDetailsDao.getLatestPriorToDate(date, residence, media);
    }

    @Override
    public List<ReadingDetails> list(final Media media) {
        return readingDetailsDao.getList(media);
    }

    @Override
    public List<ReadingDetails> getUnresolved(Media media) {
        return readingDetailsDao.getUnresolved(media);
    }
}
