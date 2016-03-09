package ua.com.itproekt.gup.dao.nace;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.itproekt.gup.model.nace.NACE;

import java.util.List;


public interface NaceRepository extends MongoRepository<NACE, String> {
    List<NACE> findByNameLike(String name);
    List<NACE> findByIdLike(String id);
}
