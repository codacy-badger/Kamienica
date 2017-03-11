package kamienica.feature.payment;

import kamienica.model.entity.Payment;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaymentService implements IPaymentService {

    private IPaymentDao paymentDao;

    @Autowired
    public PaymentService(IPaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public List<Payment> getPaymentList(Media media) {
        return paymentDao.getList(media);
    }


    @Override
    public List<Payment> getPaymentForTenant(Tenant tenant, Media media) {
        return paymentDao.getPaymentForTenant(tenant, media);
    }

}
