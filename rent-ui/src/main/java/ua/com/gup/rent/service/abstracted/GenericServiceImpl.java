package ua.com.gup.rent.service.abstracted;

import ua.com.gup.rent.repository.abstracted.generic.GenericRepository;
import ua.com.gup.rent.service.abstracted.generic.GenericService;

import java.io.Serializable;

public abstract class GenericServiceImpl<T, PK extends Serializable>   implements GenericService<T, PK> {

    private GenericRepository repository;


    public GenericServiceImpl(GenericRepository genericRepository) {
        repository = genericRepository;
    }


    protected GenericRepository getRepository() {
        return repository;
    }

}
