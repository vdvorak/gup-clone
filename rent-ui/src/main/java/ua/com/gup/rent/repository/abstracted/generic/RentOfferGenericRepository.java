package ua.com.gup.rent.repository.abstracted.generic;

import java.util.List;

public interface RentOfferGenericRepository<T, PK> {

    void create(T t);

    void update(T t);

    void delete(T t);

    T findOne(PK pk);

    List<T> findAll();

}
