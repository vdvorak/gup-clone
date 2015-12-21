package ua.com.itproekt.gup.dao.nace;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.itproekt.gup.model.nace.DepartmentOrNace;


public interface NaceRepository extends MongoRepository<DepartmentOrNace, String> {
}
