package ua.com.gup.rent.repository.rent;

import ua.com.gup.rent.model.mongo.RentObject;
import ua.com.gup.rent.repository.abstracted.generic.GenericRepository;

public interface RentObjectRepository extends GenericRepository<RentObject, String> {
    void deleteById(String rentObjectId);

    boolean isOwner(String rentObjectId, String userId);
}
