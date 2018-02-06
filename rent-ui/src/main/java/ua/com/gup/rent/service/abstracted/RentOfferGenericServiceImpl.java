package ua.com.gup.rent.service.abstracted;

import ua.com.gup.rent.repository.abstracted.generic.RentOfferGenericRepository;
import ua.com.gup.rent.service.abstracted.generic.RentOfferGenericService;

import java.io.Serializable;

public abstract class RentOfferGenericServiceImpl<T, PK extends Serializable> implements RentOfferGenericService<T, PK> {

    private RentOfferGenericRepository repository;


    public RentOfferGenericServiceImpl(RentOfferGenericRepository rentOfferGenericRepository) {
        repository = rentOfferGenericRepository;
    }


    @Override
    public T findOne(PK pk) {
        return (T) getRepository().findOne(pk);
    }

    protected RentOfferGenericRepository getRepository() {
        return repository;
    }

}
