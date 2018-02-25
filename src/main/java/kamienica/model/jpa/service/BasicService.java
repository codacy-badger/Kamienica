package kamienica.model.jpa.service;

import java.util.List;

public interface BasicService<T> {

    List<T> list();

    List<T> listForOwner();

    T getById(Long id);

    void delete(Long id);

    void delete(T object);

    void update(T t);

    void save(T t);
}
