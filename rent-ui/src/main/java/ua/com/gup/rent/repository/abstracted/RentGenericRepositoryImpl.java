package ua.com.gup.rent.repository.abstracted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.Serializable;
import java.util.List;

public abstract class RentGenericRepositoryImpl<T, PK extends Serializable> implements ua.com.gup.rent.repository.abstracted.generic.RentGenericRepository<T, PK> {

    private final Class<T> tClass;

    @Autowired
    protected MongoTemplate mongoTemplate;

    public RentGenericRepositoryImpl(Class<T> tClass) {
        this.tClass = tClass;
    }


    @Override
    public void create(T t) {
        mongoTemplate.insert(t);
    }

    @Override
    public void update(T t) {
        mongoTemplate.save(t);
    }


    @Override
    public void delete(T t) {
        mongoTemplate.remove(t);

    }

    @Override
    public List<T> findAll() {
        return mongoTemplate.findAll(tClass);
    }

    public void saveOrUpdate(PK pk) {
        mongoTemplate.findById(pk, tClass);
    }
}
