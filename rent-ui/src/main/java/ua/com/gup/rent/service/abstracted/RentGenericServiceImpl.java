package ua.com.gup.rent.service.abstracted;

import ua.com.gup.rent.repository.abstracted.generic.RentOfferGenericRepository;
import ua.com.gup.rent.service.abstracted.generic.RentGenericService;

import java.io.Serializable;

public abstract class RentGenericServiceImpl<T, PK extends Serializable>   implements RentGenericService<T, PK> {

    private RentOfferGenericRepository repository;


    public RentGenericServiceImpl(RentOfferGenericRepository rentOfferGenericRepository) {
        repository = rentOfferGenericRepository;
    }


    protected RentOfferGenericRepository getRepository() {
        return repository;
    }

}
