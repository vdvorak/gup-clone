package ua.com.gup.rent.service.abstracted;

import java.io.Serializable;

public abstract class RentRentGenericServiceImpl<T, PK extends Serializable>   implements ua.com.gup.rent.service.abstracted.generic.RentGenericService<T, PK> {

    private ua.com.gup.rent.repository.abstracted.generic.RentGenericRepository repository;


    public RentRentGenericServiceImpl(ua.com.gup.rent.repository.abstracted.generic.RentGenericRepository rentGenericRepository) {
        repository = rentGenericRepository;
    }


    protected ua.com.gup.rent.repository.abstracted.generic.RentGenericRepository getRepository() {
        return repository;
    }

}
