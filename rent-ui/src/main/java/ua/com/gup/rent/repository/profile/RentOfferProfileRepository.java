package ua.com.gup.rent.repository.profile;

import org.springframework.data.domain.Pageable;
import ua.com.gup.common.repository.CommonProfileRepository;
import ua.com.gup.common.repository.filter.ProfileRepositoryFilter;
import ua.com.gup.rent.model.mongo.user.Profile;
import ua.com.gup.rent.repository.abstracted.generic.RentOfferGenericRepository;

import java.util.List;
import java.util.Set;


public interface RentOfferProfileRepository extends
        RentOfferGenericRepository<Profile, String> ,
        CommonProfileRepository<Profile> {


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


    Profile findByPublicId(String id);



    void save(Profile profile);


    boolean hasManager(String profilePublicId);

    List<Profile> findLikeUsername(String username);


    String getIdByPulblicId( String publicId);

    Set<String> getPulblicIdsByIds( Set<String> usersPublicId);

    List<Profile> findByRole(String role, Pageable pageable);

    long countByRole(String role);

    long countByFilter(ProfileRepositoryFilter filter);

    boolean hasRole(String profilePublicId, String roleUser);

    boolean profileExistsByPublicId(String id);
}
