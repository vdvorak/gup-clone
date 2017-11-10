package ua.com.gup.rent.repository;

import java.util.List;

public interface GenericRepository<T, PK> {

    void create(T t);

    void update(T t);

    void delete(T t);

    List<T> findAll();

}
