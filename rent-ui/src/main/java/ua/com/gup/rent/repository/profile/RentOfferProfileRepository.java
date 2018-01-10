package ua.com.gup.rent.repository.profile;

import org.springframework.data.domain.Pageable;
import ua.com.gup.common.model.enumeration.CommonUserRole;
import ua.com.gup.common.repository.CommonProfileRepository;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.model.mongo.user.RentOfferUserProfile;
import ua.com.gup.rent.repository.abstracted.generic.RentOfferGenericRepository;

import java.util.List;
import java.util.Set;


public interface RentOfferProfileRepository extends RentOfferGenericRepository<RentOfferProfile, String> , CommonProfileRepository {


    /**
     * Create profile.
     *
     * @param profile the profile
     */
    void updateProfile(RentOfferProfile profile);

    /**
     * Find and return profile by it's ID.
     *
     * @param id - the Profile ID
     * @return - profile
     */
    RentOfferProfile findById(String id);

    <T extends RentOfferProfile> T findById(String id, Class<T> entityClass);

    RentOfferProfile findByPublicId(String id);

    <T extends RentOfferProfile> T findByPublicId(String id, Class<T> entityClass);


    void save(RentOfferProfile profile);


    boolean hasManager(String profilePublicId);

    List<RentOfferProfile> findLikeUsername(String username);

    List<RentOfferUserProfile> findUsersByManager(String managerId);

    RentOfferUserProfile getManagerUser(String managerPublicId, String publicId);

    Set<String> getManagerUserIds(String managerId);

    String getPulblicIdById( String id);

    String getIdByPulblicId( String publicId);

    Set<String> getPulblicIdsByIds( Set<String> usersPublicId);

    List<RentOfferProfile> findByRole(CommonUserRole role, Pageable pageable);

    long countByRole(CommonUserRole role);

    //List<RentOfferProfile> findByFilter(ProfileRepositoryFilter filter, Pageable pageable);

    long countByFilter(ProfileRepositoryFilter filter);

    <T extends RentOfferProfile> List<T>  findByFilter(ProfileRepositoryFilter filter, Pageable pageable, Class<T> entityClass);

    boolean hasRole(String profilePublicId, CommonUserRole roleUser);

    boolean profileExistsByPublicId(String id);
}
