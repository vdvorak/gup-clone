package ua.com.gup.rent.repository.rent;

public interface RentObjectRepository extends ua.com.gup.rent.repository.abstracted.generic.RentGenericRepository<ua.com.gup.rent.model.mongo.RentObject, String> {
    void deleteById(String rentObjectId);

    boolean isOwner(String rentObjectId, String userId);
}
