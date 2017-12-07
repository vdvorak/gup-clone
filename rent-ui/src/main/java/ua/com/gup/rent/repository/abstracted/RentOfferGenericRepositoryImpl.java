package ua.com.gup.rent.repository.abstracted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import ua.com.gup.rent.repository.abstracted.generic.RentOfferGenericRepository;

import java.io.Serializable;
import java.util.List;

public abstract class RentOfferGenericRepositoryImpl<T, PK extends Serializable> implements RentOfferGenericRepository<T, PK> {

    private final Class<T> tClass;

    @Autowired
    protected MongoTemplate mongoTemplate;

    public RentOfferGenericRepositoryImpl(Class<T> tClass) {
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
