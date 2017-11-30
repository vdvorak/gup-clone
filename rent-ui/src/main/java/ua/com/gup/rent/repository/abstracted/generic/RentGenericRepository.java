package ua.com.gup.rent.repository.abstracted.generic;

import java.util.List;

public interface RentGenericRepository<T, PK> {

    void create(T t);

    void update(T t);

    void delete(T t);

    List<T> findAll();

}
