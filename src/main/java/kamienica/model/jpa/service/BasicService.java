package kamienica.model.jpa.service;

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
