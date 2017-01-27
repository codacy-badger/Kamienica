package kamienica.core.daoservice;

import org.hibernate.criterion.Criterion;

import java.util.List;

public interface BasicService<T> {

    List<T> list();

    List<T> listForOwner();

    List<T> listForTenant();

    T getById(Long id);

    void deleteById(Long id);

    void update(T t);

    void save(T t);
}
