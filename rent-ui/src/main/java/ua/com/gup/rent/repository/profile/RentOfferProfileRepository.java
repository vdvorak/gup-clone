package ua.com.gup.rent.repository.profile;

import org.springframework.data.domain.Pageable;
import ua.com.gup.common.repository.CommonProfileRepository;
import ua.com.gup.rent.model.mongo.user.Profile;
import ua.com.gup.rent.model.mongo.user.UserProfile;
import ua.com.gup.rent.repository.abstracted.generic.RentOfferGenericRepository;

import java.util.List;
import java.util.Set;


public interface RentOfferProfileRepository extends RentOfferGenericRepository<Profile, String> , CommonProfileRepository {


    /**
     * Create profile.
     *
     * @param profile the profile
     */
    void updateProfile(Profile profile);

    /**
     * Find and return profile by it's ID.
     *
     * @param id - the Profile ID
     * @return - profile
     */
    Profile findById(String id);

    <T extends Profile> T findById(String id, Class<T> entityClass);

    Profile findByPublicId(String id);

    <T extends Profile> T findByPublicId(String id, Class<T> entityClass);


    void save(Profile profile);


    boolean hasManager(String profilePublicId);

    List<Profile> findLikeUsername(String username);

    List<UserProfile> findUsersByManager(String managerId);

    UserProfile getManagerUser(String managerPublicId, String publicId);

    Set<String> getManagerUserIds(String managerId);

    String getPulblicIdById( String id);

    String getIdByPulblicId( String publicId);

    Set<String> getPulblicIdsByIds( Set<String> usersPublicId);

    List<Profile> findByRole(String role, Pageable pageable);

    long countByRole(String role);

    //List<Profile> findByFilter(ProfileRepositoryFilter filter, Pageable pageable);

    long countByFilter(ProfileRepositoryFilter filter);

    <T extends Profile> List<T>  findByFilter(ProfileRepositoryFilter filter, Pageable pageable, Class<T> entityClass);

    boolean hasRole(String profilePublicId, String roleUser);

    boolean profileExistsByPublicId(String id);
}
