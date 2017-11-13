package ua.com.gup.rent.service.impl;

import ua.com.gup.rent.repository.GenericRepository;
import ua.com.gup.rent.service.GenericService;

import java.io.Serializable;

public abstract class GenericServiceImpl<T, PK extends Serializable>
        implements GenericService<T, PK> {

    private GenericRepository repository;


    public GenericServiceImpl(GenericRepository genericRepository) {
        repository = genericRepository;
    }


    protected GenericRepository getRepository() {
        return repository;
    }

}
