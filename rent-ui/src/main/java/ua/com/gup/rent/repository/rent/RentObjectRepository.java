package ua.com.gup.rent.repository.rent;

import ua.com.gup.rent.model.mongo.rent.Rent;

public interface RentObjectRepository extends ua.com.gup.rent.repository.abstracted.generic.RentGenericRepository<Rent, String> {
    void deleteById(String rentObjectId);

    boolean isOwner(String rentObjectId, String userId);
}
