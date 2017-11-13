package ua.com.gup.rent.repository;

import ua.com.gup.rent.model.mongo.RentObject;

public interface RentObjectRepository extends GenericRepository<RentObject, String> {
    void deleteById(String rentObjectId);

    boolean isOwner(String rentObjectId, String userId);
}
