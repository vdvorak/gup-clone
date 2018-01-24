package ua.com.gup.common.service;

import ua.com.gup.common.model.mongo.CommonProfile;
import ua.com.gup.common.model.security.Role;

public interface CommonProfileService<T extends CommonProfile> {

    T findById(String id);

    T findByPublicId(String id);

    String getIdByPulblicId(String publicId);

    boolean profileExistsByPublicId(String profilePublicId);

    boolean hasRole(String profilePublicId, String roleUser);
}
