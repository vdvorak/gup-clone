package ua.com.gup.rent.service.abstracted.generic;

public interface RentOfferGenericService<T, PK> {

    T findOne(PK pk);

}
