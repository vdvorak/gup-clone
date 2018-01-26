package ua.com.gup.common.repository;

import ua.com.gup.common.model.mongo.CommonProfile;

public interface CommonProfileRepository {
    String getIdByPulblicId( String publicId);

    CommonProfile findByPublicId(String id);

    boolean existsByRole(String role);
}
