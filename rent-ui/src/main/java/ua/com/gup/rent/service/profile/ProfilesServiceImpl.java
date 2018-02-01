package ua.com.gup.rent.service.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.com.gup.common.dto.profile.ProfileShortAdminDTO;
import ua.com.gup.common.service.impl.CommonProfileServiceImpl;
import ua.com.gup.rent.model.mongo.user.Profile;
import ua.com.gup.rent.repository.profile.RentOfferProfileRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProfilesServiceImpl
        extends CommonProfileServiceImpl<Profile>
        implements ProfilesService {
    private final Logger log = LoggerFactory.getLogger(ProfilesServiceImpl.class);

    @Autowired
    private RentOfferProfileRepository rentOfferProfileRepository;


    @Override
    public Profile findById(String id) {
        return rentOfferProfileRepository.findById(id);
    }

    @Override
    public boolean hasRole(String profilePublicId, String roleUser) {
        return rentOfferProfileRepository.hasRole(profilePublicId, roleUser);
    }

    @Override
    public Profile findByPublicId(String id) {
        return rentOfferProfileRepository.findByPublicId(id);
    }

    @Override
    public boolean profileExistsByPublicId(String profilePublicId) {
        return rentOfferProfileRepository.profileExistsByPublicId(profilePublicId);
    }

    @Override
    public Page<ProfileShortAdminDTO> findByRole(String role, Pageable pageable) {
        long count = rentOfferProfileRepository.countByRole(role);
        List<Profile> fullProfiles = Collections.EMPTY_LIST;
        if (count > 0) {
            fullProfiles = rentOfferProfileRepository.findByRole(role, pageable);
        }
        List<ProfileShortAdminDTO> list = fullProfiles.stream().map(profile -> new ProfileShortAdminDTO(profile)).collect(Collectors.toList());
        return new PageImpl<>(list, pageable, count);
    }

}
