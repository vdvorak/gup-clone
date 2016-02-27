package ua.com.itproekt.gup.dao.nace;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.itproekt.gup.model.nace.NACE;


public interface NaceRepository extends MongoRepository<NACE, String> {
}
