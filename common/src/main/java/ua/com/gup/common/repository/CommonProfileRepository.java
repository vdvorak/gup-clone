package ua.com.gup.common.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import ua.com.gup.common.model.mongo.CommonProfile;
import ua.com.gup.common.repository.filter.ProfileRepositoryFilter;

import java.util.List;
import java.util.Set;

public interface CommonProfileRepository<T extends CommonProfile> {
    String getIdByPulblicId( String publicId);

    T findById(String id);

    T findByPublicId(String id);

    Set<String> getPulblicIdsByIds(Set<String> usersPublicId);

    boolean existsByRole(String role);

    void updateProfile(T profile);

    boolean hasManager(String profilePublicId);

    long countByFilter(ProfileRepositoryFilter filter);

    List<T>  findByFilter(ProfileRepositoryFilter filter, Pageable pageable);
}
