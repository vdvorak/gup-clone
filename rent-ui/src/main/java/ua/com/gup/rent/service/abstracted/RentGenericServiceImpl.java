package ua.com.gup.rent.service.abstracted;

import ua.com.gup.rent.repository.abstracted.generic.RentGenericRepository;
import ua.com.gup.rent.service.abstracted.generic.RentGenericService;

import java.io.Serializable;

public abstract class RentGenericServiceImpl<T, PK extends Serializable>   implements RentGenericService<T, PK> {

    private RentGenericRepository repository;


    public RentGenericServiceImpl(RentGenericRepository rentGenericRepository) {
        repository = rentGenericRepository;
    }


    protected RentGenericRepository getRepository() {
        return repository;
    }

}
