package ua.com.gup.common.service;

import ua.com.gup.common.model.enumeration.CommonUserRole;
import ua.com.gup.common.model.mongo.CommonProfile;

public interface CommonProfileService<T extends CommonProfile> {

    T findById(String id);

    T findByPublicId(String id);

    String getIdByPulblicId(String publicId);

    boolean profileExistsByPublicId(String profilePublicId);

    boolean hasRole(String profilePublicId, CommonUserRole roleUser);
}
